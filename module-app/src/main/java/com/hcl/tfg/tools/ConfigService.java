package com.hcl.tfg.tools;

import com.hcl.tfg.tools.Repository.ConfigValuesRepository;
import com.hcl.tfg.tools.beans.ConfigValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by begin.samuel on 01-03-2018.
 */
@Service
public class ConfigService {


    @Autowired
    private ConfigValuesRepository configValuesRepository;


    public Flux<ConfigValues> allConfigValues() {
        Flux<ConfigValues> configValuesFlux = null;
        try {
            Thread.sleep(200);
            configValuesFlux =  configValuesRepository.findAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return configValuesFlux;
    }

}
