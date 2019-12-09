package com.shashi.customer.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.shashi.customer.exception.InvalidDataException;
import com.shashi.customer.model.ErrorInfo;

/*This is an other  JAX-RS Customer Exception Mapper Provider for JAX-RS. Note:  we can have multiple Mapper
Provider in one Project  */


@Component
@Provider
public class InvalidDataHandler implements ExceptionMapper<InvalidDataException> {

	@Override
	public Response toResponse(InvalidDataException exception) {
		ErrorInfo entity = ErrorInfo.builder().errCode("ERR-200").errMessage(exception.getMessage()).build();
		return Response.status(Status.BAD_REQUEST).entity(entity).build();
	}

}
