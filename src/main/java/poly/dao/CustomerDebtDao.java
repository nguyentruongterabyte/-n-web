package poly.dao;

import poly.entity.CustomerDebt;
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
public class CustomerDebtDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomerDebt get(int id) {
		Session session = sessionFactory.getCurrentSession();
		CustomerDebt customerDebt = (CustomerDebt) session.get(CustomerDebt.class, id);
		return customerDebt;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(C.ID) FROM CustomerDebt C");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<CustomerDebt> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CustomerDebt";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<CustomerDebt> list = query.list();
		return list;
	}
	
	
	public Message save(CustomerDebt customerDebt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(customerDebt);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới đơn ghi nợ thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới đơn ghi nợ thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(CustomerDebt customerDebt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(customerDebt);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật đơn ghi nợ thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật đơn ghi nợ thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int customerDebtId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerDebt customerDebt= (CustomerDebt) session.get(CustomerDebt.class, customerDebtId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(customerDebt);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá đơn ghi nợ thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá đơn ghi nợ thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
