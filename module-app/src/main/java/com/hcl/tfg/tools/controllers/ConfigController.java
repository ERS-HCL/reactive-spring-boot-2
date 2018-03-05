package com.hcl.tfg.tools.controllers;

/**
 * Created by begin.samuel on 26-02-2018.
 */

import com.hcl.tfg.tools.ConfigService;
import com.hcl.tfg.tools.Repository.ConfigValuesRepositoryCustom;
import com.hcl.tfg.tools.beans.ConfigValues;
import com.netflix.spectator.atlas.AtlasConfig;
import io.micrometer.atlas.AtlasMeterRegistry;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.sql.Time;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

@Timed
@RestController
public class ConfigController  implements ConfigValuesRepositoryCustom{


    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    private List<String> category = Arrays.asList("payments","credits","terms");



    @Autowired
    ConfigService configService;


    @Autowired
    private MeterRegistry registry;

    private final io.micrometer.core.instrument.Timer indexTimer = Metrics.timer("request.timer");
    private final LongAdder concurrent = Metrics.gauge("request.concurrent", new LongAdder());
    private final Counter successes = Metrics.counter("request.index", "result", "success");
    private final Counter failures = Metrics.counter("request.index", "result", "failure");
    Counter categoryCounter = Metrics.counter("http.server.requests", "uri", "/api/category");
    Counter configCounter = Metrics.counter("http.server.requests", "uri", "/api/category");


    AtlasConfig atlasConfig = new AtlasConfig() {
        @Override
        public Duration step() {
            return Duration.ofSeconds(10);
        }

        @Override
        public String get(String k) {
            return System.getProperty(k);
            //return null;
        }

        @Override
        public Map<String, String> commonTags() {

            Map<String,String> tags = new HashMap<String,String>();
            tags.put("region", "us-east-1");
            tags.put("nf.app","config-tool-8");
            return tags;


        }
    };

     public ConfigController() {
         registry = new AtlasMeterRegistry(atlasConfig,Clock.SYSTEM);
         logger.info( " Meter size : " + registry.getMeters().size());
          //
    }

    @GetMapping("/api/config-values")
    @Timed(extraTags = { "region", "us-east-1" })
    @Timed(value = "all.configs", longTask = true)
    public Flux<ConfigValues> allConfigValues() {
        long time = System.currentTimeMillis();
        configCounter.increment();
        Flux<ConfigValues> tempValues =  this.configService.allConfigValues();
        indexTimer.record(System.currentTimeMillis() - time, TimeUnit.MILLISECONDS);
        successes.increment();
        return tempValues;
    }


    @GetMapping("/api/category")
    @Timed( value="http.server.requests" , percentiles = {0.5, 0.95, 0.999}, histogram = true)
    public Flux<String> allCategory() {
        try {
            categoryCounter.increment();
            successes.increment();
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            failures.increment();
        }
        return Flux.fromIterable(category);
    }

    @GetMapping("/api/categoryAsync")
    public CompletableFuture<Collection<String>> categoryNameAsync() {
        return CompletableFuture.supplyAsync(() -> Collections.singletonList("jon"));
    }

    /**
     * Fallback for {@link ConfigController#allCategory()}
     *
     * @return category
     */
    @SuppressWarnings("unused")
    public List<String> fallbackCategory() {
        return Arrays.asList("old mike", "fallback frank");
    }

    @GetMapping("/api/fail")
    public String fail() {
        throw new RuntimeException("boom");
    }

    @GetMapping("/api/stats")
    public Map<String, Number> stats() {
        return Optional.ofNullable(registry.find("http.server.requests").tags("uri", "/api/category")
                .timer())
                .map(t -> new HashMap<String, Number>() {{
                    put("count", t.count());
                    put("max", t.max(TimeUnit.MILLISECONDS));
                    put("mean", t.mean(TimeUnit.MILLISECONDS));
                    put("50.percentile", t.percentile(0.5, TimeUnit.MILLISECONDS));
                    put("95.percentile", t.percentile(0.95, TimeUnit.MILLISECONDS));
                }})
                .orElse(null);


    }
}
