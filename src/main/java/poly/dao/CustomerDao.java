package poly.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poly.entity.Customer;
import poly.message.Message;


@Transactional
@Repository
public class CustomerDao {
	@Autowired 
	private SessionFactory factory;
	
	public int getMaxId() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT max(c.id) FROM Customer c");
		
		if (query.uniqueResult() == null) {
			return 0;
		}
		int maxId = (int) query.uniqueResult();
		return maxId;
	}
	
	public List<Customer> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Customer";
		Query query = session.createQuery(hql);
		

		@SuppressWarnings("unchecked")
		List<Customer> list = query.list();
		return list;
	}
	
	public Message save(Customer customer) {
		Session session = factory.openSession();
		Transaction t =  session.beginTransaction();
		Message message = new Message();
		
		try {
			session.save(customer);
			t.commit(); 
			message.setType("success");
			message.setContent("Thêm mới khách hàng thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Thêm mới thất bại!" + e);

		} finally {
			session.close();
		}
		return message;
	}

	
	public Message update(Customer customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		
		try {
			session.update(customer);
			t.commit();
			message.setType("success");
			message.setContent("Cập nhật thông tin khách hàng thành công!");
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

	public Customer get(int id) {
		Session session = factory.getCurrentSession();
		Customer c = (Customer) session.get(Customer.class, (Integer) id);
		return c;
	}
	
	public Message delete(int id) {
		Customer c = this.get(id);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		if (c.getCustomerBills().toArray().length > 0) {
			message.setType("error");
			message.setContent("Khách hàng đã có hóa đơn mua hàng! Xóa thất bại");
			return message;
		}
		if (c.getCustomerDebts().toArray().length > 0) {
			message.setType("error");
			message.setContent("Khách hàng còn phiếu nợ! Xóa thất bại");
			return message;
		}
		if (c.getCustomerOrders().toArray().length > 0) {
			message.setType("error");
			message.setContent("Khách hàng đã có đơn đặt hàng! Xóa thất bại");
		}
		try {
			c.setCustomerBills(null);
			c.setCustomerDebts(null);
			c.setCustomerOrders(null);
			
			session.delete(c);
			t.commit();
			message.setType("success");
			message.setContent("Xóa khách hàng thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Xóa sản phẩm thất bại!");
		} finally {
			session.close();
		}
		return message;
	}
}

