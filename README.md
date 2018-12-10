# Business Applications by jBPM - Spring Shell

This is a demo business application built with https://start.jbpm.org and expanded
to create an interactive runnable shell application.
 
![Sample of demo](img/shell-demo.png?raw=true)

Not all business applications need a web interface. Being able to work with 
an interactive terminal is what the Spring Shell module provides and what we 
use in this jBPM business apps demo.

## Getting Started 
1. Clone this repository locally:
   
   ```
   git clone https://github.com/business-applications/sample-shell.git
   cd sample-shell
   cd sample-shell-service
   chmod 755 launch.sh (only needed for unix environments , use launch.bat for windows)
   ```
   
2. Start your Business Application:
In your business app service module run:
```
./launch.sh clean install(or launch.bat clean install for windows)
```

## Interact with your shell application
Once started, the business app will launch its interactive terminal/shell mode. 
Spring Shell includes a number of built-in commands, such as

```
sample-shell-service:> help
```
which will list all the built in commands as well as the commands that the demo application defines
and those are the interesting ones that you can use in this demo.

The demo commands are:

```
deploy <groupId> <artifactId> <version>
```
This command allows you to deploy a new kie module (kjar) to your business app.
The paramters this command takes are groupId, artifactId, and version

```
processdefs
```
This command allows you to see all process definitions currently available. It takes no
input parameters.

```
processinstances
```
This command shows all process intances currently available. It takes no input paramters.

```
startprocess <processId>
```
This command allows you to start a business process. It takes one parater which is the processId

You can easily build more shell commands and expand the current ones included in the demo. 
The demo app comes with a module called 

```
sample-shell-secondkjar
```
which is not automatically deployed when your business app starts. You can use it 
to see how easy it is to deploy new deployment units with shell command:

```
sample-shell-service:> deploy com.company sample-shell-secondkjar 1.0-SNAPSHOT
```

one this module is deployed you an see its business process definitions available with command: 

```
sample-shell-service:> processdefs
```

which should produce the following results:

```
*** Process Definitions ***

1) Id:com.myspace.simple.firstTestProcess Name:firstTestProcess Version: 1.0 DeploymentId: sample-shell-kjar-1_0-SNAPSHOT
2) Id:com.myspace.simple.thirdTestProcess Name:thirdTestProcess Version: 1.0 DeploymentId: com.company:sample-shell-secondkjar:1.0-SNAPSHOT
3) Id:com.myspace.simple.secondTestProcess Name:secondTestProcess Version: 1.0 DeploymentId: sample-shell-kjar-1_0-SNAPSHOT
4) Id:com.myspace.simple.fourthTestProcess Name:fourthTestProcess Version: 1.0 DeploymentId: com.company:sample-shell-secondkjar:1.0-SNAPSHOT
```

For more information about Spring Shell read on here: https://docs.spring.io/spring-shell/docs/current-SNAPSHOT/reference/htmlsingle/