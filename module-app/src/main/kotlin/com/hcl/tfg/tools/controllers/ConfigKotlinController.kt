package com.hcl.tfg.tools.controllers

/**
 * Created by begin.samuel on 26-02-2018.
 */

import com.hcl.tfg.tools.ConfigService
import com.hcl.tfg.tools.Repository.ConfigValuesRepository
import com.hcl.tfg.tools.beans.ConfigValues
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux




@RestController
class ConfigKotlinController {


    @Autowired
    private val configService: ConfigService? = null


    @GetMapping("/api/config-values-kotlin")
    fun getConfigValuesInKotlin(): Flux<ConfigValues>? {

        var configValuesFlux: Flux<ConfigValues>? = null
        configValuesFlux = configService!!.allConfigValues()
        return configValuesFlux

    }



}
