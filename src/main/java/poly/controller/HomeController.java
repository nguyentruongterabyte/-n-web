package poly.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dao.ProductDao;
import poly.entity.Product;

@Transactional
@Controller
public class HomeController {
	@Autowired
	ProductDao productDao;
	@RequestMapping(value = {"/", "Home"})
	public String index(
			ModelMap model
			) {
		List<Product> products = productDao.getAll();
		int productPagesQuantity = products.toArray().length;
		System.out.println(productPagesQuantity);
		model.addAttribute("products", products);
		return "index";
	}
}
