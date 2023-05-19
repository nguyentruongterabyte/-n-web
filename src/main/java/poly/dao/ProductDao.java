package poly.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import poly.entity.Product;
import poly.message.Message;


@Transactional
@Repository
public class ProductDao {
	@Autowired
	private SessionFactory factory;
	
	public Long getLength() {
		String hql = "SELECT COUNT(*) FROM Product";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	public List<Product> getByPage(int pageId, int total) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		query.setFirstResult(pageId - 1);
		query.setMaxResults(total);
		@SuppressWarnings("unchecked")
		List<Product> list = query.list();
		return list;
	}
	
	public int getMaxId() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT max(p.id) FROM Product p");
		if (query.uniqueResult() == null)
			return 0;
		int maxId = (int) query.uniqueResult();
		return maxId;
	}
	
	public List<Product> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Product> list = query.list();

		return list;
	}
	
	public Message save(Product product) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		try {
			session.save(product);
			t.commit();
			message.setType("success");
			message.setContent("Thêm mới sản phẩm thành công!");
			
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
	
	public Message update(Product product) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		try {
			session.update(product);
			t.commit();
			message.setType("success");
			message.setContent("Cập nhật sản phẩm thành công!");
			
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
	
	public Message delete(int productId) {
		Product p = this.get(productId);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Message message = new Message();
		if (p.getInventoryCapabilities().toArray().length > 0) {
			message.setType("error");
			message.setContent("Sản phẩm đã có trong kho hàng. Xóa thất bại!");
			return message;
		}
		
		if (p.getOrderDetails().toArray().length > 0) {
			message.setType("error");
			message.setContent("Sản phẩm đã tồn tại đơn hàng. Xóa thất bại!");
		}
		try {
			p.setInventoryCapabilities(null);
			p.setOrderDetails(null);
			session.delete(p);
			t.commit();
			message.setType("success");
			message.setContent("Xóa sản phẩm thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			message.setType("error");
			message.setContent("Xóa sản phẩm thất bại!");
		} finally {
			session.close();
		}
		return message;
	}
	
	public Product get(int id) {
		Session session = factory.getCurrentSession();
		Product p = (Product) session.get(Product.class,(Integer) id);
		return p;
	}
}
