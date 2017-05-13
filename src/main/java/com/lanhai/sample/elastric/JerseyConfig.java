package com.lanhai.sample.elastric;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.lanhai.sample.elastric.api.IndexResource;
import com.lanhai.sample.elastric.exception.ResourceExceptions;

@Component
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		packages(new String[]
		{ 
				IndexResource.class.getPackage().getName(), 
				ResourceExceptions.class.getPackage().getName() })
				// .register(LoggingFilter.class)
		;
	}
}
