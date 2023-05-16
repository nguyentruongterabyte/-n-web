package poly.dao;

import poly.entity.Order;
import poly.message.Message;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class OrderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Order get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		return order;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(O.ID) FROM Order_ O");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<Order> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Order> list = query.list();
		return list;
	}
	
	
	public Message save(Order order) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(order);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(Order order) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(order);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Order order= (Order) session.get(Order.class, orderId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(order);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
