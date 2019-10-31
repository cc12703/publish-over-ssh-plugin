package jenkins.plugins.publish_over_ssh.jcasc;

import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.casc.misc.JenkinsConfiguredWithCodeRule;
import jenkins.plugins.publish_over_ssh.BapSshHostConfiguration;
import jenkins.plugins.publish_over_ssh.BapSshPublisherPlugin;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class HostConfigurationTest {


    @ClassRule
    @ConfiguredWithCode("HostConfigurationTest.yaml")
    public static JenkinsConfiguredWithCodeRule j = new JenkinsConfiguredWithCodeRule();


    @Test
    public void import_host_configuration() {
        BapSshPublisherPlugin.Descriptor cfg
                = j.jenkins.getInstance().getDescriptorByType(BapSshPublisherPlugin.Descriptor.class);


        final List<BapSshHostConfiguration> hosts = cfg.getHostConfigurations();
        assertThat(hosts, hasSize(1));

        final BapSshHostConfiguration host = hosts.get(0);
        assertThat(host.isOverrideKey(), equalTo(true));
        assertThat(host.getName(),equalTo("test.host"));
        assertThat(host.getKey(), equalTo("test"));
        assertThat(host.getHostname(), equalTo("ftp.test.com"));
        assertThat(host.getUsername(), equalTo("test.name"));
        assertThat(host.getPort(), equalTo(2022));


    }


}
