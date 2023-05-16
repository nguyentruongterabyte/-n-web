package poly.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import poly.entity.Customer;
import poly.message.Message;
import java.util.List;

@Repository
public class CustomerDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Customer get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, (Integer) id);
		return customer;
	}

	public List<Customer> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Customer";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Customer> list = query.list();
		return list;
	}

	public Message save(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(customer);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới khách hàng thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới khách hàng thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(customer);
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

	public Message delete(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, customerId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(customer);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá khách hàng thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá khách hàng thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}