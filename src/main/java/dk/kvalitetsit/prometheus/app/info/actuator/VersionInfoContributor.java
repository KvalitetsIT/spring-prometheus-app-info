package dk.kvalitetsit.prometheus.app.info.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

public class VersionInfoContributor implements InfoContributor {
    private final VersionProvider versionProvider;

    public VersionInfoContributor(VersionProvider versionProvider) {
        this.versionProvider = versionProvider;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("version", buildVersion());
    }

    private String buildVersion() {
        return versionProvider.getVersion();
    }
}
