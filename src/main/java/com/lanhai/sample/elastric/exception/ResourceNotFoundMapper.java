package com.lanhai.sample.elastric.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.lanhai.sample.elastric.dto.BaseDTO;

/**
 * status 404
 * 
 * @author Nick
 *
 */
@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<NotFoundException>
{

	@Override
	public Response toResponse(NotFoundException exception)
	{
		return Response.status(Response.Status.NOT_FOUND).entity(new BaseDTO(1)).build();
	}

}
