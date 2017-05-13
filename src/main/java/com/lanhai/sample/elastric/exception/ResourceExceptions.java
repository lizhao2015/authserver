package com.lanhai.sample.elastric.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 业务异常
 * 
 * @author Nick
 *
 */
public class ResourceExceptions
{
	protected final static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	// -- Exceptions
	public static class ResourceException extends RuntimeException
	{

		private static final long serialVersionUID = -473944565430120212L;

		private Response response;

		public ResourceException(Response response)
		{
			this.response = response;
		}

		public Response getResponse()
		{
			return response;
		}
	}

	public static class ResourceSubException extends ResourceException
	{

		private static final long serialVersionUID = 5754416922559983195L;

		public ResourceSubException(Response response)
		{
			super(response);
		}
	}

	public static class ResourceSubSubException extends ResourceSubException
	{
		private static final long serialVersionUID = 360899207075387131L;

		public ResourceSubSubException(Response response)
		{
			super(response);
		}
	}

	// -- Exception Mappers
	@Provider
	public static class ResourceExceptionMapper implements ExceptionMapper<ResourceException>
	{

		@Override
		public Response toResponse(ResourceException exception)
		{
			Response r = exception.getResponse();
			return Response.status(r.getStatus()).entity(r.getEntity()).build();
		}
	}

	@Provider
	public static class ResourceSubExceptionMapper implements ExceptionMapper<ResourceSubException>
	{

		@Override
		public Response toResponse(ResourceSubException exception)
		{
			Response r = exception.getResponse();
			return Response.status(r.getStatus()).entity(r.getEntity()).build();
		}
	}

	@Provider
	public static class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>
	{

		@Override
		public Response toResponse(WebApplicationException exception)
		{
			Response r = exception.getResponse();
			return Response.status(r.getStatus()).entity(r.getEntity()).build();
		}
	}
}
