package com.shashi.customer.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.shashi.customer.model.ErrorInfo;

/*This is an other  JAX-RS Customer Exception Mapper Provider for JAX-RS. Note:  we can have multiple Mapper
Provider in one Project  */


@Component
@Provider
public class GenericExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ErrorInfo entity = ErrorInfo.builder().errCode("ERR-1000").errMessage("Error occurred processing your request")
				.build();
		return Response.serverError().entity(entity).build();
	}

}
