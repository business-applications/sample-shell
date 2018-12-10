package com.company.service.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.ProcessInstanceDesc;
import org.kie.api.runtime.query.QueryContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@ShellComponent
public class ProcessInstancesCommand {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    RuntimeDataService runtimeDataService;

    @ShellMethod(value = "Show Process Instances", key = "processinstances")
    public String showProcessInstances() {
        Collection<ProcessInstanceDesc> processInstances = runtimeDataService.getProcessInstances(new QueryContext());

        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("processinstances",
                       processInstances);

        Context templateContext = new Context(Locale.US,
                                              contextMap);

        return templateEngine.process("processinstances",
                                      templateContext);
    }
}
