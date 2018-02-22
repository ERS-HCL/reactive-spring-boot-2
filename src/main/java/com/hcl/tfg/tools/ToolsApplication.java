package com.hcl.tfg.tools;

import com.hcl.tfg.tools.handlers.ConfigValuesHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@EnableWebFlux
@SpringBootApplication
public class ToolsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);
	}




/*




	private static final String HOST = "localhost";
	private static final int PORT = 8080;


	public static void main(String[] args) throws Exception {

		HttpServer httpServer = HttpServer.create(HOST, PORT);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler());
		Mono<? extends NettyContext> context = httpServer.newHandler(adapter);
		context.block();
		System.in.read();
	}

	static HttpHandler httpHandler() {
		return RouterFunctions.toHttpHandler(routes(new ConfigValuesHandler()));
	}
*/


}
