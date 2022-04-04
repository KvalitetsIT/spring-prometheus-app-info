import dk.kvalitetsit.prometheus.app.info.actuator.GitVersionProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.actuate.info.Info;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GitVersionProviderTest {
    @Test
    public void testBuildVersionNoTag() {
        var commit = "abc";

        var versionInfoContributor = new GitVersionProvider(commit, Collections.emptyList());

        var version= versionInfoContributor.getVersion();

        assertEquals(commit, version);
    }

    @Test
    public void testBuildVersionNoValidTags() {
        var commit = "abc";
        var tags = Arrays.asList("tag1", "tag2");
        var builder = Mockito.mock(Info.Builder.class);

        var versionInfoContributor = new GitVersionProvider(commit, tags);

        var version= versionInfoContributor.getVersion();

        assertEquals(commit, version);
    }

    @Test
    public void testBuildVersionValidTag() {
        var commit = "abc";
        var validTag = "v0.2.1";
        var tags = Arrays.asList("tag1", validTag, "tag2");

        var versionInfoContributor = new GitVersionProvider(commit, tags);

        var version= versionInfoContributor.getVersion();

        assertEquals(validTag.substring(1), version);
    }

    @Test
    public void testBuildVersionDevWhenNull() {
        var versionInfoContributor = new GitVersionProvider(null, null);

        var version= versionInfoContributor.getVersion();

        assertEquals("dev", version);
    }
}
