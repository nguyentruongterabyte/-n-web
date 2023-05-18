package poly.dao;
import poly.entity.Document;
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
public class DocumentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Document get(int id) {
		Session session = sessionFactory.getCurrentSession();
		Document document = (Document) session.get(Document.class, id);
		return document;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT max(d.id) FROM Document d");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<Document> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Document";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Document> list = query.list();
		return list;
		// ----
		
	}
	
	
	public Message save(Document document) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(document);
			transaction.commit();
			message.setType("success");
			message.setContent("Thêm mới đơn thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới đơn thất bại!" + e);
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(Document document) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(document);
			transaction.commit();
			message.setType("success");
			message.setContent("Cập nhật đơn thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Cập nhật đơn thất bại!");
		} finally

		{
			session.close();
		}
		return message;
	}

	public Message delete(int documentId) {
		Session session = sessionFactory.openSession();
		Document document= (Document) session.get(Document.class, documentId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(document);
			transaction.commit();
			message.setType("success");
			message.setContent("Xoá đơn thành công");

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Xoá đơn thất bại!");
		} finally

		{
			session.close();
		}
		return message;
		
	}
}
