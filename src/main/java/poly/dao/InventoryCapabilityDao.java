package poly.dao;

import java.util.Collection;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poly.entity.InventoryCapability;

@Transactional
@Repository
public class InventoryCapabilityDao {
	@Autowired
	private SessionFactory factory;
	public String save(InventoryCapability inventoryCapability) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String message = "";
		try {
			session.save(inventoryCapability);
			t.commit();
			message = "Thêm mới sức chứa kho hàng thành công!";
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return "Thêm mới thất bại!";
		} finally {
			session.close();
		}
		return message;
	}
	
	public void saveList(Collection<InventoryCapability> list) {
		for (InventoryCapability inventoryCapability : list) {
			save(inventoryCapability);
		}
	}
	
}
