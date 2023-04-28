package poly.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.validation.MessageInterpolator.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dao.ProductDao;
import poly.entity.Product;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("/san-pham")
public class ProductController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	ServletContext context;
	// RequestMapping
	@RequestMapping("danh-sach")
	public String showList(ModelMap model) {
		List<Product> list = productDao.getAll();
		model.addAttribute("products", list);
		return "productList";
	}
	
	@RequestMapping(value = "xac-thuc", method = RequestMethod.POST)
	public String validate(ModelMap model,
			@ModelAttribute("product") @Validated Product product,
			@RequestParam(value = "picture", required = false) MultipartFile picture,
			BindingResult errors) {
		if (product.getInPrice() > product.getOutPrice()) {
			errors.rejectValue("inPrice", "product", "Giá nhập phải nhỏ hơn giá bán!");
		} 
		
		System.out.println(picture.getOriginalFilename());
		
		if (errors.hasErrors()) {
			Message message = new Message("error","Vui lòng sửa những lỗi sau đây!");
			model.addAttribute("message", message);
		} else {
			
			
			model.addAttribute("products", productDao.getAll());
			return "redirect:danh-sach.htm";
		}
		return "addProduct";
	}
	
	@RequestMapping(value = "/{id}", params = "lnkEdit")
	public String edit(ModelMap model, @PathVariable("id") int id) {
		Product product = productDao.get(id);
		model.addAttribute("product", product);
		product.getId();
		return "productEdit";
	}
	
	@RequestMapping(value = "them-moi")
	public String add(ModelMap model) {
		int maxId = productDao.getMaxId();
		Product product = new Product();
		product.setId(maxId + 1);
		product.setPicture("");
		product.setInPrice(0);
		product.setOutPrice(0);
		model.addAttribute(product);
		model.addAttribute("pageType", "add");
		
		return "addProduct";
	}
}

