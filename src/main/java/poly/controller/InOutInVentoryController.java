package poly.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.entity.CustomerOrder;
import poly.entity.Inventory;

@Controller
@RequestMapping("don-nhap-xuat-kho")
public class InOutInVentoryController {
	
	@RequestMapping("them-moi")
	public String add(
			ModelMap model,
			HttpSession session
			) {
		@SuppressWarnings("unchecked")
		List<Inventory> inventories = (List<Inventory>) session.getAttribute("inventories");
		CustomerOrder customerOrder = (CustomerOrder) session.getAttribute("customerOrder");
		model.addAttribute("customerOrder", customerOrder);
		model.addAttribute("inventories", inventories);
		
		return "addInOutInventory";
	}
	
	
	
}
