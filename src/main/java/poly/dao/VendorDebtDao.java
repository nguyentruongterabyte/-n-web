package poly.dao;

import poly.entity.VendorDebt;
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
public class VendorDebtDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public VendorDebt get(int id) {
		Session session = sessionFactory.getCurrentSession();
		VendorDebt vendorDebt = (VendorDebt) session.get(VendorDebt.class, id);
		return vendorDebt;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT MAX(V.ID) FROM VendorDebt V");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<VendorDebt> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM VendorDebt";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<VendorDebt> list = query.list();
		return list;
	}
	
	
	public Message save(VendorDebt vendorDebt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(vendorDebt);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới phiếu nợ thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới phiếu nợ thất bại");
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(VendorDebt vendorDebt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(vendorDebt);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật phiếu nợ thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật phiếu nợ thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int vendorDebtId) {
		Session session = sessionFactory.getCurrentSession();
		VendorDebt vendorDebt= (VendorDebt) session.get(VendorDebt.class, vendorDebtId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(vendorDebt);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá phiếu nợ thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá phiếu nợ thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
