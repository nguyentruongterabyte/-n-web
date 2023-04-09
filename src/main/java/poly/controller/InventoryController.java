package poly.controller;  


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
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
	
	@RequestMapping(value = "tao-moi/xac-thuc")
	public String validate(ModelMap model, @ModelAttribute("inventory") Inventory inventory,
							@RequestParam(value = "productsId[]", required = false) String[] productsId,
							@RequestParam(value = "maxCounts[]", required = false) String[] maxCounts,
							@RequestParam(value = "lasts[]", required = false) String[] lasts,
							@RequestParam(value = "currentCounts[]", required = false) String[] currentCounts,
							BindingResult errors
			) { 
		
		List<Product> products = productDao.getAll();
		
		if (inventory.getName().trim().length() == 0) {
			errors.rejectValue("name", "inventory", "Vui lòng nhập tên kho!");
		}
		
		if (inventory.getAddress().trim().length() == 0) {
			errors.rejectValue("address", "inventory", "Vui lòng nhập địa chỉ của kho!");
		}
		
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
		}
		
		Collection<InventoryCapability> inventoryCapability = new ArrayList<>();
		
		if (productsId != null) {
			
			for (int i = 0; i < productsId.length; i++) {			
				Product product = productDao.get(Integer.parseInt(productsId[i]));
				
				int index = -1;
				for (Product p : products) {
					if (product.getId() == p.getId()) {
						index = products.indexOf(p);
						break;
					}
				}
				if (index != -1) {					
					products.remove(index);
				}

				/*
				// Test xem product length có bị giảm qua mỗi vòng lặp không
				System.out.println(products.toArray().length);
				*/
				int maxCount = Integer.parseInt(maxCounts[i]);
				int last = Integer.parseInt(lasts[i]);
			/* 
 				// Test xem get trong productDao có hoạt động không
				System.out.println("{"
					+ "productId=" + product.getId()
					+ ", productName=" + product.getName()
					+ ", productBarCode=" + product.getBarCode()
					+ "}"); 
			 */
				int currentCount = Integer.parseInt(currentCounts[i]);
				InventoryCapability.Id embeddedId = new InventoryCapability.Id(product, inventory); 
				inventoryCapability.add(new InventoryCapability(embeddedId, maxCount, last, currentCount));
			}
			
		}
		
/*
 		// Test xem inventory.inventoryCapability có hoạt động không
 		for (InventoryCapability ic : inventoryCapability) {
			System.out.println("{"
					+ "productName=" + ic.getEmbeddedId().getProduct().getName()
					+ ", maxCount=" + ic.getMaxCount()
					+ ", last=" + ic.getLast()
					+ ", currentCount=" + ic.getCurrentCount()
					+ "}");
		}
*/	
		inventory.setInventoryCapability(inventoryCapability);
		
		model.addAttribute("products", products);
		model.addAttribute("inventory", inventory);
		return "addInventory";
	}
}
