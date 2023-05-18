package poly.dao;

import poly.entity.VendorBill;
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
public class VendorBillDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public VendorBill get(int id) {
		Session session = sessionFactory.getCurrentSession();
		VendorBill vendorBill = (VendorBill) session.get(VendorBill.class, id);
		return vendorBill;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT MAX(V.ID) FROM VendorBill V");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<VendorBill> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM VendorBill";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<VendorBill> list = query.list();
		return list;
	}
	
	
	public Message save(VendorBill vendorBill) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(vendorBill);
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

	public Message update(VendorBill vendorBill) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(vendorBill);
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

	public Message delete(int vendorBillId) {
		Session session = sessionFactory.openSession();
		VendorBill vendorBill= (VendorBill) session.get(VendorBill.class, vendorBillId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(vendorBill);
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
