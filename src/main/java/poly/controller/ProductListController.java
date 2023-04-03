package poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.dao.ProductDao;
import poly.entity.Product;


@Controller
@RequestMapping("/")
public class ProductListController {
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("danh-sach")
	public String showList(ModelMap model) {
	
		List<Product> list = productDao.getAll();
		model.addAttribute("product", new Product());
		model.addAttribute("products", list);
		return "productList";
	}
	
	
	
}
