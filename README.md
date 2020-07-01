# spring-prometheus-app-info

Utility to provide application version etc. to prometheus.

Include it in your Spring Boot project and Spring will do its magic to expose both Actuator and Prometheus endpoints. 

In your project include KvalitetsIT Github Maven package repository in your pom file:
```
<repositories>
        <repository>
                <id>github-maven</id>
                <name>KvalitetsIT GitHub</name>
                <url>https://github.com/KvalitetsIT/maven/raw/mvn-repo/</url>
        </repository>
</repositories>
```

## Configuration

By default it configures Actuator to listen on port 8081. It is possible to override the configuration by including an `actuator-custom.properties` file in your project. 

## Release

To create a new release run `mvn release:prepare`. This will create a new tag it Git. Jenkins will then do its magic to push a new version to the Github Maven Package repository. 


