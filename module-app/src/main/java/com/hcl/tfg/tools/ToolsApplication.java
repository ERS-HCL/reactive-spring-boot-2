package com.hcl.tfg.tools;

import com.hcl.tfg.tools.controllers.ConfigController;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.boot.jdbc.metadata.TomcatDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

/*
import java.time.Duration;
import com.netflix.spectator.atlas.AtlasConfig;
import io.micrometer.atlas.AtlasMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
*/


//@EnableAutoConfiguration(exclude = {DataSourcePoolMetricsAutoConfiguration.class})
@EnableWebFlux
@SpringBootApplication
public class ToolsApplication {
	public static void main(String[] args) {

		SpringApplication.run(ToolsApplication.class, args);
	}


	@Bean
	@ConditionalOnProperty(value = "spring.metrics.binders.jvmthreads.enabled", matchIfMissing = true)
	@ConditionalOnMissingBean(JvmThreadMetrics.class)
	public JvmThreadMetrics jvmThreadMetrics() {
		return new JvmThreadMetrics();
	}

	@Bean
	@ConditionalOnProperty(value = "spring.metrics.binders.jvmgc.enabled", matchIfMissing = true)
	@ConditionalOnMissingBean(JvmGcMetrics.class)
	public JvmGcMetrics jvmGcMetrics() {
		return new JvmGcMetrics();
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("region", "us-east-1");
	}

	/*AtlasConfig atlasConfig = new AtlasConfig() {
		@Override
		public Duration step() {
			return Duration.ofSeconds(10);
		}

		@Override
		public String get(String k) {
			//return System.getProperty(k);
			return null;
		}
	};

*/

	/*@Bean
	MeterRegistry  getMeterRegistry(){

		CompositeMeterRegistry compositeMeterRegistry = new CompositeMeterRegistry();
		AtlasMeterRegistry atlasMeterRegistry = new AtlasMeterRegistry(atlasConfig,Clock.SYSTEM);
		atlasMeterRegistry.config().commonTags("region","us-east-1");
		compositeMeterRegistry.add(atlasMeterRegistry);
		return compositeMeterRegistry;
	}*/


	/*@Bean
	JvmThreadMetrics threadMetrics() {
		return new JvmThreadMetrics();
	}
*/



}
