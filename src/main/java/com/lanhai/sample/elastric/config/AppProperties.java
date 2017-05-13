package com.lanhai.sample.elastric.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * App Config
 * 
 * @author Nick
 *
 */
@Component
@ConfigurationProperties(prefix = "es", ignoreInvalidFields = true)
public class AppProperties
{
	String url;
	
	String clusterName;
	
	int clientPort;
	
	String host;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getClusterName()
	{
		return clusterName;
	}

	public void setClusterName(String clusterName)
	{
		this.clusterName = clusterName;
	}

	public int getClientPort()
	{
		return clientPort;
	}

	public void setClientPort(int clientPort)
	{
		this.clientPort = clientPort;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}
}
