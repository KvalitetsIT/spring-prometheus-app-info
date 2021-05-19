package dk.kvalitetsit.prometheus.app.info.actuator.configuration;

import dk.kvalitetsit.prometheus.app.info.actuator.ApplicationInformationProbe;
import dk.kvalitetsit.prometheus.app.info.actuator.GitVersionProvider;
import dk.kvalitetsit.prometheus.app.info.actuator.VersionInfoContributor;
import dk.kvalitetsit.prometheus.app.info.actuator.VersionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("actuator.properties")
@PropertySource(value = "actuator-custom.properties", ignoreResourceNotFound = true)
@PropertySource(value = "git.properties", ignoreResourceNotFound = true)
public class ActuatorAppInfoAutoConfiguration {
    @Bean
    public VersionInfoContributor versionInfo(VersionProvider versionProvider) {
        return new VersionInfoContributor(versionProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public VersionProvider versionProvider(@Value("${git.commit.id.describe:#{null}}")String commit, @Value("${git.tags:#{null}}") List<String> tags) {
        return new GitVersionProvider(commit, tags);
    }

    @Bean
    ApplicationInformationProbe applicationInformationProbe(VersionProvider versionProvider, HealthEndpoint healthEndpoint) {
        return new ApplicationInformationProbe(versionProvider, healthEndpoint);
    }
}


