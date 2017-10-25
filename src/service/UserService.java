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

import dao.UserDao;
import entites.UserEntity;

@Path("/users")
public class UserService {

	@POST
	@Path("/insertUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(UserEntity user) {
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
		// return contacts;

	}

	@PUT
	@Path("editUser/{username}")
	@Consumes(MediaType.APPLICATION_JSON) // Access only for the administrator
	public Response updateUser(@PathParam("username") String username, UserEntity user) {
		UserDao userDao = new UserDao();
		int count = userDao.UpdateUser(username, user);
		System.out.println(count);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	// exit user
	@POST
	@Path("/isValid")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isValid_User(UserEntity user) {
		UserDao userDao = new UserDao();
		Response re = null;
		if (userDao.userLoginCheck(user.getUsername(), user.getPassword())) {
			re = Response.status(200).entity("edit is succsesfully").build();
			
		}
		return re;

	}
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON) // Access for all
	public Response editUser(@PathParam("username") String username,UserEntity user){
		UserDao userdao = new UserDao();
	    int count = userdao.editUser(username, user.getPassword());
	    if(count==0){
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(200).entity("edit is succsesfully").build();
	}

	@DELETE
	@Path("/{username}")
	public Response deleteUser(@PathParam("username") String username){
		UserDao userdao = new UserDao();
	    int count = userdao.deleteUser(username);
	    if(count==0){
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.ok().build();
	}	

}
