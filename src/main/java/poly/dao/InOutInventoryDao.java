package poly.dao;
import poly.entity.InOutInventory;
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
public class InOutInventoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public InOutInventory get(int id) {
		Session session = sessionFactory.getCurrentSession();
		InOutInventory inOutInventory = (InOutInventory) session.get(InOutInventory.class, id);
		return inOutInventory;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT MAX(I.ID) FROM INOUTINVENTORY I");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<InOutInventory> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM INOUTINVENTORY";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<InOutInventory> list = query.list();
		return list;
	}
	
	
	public Message save(InOutInventory inOutInventory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(inOutInventory);
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

	public Message update(InOutInventory inOutInventory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(inOutInventory);
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

	public Message delete(int inOutInventoryId) {
		Session session = sessionFactory.openSession();
		InOutInventory inOutInventory= (InOutInventory) session.get(InOutInventory.class, inOutInventoryId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(inOutInventory);
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
