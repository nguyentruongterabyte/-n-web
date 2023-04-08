package poly.controller;  


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/tao-moi/xac-thuc", method = RequestMethod.POST)
	public String validate(ModelMap model, @ModelAttribute("inventory") @Validated Inventory inventory,
							@RequestParam("productsId[]") String[] productsId,
							@RequestParam("maxCounts[]") String[] maxCounts,
							@RequestParam("lasts[]") String[] lasts,
							@RequestParam("currentCounts[]") String[] currentCounts,
							BindingResult errors
			) { 
		
		
		
		return "addInventory";
	}
}
