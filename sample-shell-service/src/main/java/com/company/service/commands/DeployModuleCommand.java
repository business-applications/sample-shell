package com.company.service.commands;

import java.util.Collections;
import java.util.Locale;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@ShellComponent
public class DeployModuleCommand {

    @Autowired
    DeploymentService deploymentService;

    @Autowired
    TemplateEngine templateEngine;

    @ShellMethod(value = "Deploy module", key = "deploy")
    public String deployProcessDef(String groupId,
                                   String artifactId,
                                   String version) {
        KModuleDeploymentUnit deploymentUnit = new KModuleDeploymentUnit(groupId,
                                                                         artifactId,
                                                                         version);
        deploymentService.deploy(deploymentUnit);
        Context templateContext = new Context(Locale.US,
                                              Collections.singletonMap("deploymentunit",
                                                                       deploymentUnit));

        return templateEngine.process("deploymodule",
                                      templateContext);
    }
}
