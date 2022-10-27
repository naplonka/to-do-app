package com.natalia.spring.todoapp.restrepository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task.template")
public class TaskConfigurationProperties  {
    public boolean isAllowMultipleTasksFromTemplate() {
        return allowMultipleTasksFromTemplate;
    }

    public void setAllowMultipleTasksFromTemplate(boolean allowMultipleTasksFromTemplate) {
        this.allowMultipleTasksFromTemplate = allowMultipleTasksFromTemplate;
    }

    private boolean allowMultipleTasksFromTemplate;


}
