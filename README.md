# spring-prometheus-app-info

Utility to provide application version etc. to prometheus.

Include it in your Spring Boot project and Spring will do its magic to expose both Actuator and Prometheus endpoints. 

## Configuration

By default, it configures Actuator to listen on port 8081. It is possible to override the configuration by including an `actuator-custom.properties` file in your project. 

## Release

To create a new release run `mvn release:prepare`. This will create a new tag it Git. CI/CD pipeline will then do its magic to push a new version to Github Maven repository. 
