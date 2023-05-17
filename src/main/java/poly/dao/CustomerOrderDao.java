package poly.dao;
import poly.entity.CustomerOrder;
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
public class CustomerOrderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomerOrder get(int id) {
		Session session = sessionFactory.getCurrentSession();
		CustomerOrder customerOrder = (CustomerOrder) session.get(CustomerOrder.class, id);
		return customerOrder;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(C.ID) FROM CustomerOrder C");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<CustomerOrder> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CustomerOrder";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<CustomerOrder> list = query.list();
		return list;
	}
	
	
	public Message save(CustomerOrder customerOrder) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(customerOrder);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới đơn đặt hàng thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới đơn đặt hàng thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(CustomerOrder customerOrder) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(customerOrder);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật đơn đặt hàng thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật đơn đặt hàng thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int customerOrderId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerOrder customerOrder= (CustomerOrder) session.get(CustomerOrder.class, customerOrderId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(customerOrder);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá đơn đặt hàng thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá đơn đặt hàng thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
