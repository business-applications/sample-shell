package com.company.service.commands;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jbpm.services.api.ProcessService;
import org.kie.server.springboot.jbpm.ContainerAliasResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@ShellComponent
public class StartProcessCommand {

    @Autowired
    ProcessService processService;

    @Autowired
    ContainerAliasResolver aliasResolver;

    @Autowired
    TemplateEngine templateEngine;

    private String containerAlias = "sample-shell-kjar";

    @ShellMethod(value = "Start business process", key = "startprocess")
    public String startProcess(String processId, String deploymentId) {
        long pid;
        if(deploymentId == null || deploymentId.length() < 1) {
            pid = processService.startProcess(aliasResolver.latest(containerAlias),
                                              processId,
                                              new HashMap<>());
        } else {
            pid = processService.startProcess(deploymentId,
                                              processId,
                                              new HashMap<>());
        }

        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("pid",
                       pid);
        contextMap.put("processid",
                       processId);

        Context templateContext = new Context(Locale.US,
                                              contextMap);

        return templateEngine.process("startprocess",
                                      templateContext);
    }
}
