package com.lanhai.sample.elastric;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Profile("dev")
public class JerseyApplication extends SpringBootServletInitializer
{
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

	@SuppressWarnings("unused")
	private void initConfig()
	{}

	public static void main(String[] args)
	{
		new JerseyApplication().configure(new SpringApplicationBuilder(JerseyApplication.class)).run(args);
	}

}
