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


@Transactional
@Repository
public class ProductDao {
	@Autowired
	private SessionFactory factory;
	
	public List<Product> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Product> list = query.list();

		return list;
	}
	
	public String save(Product product) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String message = "";
		try {
			session.save(product);
			t.commit();
			message = "Thêm mới sản phẩm mới thành công!";
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return "Thêm mới thất bại!";
		} finally {
			session.close();
		}
		return message;

	}
	
	public String update(Product product) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String message = "";
		try {
			session.update(product);
			t.commit();
			message = "Cập nhật sản phẩm thành công!";
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return "Cập nhật thất bại!";
		} finally {
			session.close();
		}
		return message;

	}
	
	public String delete(int productId) {
		Product p = this.get(productId);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String message = "";
		try {
			session.delete(p);
			t.commit();
			message = "Xóa sản phẩm thành công!";
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return "Xóa sản phẩm thất bại";
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
