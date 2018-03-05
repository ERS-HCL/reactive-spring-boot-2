package com.hcl.tfg.tools.controllers;


import com.codahale.metrics.MetricRegistry;
import com.hcl.tfg.tools.ConfigService;
import com.hcl.tfg.tools.beans.ConfigValues;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;


/**
 * Created by begin.samuel on 01-03-2018.
 */

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class ConfigControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ConfigControllerTest.class.getName());
    private static ConfigValues currentTestData;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext context;

    @MockBean(name="configService")
    private ConfigService configService;

    @MockBean(name="registry")
    private MeterRegistry registry;

    @MockBean(name="metrics")
    private Metrics metrics;


    ConfigValues  configValues = new ConfigValues();




    private String  keyName = "key-name ";
    private String  keyValue = "key-value ";
    private String targetUri = "http://localhost:8181";


    @Before
    public void setUp()
    {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();

    }


    @Test
    public void getConfigureValueTest() throws Exception {

        Flux<String> configValuesFlux = this.webTestClient.get().uri("/api/category")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .returnResult(String.class)
                .getResponseBody();
        Assert.assertEquals(true,configValuesFlux.blockFirst().contains("payment"));


    }

    public Executable   getExecutableTestCase(int i, ConfigValues temp){


        Executable executable = ()->{

            currentTestData = temp;

            logger.info( "Test case (" +  i + ") URL " + targetUri);
            logger.info( "Body : " + currentTestData.toString());
            WebTestClient.bindToServer()
                    .baseUrl(targetUri)
                    .build()
                    .post()
                    .uri("/config-values")
                    .body(Mono.just(temp),ConfigValues.class)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody().isEmpty();


        };
        return executable;
    }

    @TestFactory
    public Stream<DynamicTest>  loadAllTestData(){
        Collection<DynamicTest> dynamicTests = new ArrayList<DynamicTest>();
        configValues.setSubcategory("payment");
        configValues.setCategory("payment");
        configValues.setApplication("ccc");
        configValues.setConfigname("Configuration for Key");
        configValues.setGroupid("G1000");
        configValues.setIncludedflows("AAL,MODIFY");
        configValues.setValuetype("string");

        for(int i =1; i< 2;i++) {
            configValues.setKeyname(keyName + i);
            configValues.setKeyvalue(keyValue + i);
            configValues.setId(UUID.randomUUID().toString());
            Executable executable = getExecutableTestCase(i,configValues);
            DynamicTest dynamicTest = DynamicTest.dynamicTest("Posting Config Values of " + i, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests.stream();
    }


}
