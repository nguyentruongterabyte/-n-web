package poly.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
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
			HttpSession session,
			@RequestParam(value="productPage",required = false) String productPage
			) {
		
		if (session.getAttribute("user") == null) {
			return "redirect:dang-nhap.htm";
		}
		
		// Số sản phẩm được show lên màn hình chính
		int total = 4;
		Long ProductCount = productDao.getLength();
		if (productPage != null) {
			int productPageInt = Integer.parseInt(productPage);
			productPageInt = (productPageInt - 1) * total + 1; 
			List<Product> products = productDao.getByPage(productPageInt, total);
			
			// Tổng số trang của sản phẩm
			int pageProductTotal =(int) Math.ceil((double) ProductCount / total);
			model.addAttribute("activeProductPage", productPage);
			model.addAttribute("pageProductTotal", pageProductTotal);
			model.addAttribute("products", products);
		} 
		
		else {
			List<Product> products = productDao.getByPage(1, total);
			int pageProductTotal = (int) Math.ceil((double) ProductCount / total);
			model.addAttribute("activeProductPage", 1);
			model.addAttribute("pageProductTotal", pageProductTotal);
			model.addAttribute("products", products);
		}
		return "index";
	}
}
