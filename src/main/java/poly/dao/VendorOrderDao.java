package poly.dao;

import poly.entity.VendorOrder;
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
public class VendorOrderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public VendorOrder get(int id) {
		Session session = sessionFactory.getCurrentSession();
		VendorOrder vendorOrder = (VendorOrder) session.get(VendorOrder.class, id);
		return vendorOrder;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(V.ID) FROM VendorOrder V");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<VendorOrder> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM VendorOrder";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<VendorOrder> list = query.list();
		return list;
	}
	
	
	public Message save(VendorOrder vendorOrder) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(vendorOrder);
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

	public Message update(VendorOrder vendorOrder) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(vendorOrder);
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

	public Message delete(int vendorOrderId) {
		Session session = sessionFactory.getCurrentSession();
		VendorOrder vendorOrder= (VendorOrder) session.get(VendorOrder.class, vendorOrderId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(vendorOrder);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá đơn dặt hàng thành công");

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
