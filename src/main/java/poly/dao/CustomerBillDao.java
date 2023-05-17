package poly.dao;
import poly.entity.CustomerBill;
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
public class CustomerBillDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomerBill get(int id) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBill customerBill = (CustomerBill) session.get(CustomerBill.class, id);
		return customerBill;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(C.ID) FROM CustomerBill C");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<CustomerBill> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CustomerBill";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<CustomerBill> list = query.list();
		return list;
	}
	
	
	public Message save(CustomerBill customerBill) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(customerBill);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới hoá đơn thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới hoá đơn thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(CustomerBill customerBill) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(customerBill);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật hoá đơn thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật hoá đơn thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int customerBillId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBill customerBill= (CustomerBill) session.get(CustomerBill.class, customerBillId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(customerBill);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá hoá đơn thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá hoá đơn thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
