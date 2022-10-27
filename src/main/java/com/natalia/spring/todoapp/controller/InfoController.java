package com.natalia.spring.todoapp.controller;

import com.natalia.spring.todoapp.restrepository.TaskConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController {
    private DataSourceProperties data;

//    @Value("${spring.datasource.url}")//odwołujemy się do wartości w application.properties
//    private String url;
//teraz jak to mamy oraz TaskConfigurationProperties to wchodząc na http://localhost:8080/info/prop dostajemy false ale /url dostajemy
    //ten url który mamy w application.properties
    private TaskConfigurationProperties prop;

    public InfoController(DataSourceProperties data, TaskConfigurationProperties prop) {
        this.data = data;
        this.prop = prop;
    }

    @GetMapping("info/url")
    String url() {
        return data.getUrl();
    }

    @GetMapping("info/prop")
    boolean prop() {
        return prop.isAllowMultipleTasksFromTemplate();

    }
}
