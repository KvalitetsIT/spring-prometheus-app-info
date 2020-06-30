package dk.kvalitetsit.prometheus.app.info.actuator;

import java.util.List;
import java.util.regex.Pattern;

public class GitVersionProvider implements VersionProvider {
    private final String commit;
    private final List<String> versions;

    public GitVersionProvider(String commit, List<String> versions) {
        this.commit = commit;
        this.versions = versions;
    }

    public String getVersion() {
        if(versions == null && commit == null) {
            return "dev";
        }

        var pattern = Pattern.compile("^v[0-9]*\\.[0-9]*\\.[0-9]*");

        var optionalTag = versions.stream().filter(pattern.asPredicate()).map( x -> x.substring(1)).findFirst();

        return optionalTag.orElse(commit);
    }
}
