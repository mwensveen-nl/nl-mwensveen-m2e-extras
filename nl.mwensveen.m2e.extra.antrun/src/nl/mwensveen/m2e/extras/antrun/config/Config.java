/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun.config;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "antrunconfig", namespace="nl.mwensveen.m2e.extras.antrun")
public class Config {
	private ArrayList<ConfigItem> configList;

	/**
	 * @return the configList
	 */
	@XmlElementWrapper(name = "config-items")
	@XmlElement(name = "config-item")
	public ArrayList<ConfigItem> getConfigList() {
		if (configList == null) {
			configList = new ArrayList<ConfigItem>();
		}
		return configList;
	}

	/**
	 * @param configList the configList to set
	 */
	public void setConfigList(ArrayList<ConfigItem> configList) {
		this.configList = configList;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Config [configList=").append(configList).append("]");
		return builder.toString();
	}	
	
}
