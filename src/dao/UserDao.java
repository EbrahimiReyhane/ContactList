package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entites.UserEntity;

public class UserDao {
	private UserEntity new_user = new UserEntity();

	// ________________________add new user________________________________

	public void InsertUser(UserEntity user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String password_new = stringToMD5(user.getPassword());
		new_user.setUsername(user.getUsername());
		new_user.setPassword(password_new);
		new_user.setRole(user.getRole());
		try {
			session.save(new_user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// _____________________show all users______________________________________

	public List<UserEntity> getUsers() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Object[][] contact = null;
		List<UserEntity> users = new ArrayList<UserEntity>();
		try {
			// String hql = "FROM ContactEntity";
			// Query query = session.createQuery(hql);
			// List contacts = query.list();
			Criteria criteria = session.createCriteria(UserEntity.class);
			List result = criteria.list();
			Iterator itr = result.iterator();
			while (itr.hasNext()) {
				UserEntity count = (UserEntity) itr.next();
				users.add(count);
			}
			tx.commit();

			// for(int i=0;i<contacts.size();i++){
			// contact[i][0]=contacts.get(i).getId();
			// contact[i][1]=contacts.get(i).getName();
			// contact[i][2]=contacts.get(i).getFamily();
			// contact[i][3]=contacts.get(i).getEmail();
			// contact[i][4]=contacts.get(i).getMobile();
			// contact[i][5]=contacts.get(i).getTel();
			//
			// }

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}
	// ____________________delete user________________________________________

	public int deleteUser(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String state = "delete from UserEntity where username = :username";
		Query query = session.createQuery(state);
		query.setString("username", username);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
	// ________________edit a user just forn manager______________________________

	public int UpdateUser(String username, UserEntity user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update UserEntity set  password = :password ,role = :role  where username = :username";
		String password_new = stringToMD5(user.getPassword());
		Query query = session.createQuery(hql);
		query.setString("username", user.getUsername());
		query.setString("password", password_new);
		query.setString("role", user.getRole());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
	// _____________________________________search a user_______________________

	// ___________________________Exit User___________________________________

	public boolean userLoginCheck(String username, String password){
		Boolean flage = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String password_new=stringToMD5(password);
		try {
	List<UserEntity> theUser =  (List<UserEntity>) session
			.createQuery("from UserEntity").getResultList();
	tx.commit();
	for(UserEntity user:theUser){
       if((username==user.getUsername()) &&(password_new==user.getPassword())){
			
			 flage=true;
	}
	
	}
		
	
	
} catch (Exception e) {
	// TODO: handle exception
	return false;
}
		return flage;
	}
	// ____________________________________ String to MD5______________________

	public String stringToMD5(String password){
	  MessageDigest digest = null;
	try {
		digest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		digest.update(password.getBytes());
		byte hash[]=digest.digest();
		BigInteger bigInt = new BigInteger(1,hash);
		String hashtext = bigInt.toString(16);
		//System.out.println(hashtext);
		return hashtext;
	}
//_______________________________ edit user for users__________________
		
		 public int editUser(String username,String password){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update UserEntity set  password = :password   where username = :username";
			String password_new=stringToMD5(password);
			Query query = session.createQuery(hql);
			query.setString("username", username);
			query.setString("password",password_new);
			int rowCount = query.executeUpdate();
			System.out.println("Rows affected: " + rowCount);
			tx.commit();
			session.close();
			return rowCount;
		}
  }


