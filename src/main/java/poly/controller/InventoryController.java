package poly.controller;  


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.InventoryDao;
import poly.dao.ProductDao;
import poly.entity.Inventory;
import poly.entity.InventoryCapability;
import poly.entity.Product;
 
@Controller
@RequestMapping("/kho-hang")
public class InventoryController {
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private ProductDao productDao;
	private Inventory inventoryTemplate;
	
	@RequestMapping("tao-moi")
	public String addInventory(ModelMap model) {
		int maxId = inventoryDao.getMaxId();
		Inventory inventory = new Inventory();
		inventory.setId(maxId + 1);
		
		List<Product> products = productDao.getAll();		
		Collection<InventoryCapability> inventoryCapability = inventory.getInventoryCapability();
		
		inventory.setInventoryCapability(inventoryCapability);
		inventoryTemplate = inventory;
		
		model.addAttribute("inventory", inventory);
		model.addAttribute("products", products);
		
		
		
		return "addInventory";
	}
	
//	@RequestMapping("tao-moi/them-san-pham") 
//	public String addProduct(ModelMap model, @RequestParam(value = "id") String id,
//							@RequestParam(value = "maxCount") String maxCount,
//							@RequestParam(value = "last") String last,
//							@RequestParam(value = "currentCount") String currentCount
//							) {
//		Collection<InventoryCapability> inventoryCapability = inventoryTemplate.getInventoryCapability();
//		Product product = productDao.get(Integer.parseInt(id));
//		inventoryCapability.add(new InventoryCapability(inventoryTemplate, product, Integer.parseInt(maxCount), Integer.parseInt(last), Integer.parseInt(currentCount)));
//		model.addAttribute("inventory", inventoryTemplate);
//		model.addAttribute("products", productDao.getAll());
//		return "redirect:/kho-hang/tao-moi.htm";
//	}
}
