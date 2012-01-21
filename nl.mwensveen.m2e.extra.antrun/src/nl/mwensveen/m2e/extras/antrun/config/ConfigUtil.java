/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun.config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
	private static Config config = null;

	public static Config getConfig() {
		if (config == null) {
			synchronized (ConfigUtil.class) {
				if (config == null) {
					LOG.info("Reading AntRun configuration");
					String userDir = System.getProperty("user.home", "");
					File xml = new File(userDir + "/.m2/nl.mwensveen.m2e.extras.antrun.config.xml");
					if (xml.exists()) {
						try {
							LOG.info("unmarshalling config");
							JAXBContext context = JAXBContext.newInstance(Config.class);
							Unmarshaller um = context.createUnmarshaller();
							config = (Config) um.unmarshal(xml);
						} catch (JAXBException e) {
							config = new Config();
							LOG.error("Problem with config", e);
						}
					} else {
						LOG.warn("Cannot read the configuration file");
						config = new Config();
					}
					LOG.debug("Config: " + config.toString());
				}
			}
		}
		return config;
	}

	public static boolean mustRun(Config config, String groupId, String artifactId, String executionId) {
		if (config == null || groupId == null || artifactId == null || executionId == null) {
			return false;
		}
		for (ConfigItem item : config.getConfigList()) {
			if (groupId.equals(item.getGroupId()) && artifactId.equals(item.getArtifactId()) && executionId.equals(item.getExecutionId())) {
				LOG.debug(item.toString());
				return item.isRun();
			}
		}
		return false;
	}

}
