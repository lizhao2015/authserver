package com.lanhai.sample.elastric.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.lanhai.sample.elastric.dto.BaseDTO;

/**
 * 首页资源
 * 
 * @author Nick
 *
 */
@Component
@Path("index")
@Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndexResource
{

	@GET
	public BaseDTO index()
	{
		return new BaseDTO(0);
	}
}
