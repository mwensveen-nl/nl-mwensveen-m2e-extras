/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
    private static volatile Config config = null;

    public static Config getConfig() {
        if (config == null) {
            synchronized (ConfigUtil.class) {
                if (config == null) {
                    LOG.info("Reading AntRun configuration");
                    String userDir = System.getProperty("user.home", "");
                    File xml = new File(userDir + "/.m2/nl.mwensveen.m2e.extras.antrun.config.xml");
                    LOG.info("using config-file: " + xml.getAbsolutePath());
                    if (xml.exists()) {
                        LOG.info("unmarshalling config");
                        Config c = new Config();
                        ConfigItem ci = null;
                        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
                        XMLEventReader xmlEventReader;
                        try {
                            xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(xml));
                            while (xmlEventReader.hasNext()) {
                                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                                switch (xmlEvent.getEventType()) {
                                    case XMLEvent.START_ELEMENT:
                                        StartElement startElement = xmlEvent.asStartElement();
                                        switch (startElement.getName().getLocalPart()) {
                                            case "config-item":
                                                ci = new ConfigItem();
                                                c.getConfigList().add(ci);
                                                break;
                                            case "groupid":
                                                ci.setGroupId(xmlEventReader.nextEvent().asCharacters().getData());
                                                break;
                                            case "artifactid":
                                                ci.setArtifactId(xmlEventReader.nextEvent().asCharacters().getData());
                                                break;
                                            case "executionid":
                                                ci.setExecutionId(xmlEventReader.nextEvent().asCharacters().getData());
                                                break;
                                            case "run":
                                                ci.setRun(Boolean.valueOf(xmlEventReader.nextEvent().asCharacters().getData()));
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                            config = c;
                        } catch (FileNotFoundException | XMLStreamException e) {
                            LOG.error("Cannot read <home>/.m2/nl.mwensveen.m2e.extras.antrun.config.xml", e);
                            config = new Config();
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
