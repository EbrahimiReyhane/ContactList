package service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ContactDao;
import entites.ContactEntity;

@Path("/contacts")
public class ContactService {
	

	//_______________________show all contacts____________________________
@GET
@Path("/show")
@Produces(MediaType.APPLICATION_JSON)
	public Response getContact() throws SQLException {
			ContactDao contactDao = new ContactDao();
			List<ContactEntity> contacts = contactDao.getContacts();
			System.out.println(contacts.size());
			return Response.status(200).entity(contacts).build();
			//return contacts;

		}
//@GET
//@Path("{id}")
//@Produces(MediaType.APPLICATION_JSON)
//public Response search(@PathParam("id") int id){
//	ContactDao contactDao = new ContactDao();
//	 ContactEntity contact = contactDao.search(id);
//	 return Response.status(200).entity(contact).build();
//}

//__________________________add new contact___________________
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response addContact(ContactEntity contact  ){
		ContactDao contactDao = new ContactDao();
		contactDao.InsertContact(contact); 
		       return Response.ok().build();
		      
		    }

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(@PathParam("id") int id, ContactEntity contact){
		ContactDao contactDao = new ContactDao();
	    int count = contactDao.UpdateContact(id, contact);
	    if(count==0){
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(200).entity("edit is succsesfully").build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteContact(@PathParam("id") int id){
		ContactDao contactDao = new ContactDao();
	    int count = contactDao.DeleteContact(id);
	    if(count==0){
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.ok().build();
	}	
}
