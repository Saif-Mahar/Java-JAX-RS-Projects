package com.shashi.customer.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashi.customer.model.Customer;
import com.shashi.customer.model.HeaderInfo;
import com.shashi.customer.service.CustomerService;

		// This is the Resource Class for JAX-RS where we are defining all the HTTP Mehtods 


@Service
@Path("/customer")
public class CustomerResource {

	// Injecting Customer Serivce Dependency 
	@Autowired
	private CustomerService cs;

	// Normal GET method 
	@GET
	public Response health() {
		return Response.ok().build();
	}

	// POST Method of HTTP to create the Customer
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response create(final Customer customer) {
		final Customer entity = cs.create(customer);
		return Response.ok().entity(entity).build();
	}
	
	// PUT method of HTTP to modify the Customer 
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response modify(final Customer customer) {
		cs.modify(customer);
		return Response.noContent().type(MediaType.APPLICATION_JSON).build();
	}

	// GET method of HTTP to get the specific  Customer information by ID
	@Path("/{customerId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@PathParam("customerId") final Long id) {
		final Customer entity = cs.find(id);
		return Response.ok().entity(entity).build();
	}
	
	// DELETE method of the HTTP to delete the Customer 
	@Path("/{customerId}")
	@DELETE
	public Response removeById(@PathParam("customerId") final Long id) {
		cs.remove(id);
		return Response.noContent().build();
	}

	// GET method of HTTP to get List of all the  Customers 
	@Path("/all")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() {
		final List<Customer> customers = cs.getAll();
		final GenericEntity<List<Customer>> entity = new GenericEntity<>(customers, List.class);
		return Response.ok().entity(entity).build();
	}

	// GET method of the HTTP to Search the Customer by First and Last Name
	@Path("/search")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response search(@QueryParam("fName") final String firstName, @QueryParam("lName") final String lastName) {
		final List<Customer> customers = cs.search(firstName, lastName);
		final GenericEntity<List<Customer>> entity = new GenericEntity<>(customers, List.class);
		return Response.ok().entity(entity).build();
	}

	// Get method with @MatrixParam 
	@Path("/matrix")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response searchByMatrix(@MatrixParam("fName") final String firstName,
			@MatrixParam("lName") final String lastName) {
		final List<Customer> customers = cs.search(firstName, lastName);
		final GenericEntity<List<Customer>> entity = new GenericEntity<>(customers, List.class);
		return Response.ok().entity(entity).build();
	}

	// Get method with @HeaderParam
	@Path("/hdr")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHdrValues(@HeaderParam("auth-token") String authToken, @HeaderParam("app-name") String appName,
			@HeaderParam("app-version") String appVersion, @HeaderParam("content-type") String contentType) {
		HeaderInfo entity = HeaderInfo.builder().appName(appName).appVersion(appVersion).authToken(authToken)
				.contentType(contentType).build();
		return Response.ok().entity(entity).build();
	}

	 
	// Get method with @Context to send the Header as an Object
	@Path("/hdrContext")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHdrByContext(@Context HttpHeaders header) {
		String appName = header.getHeaderString("app-name");
		String appVersion = header.getHeaderString("app-version");
		String authToken = header.getHeaderString("auth-token");
		String contentType = header.getHeaderString("content-type");
		HeaderInfo entity = HeaderInfo.builder().appName(appName).appVersion(appVersion).authToken(authToken)
				.contentType(contentType).build();
		return Response.ok().entity(entity).build();
	}

}
