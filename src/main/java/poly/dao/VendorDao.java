package poly.dao;

import poly.entity.Vendor;
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
public class VendorDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Vendor get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Vendor vendor = (Vendor) session.get(Vendor.class, id);
		return vendor;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(V.ID) FROM Vendor V");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<Vendor> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Vendor";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Vendor> list = query.list();
		return list;
	}
	
	
	public Message save(Vendor vendor) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(vendor);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới nhà cung cấp thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới nhà cung cấp thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(Vendor vendor) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(vendor);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật nhà cung cấp thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật nhà cung cấp thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int vendorId) {
		Session session = sessionFactory.getCurrentSession();
		Vendor vendor= (Vendor) session.get(Vendor.class, vendorId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(vendor);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá nhà cung cấp thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá nhà cung cấp thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
