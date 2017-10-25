package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ContactDao;
import dao.UserDao;
import entites.ContactEntity;
import entites.UserEntity;





@Path("/system")
public class Service {

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


@POST
@Path("/insert")
@Consumes(MediaType.APPLICATION_JSON)
 public Response addContact(ContactEntity contact  ){
	ContactDao contactDao = new ContactDao();
	contactDao.InsertContact(contact); 
	       return Response.ok().build();
	      
	    }

@POST
@Path("/insertUser")
@Consumes(MediaType.APPLICATION_JSON)
 public Response addUser(UserEntity user  ){
	UserDao userDao = new UserDao();
	userDao.InsertUser(user);
	       return Response.ok().build();
	      
	    }
@GET
@Path("/showuser")
@Produces(MediaType.APPLICATION_JSON)
	public Response getUser() throws SQLException {
		UserDao userDao = new UserDao();
		List<UserEntity> users = userDao.getUsers();
		System.out.println(users.size());
		return Response.status(200).entity(users).build();
		//return contacts;

	}
@PUT
@Path("editUser/{username}")
@Consumes(MediaType.APPLICATION_JSON)//edit user just for manager
public Response updateUser(@PathParam("username") String username, UserEntity user){
	UserDao userDao = new UserDao();
    int count = userDao.UpdateUser(username, user);
    System.out.println(count);
    if(count==0){
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().build();
}
//exit user
@POST
@Path("/isValid")
@Consumes(MediaType.APPLICATION_JSON)
 public Response isValid_User(UserEntity user  ){
	UserDao userDao = new UserDao();
	Response re=null;
	
	if(userDao.userLoginCheck(user.getUsername(), user.getPassword())){
		 re= Response.ok().build();
		 System.out.println("user is valid");
	};
	return re;
	      
	    }

}