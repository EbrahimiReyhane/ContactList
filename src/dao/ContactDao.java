package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entites.ContactEntity;



public class ContactDao {
	
//________________________add new  contact________________________________
	
	public void InsertContact(ContactEntity new_contact){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(new_contact);
		tx.commit();
		session.close();
	}
//	public void InsertContact(Session session,ContactEntity new_contact){
//		session.save(new_contact);
//	}
	
//_____________________show all contacts______________________________________
	
  public List<ContactEntity> getContacts()throws SQLException {
	  Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Object[][] contact = null;
		List<ContactEntity> contacts=new ArrayList<ContactEntity>();
	  try{	
//	 String hql = "FROM ContactEntity";
//		Query query = session.createQuery(hql);
//		List contacts = query.list();
	 Criteria criteria = session.createCriteria(ContactEntity.class);
		 List result = criteria.list();
		 Iterator itr = result.iterator();
			while (itr.hasNext()) {
				ContactEntity count = (ContactEntity) itr.next();
				contacts.add(count);
			}
		tx.commit();

//		for(int i=0;i<contacts.size();i++){
//			contact[i][0]=contacts.get(i).getId();
//			contact[i][1]=contacts.get(i).getName();
//			contact[i][2]=contacts.get(i).getFamily();
//			contact[i][3]=contacts.get(i).getEmail();
//			contact[i][4]=contacts.get(i).getMobile();
//			contact[i][5]=contacts.get(i).getTel();
//
//		}

	}catch (HibernateException e) {
		if (tx!=null) tx.rollback();
		e.printStackTrace(); 
	}finally {
		session.close(); 
	}
		return contacts;
	}
 //____________________delete contact________________________________________
 
 public int DeleteContact(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String state = "delete from ContactEntity where id = :id";
		Query query = session.createQuery(state);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
 //_______________________ edit a contact_______________________________________
 
 public int UpdateContact(int id, ContactEntity contact) {
		if (id <= 0)
			return 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update ContactEntity set  name = :Name , family =  :family, tel = :tel,"
				+ " mobile = :mobile , email = :email where id = :id";

		Query query = session.createQuery(hql);
		query.setInteger("id",id);
		query.setString("Name", contact.getName());
		query.setString("email",contact.getEmail());
		query.setString("family",contact.getFamily());
		query.setInteger("tel", contact.getTel());
		query.setInteger("mobile",contact.getMobile());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
 //_____________________________________search a contact_______________________
 
}
