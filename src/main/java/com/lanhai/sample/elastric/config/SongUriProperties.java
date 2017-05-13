package com.lanhai.sample.elastric.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(
{ @PropertySource(value = "classpath:application-uri.properties", ignoreResourceNotFound = true) })
public class SongUriProperties
{
	@Value("${song.index}")
	private String index;

	@Value("${song.type}")
	private String type;

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
