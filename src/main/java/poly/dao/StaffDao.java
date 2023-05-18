package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poly.entity.Staff;
import poly.message.Message;

@Transactional
@Repository
public class StaffDao {
	@Autowired 
	private SessionFactory factory;
	
	public int getMaxId() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT max(s.id) FROM Staff s");
		
		if (query.uniqueResult() == null)
			return 0;
		int maxId = (int) query.uniqueResult();
		return maxId;
	}
	
	public List<Staff> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff WHERE Resigned = false";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Staff> list = query.list();
		return list;
	}
	
	public Message save(Staff staff) {
		Session session = factory.openSession();
		Transaction t =  session.beginTransaction();
		Message message = new Message();
		
		try {
			session.save(staff);
			t.commit();
			message.setType("success");
			message.setContent("Thêm mới nhân viên thành công!");
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Thêm mới thất bại!");
		} finally {
			session.close();
		}
		return message;
	}
	
	public Message update(Staff staff) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(staff);
			t.commit();
			message.setType("success");
			message.setContent("Cập nhật thông tin nhân viên thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Cập nhật thất bại!");
		} finally {
			session.close();
		}
		return message;
	}
	
	public Staff get(int id) {
		Session session = factory.getCurrentSession();
		Staff s = (Staff) session.get(Staff.class, (Integer) id);
		return s;
	}
	
	public Message delete(int staffId) {
		Staff s = this.get(staffId);
		Message message = new Message();
		s.setResigned(true);
		message = this.update(s);
		if (message.getType().equals("error")) {
			message.setContent("Xóa nhân viên thất bại!");
		} else {
			message.setType("success");
			message.setContent("Xóa nhân viên thành công!");			
		}
		
		return message;
	}
}
