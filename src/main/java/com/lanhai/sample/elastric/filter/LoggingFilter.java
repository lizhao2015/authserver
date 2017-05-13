package com.lanhai.sample.elastric.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 自定义日志过滤器
 * <p>
 * 用于Debug请求和相应的资源信息
 * 
 * @author Nick
 *
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter
{
	@Context
	private ResourceInfo resourceInfo;

	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	final static Gson gsonSerNulls = new GsonBuilder().serializeNulls().create();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException
	{
		MDC.put("start-time", String.valueOf(System.currentTimeMillis()));

		log.debug("Request URI: /{}", requestContext.getUriInfo().getPath());
		log.debug("Request Method: {}", requestContext.getMethod());
		log.debug("Resource: {}.{}", resourceInfo.getResourceClass().getCanonicalName(), resourceInfo.getResourceMethod().getName());

		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		if (!headers.isEmpty())
			log.debug("Headers: {}", headers);

		MultivaluedMap<String, String> pathParameters = requestContext.getUriInfo().getPathParameters();

		if (!pathParameters.isEmpty())
			log.debug("Path Parameters: {}", pathParameters);

		MultivaluedMap<String, String> queryParameters = requestContext.getUriInfo().getQueryParameters();
		if (!queryParameters.isEmpty())
			log.debug("Query Parameters: {}", queryParameters);

		String entity = readEntityStream(requestContext);
		if (null != entity && entity.trim().length() > 0)
		{
			log.debug("Entity Parameters : {}", entity);
		}
	}

	private String readEntityStream(ContainerRequestContext requestContext)
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		final InputStream inputStream = requestContext.getEntityStream();
		final StringBuilder builder = new StringBuilder();
		try
		{
			ReaderWriter.writeTo(inputStream, outStream);
			byte[] requestEntity = outStream.toByteArray();
			if (requestEntity.length == 0)
			{
				builder.append("");
			}
			else
			{
				builder.append(new String(requestEntity));
			}
			requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
		}
		catch (IOException ex)
		{
			log.debug("----Exception occurred while reading entity stream :{}", ex.getMessage());
		}

		return builder.toString();
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException
	{
		String stTime = MDC.get("start-time");
		if (null == stTime || stTime.length() == 0)
		{
			return;
		}
		long startTime = Long.parseLong(stTime);
		long executionTime = System.currentTimeMillis() - startTime;
		log.debug("Time: {} ms", executionTime);

		MDC.clear();

		log.debug("Response: {}", gsonSerNulls.toJson(responseContext.getEntity()));
	}
}