package com.hcl.tfg.tools.handlers;

import com.hcl.tfg.tools.beans.ConfigValues;
import com.hcl.tfg.tools.Repository.ConfigValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;



@Service
public class ConfigValuesHandler {

    @Autowired
    private  ConfigValuesRepository configValues;

    public ConfigValuesHandler() {
    }




    public Mono<ServerResponse> all(ServerRequest req) {
        return ServerResponse.ok().body(this.configValues.findAll(), ConfigValues.class);
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        System.out.println("req" + req);
        System.out.println("Config" + req.bodyToMono(ConfigValues.class));
        return req.bodyToMono(ConfigValues.class)
                .flatMap(post -> this.configValues.save(post))
                .flatMap(p -> ServerResponse.created(URI.create("/config-values/" + p.getId())).build());
    }

    public Mono<ServerResponse> get(ServerRequest req) {
        return this.configValues.findById(req.pathVariable("id"))
                .flatMap(post -> ServerResponse.ok().body(Mono.just(post), ConfigValues.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest req) {

        return Mono
                .zip(
                        (data) -> {
                            ConfigValues p = (ConfigValues) data[0];
                            ConfigValues p2 = (ConfigValues) data[1];
                            p.setKeyvalue(p2.getKeyvalue());
                            p.setKeyname(p2.getKeyname());
                            return p;
                        },
                        this.configValues.findById(req.pathVariable("id")),
                        req.bodyToMono(ConfigValues.class)
                )
                .cast(ConfigValues.class)
                .flatMap(post -> this.configValues.save(post))
                .flatMap(post -> ServerResponse.noContent().build());

    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        return ServerResponse.noContent().build(this.configValues.deleteById(req.pathVariable("id")));
    }


}
