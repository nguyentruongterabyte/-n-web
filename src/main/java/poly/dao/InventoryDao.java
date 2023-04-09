package poly.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poly.entity.Inventory;

@Transactional
@Repository
public class InventoryDao {
	@Autowired
	private SessionFactory factory;
	
	public List<Inventory> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Inventory";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Inventory> list = query.list();
		
		return list;
	}
	
	public Inventory get(int id) {
		Session session = factory.getCurrentSession();
		Inventory i = (Inventory) session.get(Inventory.class, (Integer) id);
		return i;
	}
	
	public int getMaxId() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT max(i.id) FROM Inventory i");
		
		int maxId = (int) query.uniqueResult();
		return maxId;
	}
	
	public String save(Inventory inventory) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String message = "";
		try {
			session.save(inventory);
			t.commit();
			message = "Thêm mới kho hàng thành công!";
		} catch (Exception e) {
			t.rollback();
			return "Thêm mới thất bại!";
		} finally {
			session.close();
		}
		return message;
	}
}
