package ContactList;

import java.sql.SQLException;
import java.util.List;

import clients.ContactClient;
import clients.UserClient;
import dao.UserDao;
import entites.UserEntity;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao u= new UserDao();
		UserEntity t1 = new UserEntity("43e45","test","manager");
		//u.InsertUser(t1);
		//u.editUser("43e45", "re");
		//u.deleteUser("er345");
		ContactClient v =new  ContactClient();
		//v.editContact(3, "mary", "mae", 245, 0124, "mar@H");
		UserClient y = new UserClient();
		//y.deleteUser("zahra0");
		y.editUser("43e45", "rsdf", "user");
	
		try {
			List<UserEntity> l= u.getUsers();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
