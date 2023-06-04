package poly.dao;
import poly.entity.OrderDetail;
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
public class OrderDetailDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public OrderDetail get(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderDetail orderDetail = (OrderDetail) session.get(OrderDetail.class, id);
		return orderDetail;
	}
	
	public int getMaxId(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT MAX(O.ID) FROM OrderDetail O");
		if(query.uniqueResult() == null)
			return 0;
		int maxId = (int)query.uniqueResult();
		return maxId;
	}
	
	public List<OrderDetail> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM OrderDetail";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = query.list();
		return list;
	}
	
	public Message save(OrderDetail orderDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();

		try {
			session.save(orderDetail);
			transaction.commit();
			message.setType("Success");
			message.setContent("Thêm mới thành công");
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			message.setType("error");
			message.setContent("Thêm mới thất bại " + e);
		} finally {
			session.close();
		}
		return message;
	}

	public Message update(OrderDetail orderDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(orderDetail);
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

	public Message delete(int orderDetailId) {
		Session session = sessionFactory.openSession();
		OrderDetail orderDetail= (OrderDetail) session.get(OrderDetail.class, orderDetailId);
		Transaction transaction = session.beginTransaction();
		Message message = new Message();
		try {
			session.delete(orderDetail);
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
