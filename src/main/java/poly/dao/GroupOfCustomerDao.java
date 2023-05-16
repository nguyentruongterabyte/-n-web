package poly.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poly.entity.GroupOfCustomer;

@Transactional
@Repository
public class GroupOfCustomerDao {
	@Autowired
	private SessionFactory factory;
	
	public List<GroupOfCustomer> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM GroupOfCustomer";
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<GroupOfCustomer> list = query.list();
		return list;
	}
}
