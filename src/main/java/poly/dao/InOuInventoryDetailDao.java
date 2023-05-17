package poly.dao;
import poly.entity.InOutInventoryDetail;
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
public class InOuInventoryDetailDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public InOutInventoryDetail get(int id) {
		Session session = sessionFactory.getCurrentSession();
		InOutInventoryDetail inOutInventoryDetail = (InOutInventoryDetail) session.get(InOutInventoryDetail.class, id);
		return inOutInventoryDetail;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT MAX(I.ID) FROM InOutInventoryDetail I");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<InOutInventoryDetail> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM InOutInventoryDetail";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<InOutInventoryDetail> list = query.list();
		return list;
	}
	
	
	public Message save(InOutInventoryDetail inOutInventoryDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(inOutInventoryDetail);
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

	public Message update(InOutInventoryDetail inOutInventoryDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(inOutInventoryDetail);
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

	public Message delete(int inOutInventoryDetailId) {
		Session session = sessionFactory.openSession();
		InOutInventoryDetail inOutInventoryDetail= (InOutInventoryDetail) session.get(InOutInventoryDetail.class, inOutInventoryDetailId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(inOutInventoryDetail);
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
