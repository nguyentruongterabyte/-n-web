package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
