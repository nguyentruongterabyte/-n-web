package poly.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.ProductDao;
import poly.entity.Product;

@Transactional
@Controller
public class HomeController {
	@Autowired
	ProductDao productDao;
	@RequestMapping(value = {"/", "trang-chu"})
	public String index(
			ModelMap model, 
			@RequestParam(value="productPage",required = false) String productPage
			) {
		int total = 4;
		if (productPage != null) {
			int productPageInt = Integer.parseInt(productPage);
			productPageInt = (productPageInt - 1) * total + 1; 
			List<Product> products = productDao.getByPage(productPageInt, total);
			model.addAttribute("products", products);
		} 
		
		else {
			List<Product> products = productDao.getByPage(1, total);
			model.addAttribute("products", products);
		}
		return "index";
	}
}
