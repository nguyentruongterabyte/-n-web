package poly.controller;  


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("tao-moi")
	public String addInventory(ModelMap model) {
		int maxId = inventoryDao.getMaxId();
		Inventory inventory = new Inventory();
		inventory.setId(maxId + 1);
		
		List<Product> products = productDao.getAll();		
		Collection<InventoryCapability> inventoryCapability = inventory.getInventoryCapability();
		
		inventory.setInventoryCapability(inventoryCapability);
		
		model.addAttribute("inventory", inventory);
		model.addAttribute("products", products);
		
		
		return "addInventory";
	}
	

}
