package com.lanhai.sample.elastric.api;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lanhai.sample.elastric.dto.ResultError;
import com.lanhai.sample.elastric.exception.ResourceExceptions.ResourceException;
import com.lanhai.sample.elastric.util.ExceptionCode;

/**
 * 资源抽象类
 * 
 * @author Nick
 *
 */
public abstract class AbstractResource
{
	@Context
	protected HttpServletRequest request;

	@Context
	protected HttpServletResponse response;

	protected static final String UTF_8 = "UTF-8";

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected final static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	protected final static Gson gsonSerNulls = new GsonBuilder().serializeNulls().create();

	protected Response responseBuild(String json) throws UnsupportedEncodingException
	{
		return Response.ok(json).header(HttpHeaders.DATE, new Date()).header(HttpHeaders.CONTENT_LENGTH, json.getBytes(Charset.forName(UTF_8)).length).build();
	}

	protected void generateResourceException(int statusCode, ExceptionCode exceptionCode, Exception e)
	{
		generateResourceException(1, statusCode, exceptionCode, e);
	}

	protected void generateResourceException(int result, int statusCode, ExceptionCode exceptionCode, Exception e)
	{
		ResultError resultError = new ResultError(result, exceptionCode, org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage(e));

		log.error(getClass() + " Exception info:{}", resultError);

		ResultError resultError0 = new ResultError(result, exceptionCode);
		Response response = Response.status(statusCode).entity(resultError0).build();

		throw new ResourceException(response);
	}
}
