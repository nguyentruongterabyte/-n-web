package poly.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dao.ProductDao;
import poly.entity.Product;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("/san-pham")
public class ProductController {
	@Autowired
	private ProductDao productDao;
	// RequestMapping
	@RequestMapping("danh-sach")
	public String showList(ModelMap model) {
		List<Product> list = productDao.getAll();
		model.addAttribute("products", list);
		return "productList";
	}
	
	@RequestMapping(value = "xac-thuc", method = RequestMethod.POST)
	public String validate(ModelMap model, @ModelAttribute("product") @Validated Product product,
			BindingResult errors) {
		if (product.getInPrice() > product.getOutPrice()) {
			errors.rejectValue("inPrice", "product", "Giá nhập phải nhỏ hơn giá bán!");
		} 
		
		if (errors.hasErrors()) {
			Message message = new Message("error","Vui lòng sửa những lỗi sau đây!");
			model.addAttribute("message", message);
		} else {
			Message message = productDao.update(product);
			model.addAttribute("message", message);
			model.addAttribute("products", productDao.getAll());
			return "productList";
		}
		return "productEdit";
	}
	
	@RequestMapping(value = "/{id}", params = "lnkEdit")
	public String edit(ModelMap model, @PathVariable("id") int id) {
		Product product = productDao.get(id);
		model.addAttribute("product", product);
		product.getId();
		return "productEdit";
	}
}
