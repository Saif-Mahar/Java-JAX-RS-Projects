package com.shashi.customer.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.shashi.customer.exception.CustomerNotFoundException;
import com.shashi.customer.model.ErrorInfo;

/*This is an other  JAX-RS Customer Exception Mapper Provider for JAX-RS. Note:  we can have multiple Mapper
Provider in one Project  */

@Component
@Provider
public class CustomerNotFoundHandler implements ExceptionMapper<CustomerNotFoundException> {

	@Override
	public Response toResponse(CustomerNotFoundException exception) {
		ErrorInfo entity = ErrorInfo.builder().errCode("ERR-201").errMessage(exception.getMessage()).build();
		return Response.status(404).entity(entity).build();
	}

}
