package poly.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import poly.entity.User;
import poly.message.Message;

@Transactional
@Repository
public class UserDao {
	@Autowired 
	private SessionFactory factory;
	
	public User getByEmail(String email) {
		Session session = factory.getCurrentSession();
	    Query query = session.createQuery("FROM User WHERE email = :email");
	    query.setParameter("email", email);
	    User user = (User) query.uniqueResult();
	    return user;
	}
	
	public Message update(User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(user);
			t.commit();
			message.setType("success");
			message.setContent("Cập nhật thông tin tài khoản thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Cập nhật thất bại!");
		} finally {
			session.close();
		}
		return message;
	}
	
	public User get(String username) {
		Session session = factory.getCurrentSession();
		User s = (User) session.get(User.class, username);
		return s;
	}
	
	public Message save(User user) {
		Session session = factory.openSession();
		Transaction t =  session.beginTransaction();
		Message message = new Message();
		
		try {
			session.save(user);
			t.commit();
			message.setType("success");
			message.setContent("Thêm mới tài khoản thành công!");
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Thêm mới thất bại!");
		} finally {
			session.close();
		}
		return message;
	}
}
