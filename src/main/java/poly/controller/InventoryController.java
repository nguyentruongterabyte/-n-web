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
		model.addAttribute("inventory", inventory);
		
		List<Product> list = productDao.getAll();
		Product product = list.get(0);
		InventoryCapability inventoryCapability = new InventoryCapability(inventory, product, 0, 0, 0);
		
//		System.out.println(inventoryCapability.getEmbeddedId().getProduct().getName());
//		System.out.println(inventoryCapability.getMaxCount());
		
		
		return "addInventory";
	}
}
