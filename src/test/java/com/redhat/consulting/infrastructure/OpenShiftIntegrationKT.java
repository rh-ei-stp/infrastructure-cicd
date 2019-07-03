package com.redhat.consulting.infrastructure;

import static io.fabric8.kubernetes.assertions.Assertions.assertThat;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.arquillian.cube.kubernetes.api.Session;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapList;
import io.fabric8.kubernetes.client.KubernetesClient;

@RunWith(Arquillian.class)
@RunAsClient
public class OpenShiftIntegrationKT {

	private static final Logger LOG = LogManager.getLogger(OpenShiftIntegrationKT.class);

	/*
	 * @Inject
	 * 
	 * @Uri("direct:produceOneMessage") private ProducerTemplate producer;
	 */

	@ArquillianResource
	KubernetesClient client;

	@ArquillianResource
	Session session;

	private String currentNamespace;

	@Before
	public void init() {
		currentNamespace = session.getNamespace();
		LOG.info("Init::currentNamepsace: " + currentNamespace);
	}

	@Test
	public void testAppProvisionsRunningPods() throws Exception {

		LOG.info("NameSpace: " + client.apps().getNamespace());

		ConfigMapList configMapList = client.configMaps().list();
		for (ConfigMap cm : configMapList.getItems()) {
			LOG.info("Kind: " + cm.getKind());
			Map<String, Object> props = cm.getAdditionalProperties();
			props.forEach((key, value) -> LOG.info(key + ":" + value));
		}
		
		assertThat(client).deployments().pods().isPodReadyForPeriod();
	}
}
