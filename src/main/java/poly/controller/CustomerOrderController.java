package poly.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.InventoryDao;
import poly.entity.Customer;
import poly.entity.CustomerOrder;
import poly.entity.Inventory;

@Controller
@RequestMapping("don-ban-hang")
public class CustomerOrderController {
	@Autowired
	InventoryDao inventoryDao;
	
	
	@RequestMapping("them-moi")
	public String add(
			ModelMap model,
			HttpSession session
			) {
		@SuppressWarnings("unchecked")
		List<Inventory> inventories = (List<Inventory>) session.getAttribute("inventories");
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) session.getAttribute("customers");
		CustomerOrder customerOrder = (CustomerOrder) session.getAttribute("customerOrder");
		
		// Test
//		System.out.println(customerOrder.getDocument().getCreateDate());
		
		model.addAttribute("customers", customers);
		model.addAttribute("customerOrder", customerOrder);
		model.addAttribute("inventories", inventories);
		return "addCustomerOrder";
	}
	@RequestMapping("chon-kho-hang")
	public String selectInventory(
			RedirectAttributes redirectAttributes,
			@RequestParam("id") String id,
			HttpSession session
			) {
		if (id != "") {
			Inventory inventory = inventoryDao.get(Integer.parseInt(id));
			if (inventory != null) {
				redirectAttributes.addFlashAttribute("inventory", inventory);
			}			
		}
		return "redirect:them-moi.htm";
	}
}
