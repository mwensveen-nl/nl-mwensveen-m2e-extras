/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config-item", namespace="nl.mwensveen.m2e.extras.antrun")
public class ConfigItem {
	private String groupId;
	private String artifactId;
	private String executionId;
	private boolean run;
	/**
	 * @return the groupId
	 */
	@XmlElement(name = "groupid")
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the artifactId
	 */
	@XmlElement(name = "artifactid")
	public String getArtifactId() {
		return artifactId;
	}
	/**
	 * @param artifactId the artifactId to set
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	/**
	 * @return the executionId
	 */
	@XmlElement(name = "executionid")
	public String getExecutionId() {
		return executionId;
	}
	/**
	 * @param executionId the executionId to set
	 */
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	/**
	 * @return the run
	 */
	@XmlElement(name = "run")
	public boolean isRun() {
		return run;
	}
	/**
	 * @param run the run to set
	 */
	public void setRun(boolean run) {
		this.run = run;
	}
	/** {@inheritDoc} */
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigItem [groupId=").append(groupId).append(", artifactId=").append(artifactId).append(", executionId=")
				.append(executionId).append(", run=").append(run).append("]");
		return builder.toString();
	}
	
	
}
