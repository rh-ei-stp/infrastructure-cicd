package com.redhat.consulting.infrastructure;

import static io.fabric8.kubernetes.assertions.Assertions.assertThat;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.fabric8.kubernetes.client.KubernetesClient;

@RunWith(Arquillian.class)
@RunAsClient
public class OpenShiftIntegrationKT {

	@ArquillianResource
	KubernetesClient client;

	@Test
	public void testAppProvisionsRunningPods() throws Exception {
		assertThat(client).deployments().pods().isPodReadyForPeriod();
	}
}
