package dk.kvalitetsit.prometheus.app.info.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;

public class ApplicationInformationProbe implements MeterBinder {
    private final String name;
    private final String description;
    private final VersionProvider versionProvider;
    private final HealthEndpoint healthEndpoint;

    public ApplicationInformationProbe(VersionProvider versionProvider, HealthEndpoint healthEndpoint) {
        this.versionProvider = versionProvider;
        this.healthEndpoint = healthEndpoint;
        this.name = "application";
        this.description = "Application Information";
    }

    @Override
    public void bindTo(final MeterRegistry meterRegistry) {
        Gauge.builder(name, this, value -> serviceStatus())
                .description(description)
                .tags(tags())
                .baseUnit("information")
                .register(meterRegistry);
    }

    private double serviceStatus() {
        var status = healthEndpoint.health().getStatus();

        if(status == Status.UP) {
            return 1;
        }

        if(status.equals(Status.DOWN)) {
            return 0;
        }

        if(status.equals(Status.OUT_OF_SERVICE)) {
            return 0;
        }

        return 0.5;
    }

    protected  Iterable<Tag> tags() {
            return Tags.of(Tag.of("version", versionProvider.getVersion()));
    }
}

