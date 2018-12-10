package com.company.service.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.ProcessDefinition;
import org.kie.api.runtime.query.QueryContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@ShellComponent
public class ProcessDefinitionsCommand {

    @Autowired
    RuntimeDataService runtimeDataService;

    @Autowired
    TemplateEngine templateEngine;

    @ShellMethod(value = "Show process definitions", key = "processdefs")
    public String showProcessDefs() {
        Collection<ProcessDefinition> processDefinitions = runtimeDataService.getProcesses(new QueryContext());
        Context templateContext = new Context(Locale.US,
                                              Collections.singletonMap("processdefs",
                                                                       processDefinitions));

        return templateEngine.process("processdefs",
                                      templateContext);
    }
}
