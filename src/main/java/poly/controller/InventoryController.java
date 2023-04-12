package poly.controller;  


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.InventoryCapabilityDao;
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
	@Autowired
	private InventoryCapabilityDao inventoryCapabilityDao;
	
	@RequestMapping("danh-sach")
	public String showList(@RequestParam(value="id", required = false) String id, ModelMap model) {		
		List<Inventory> list = inventoryDao.getAll();
		model.addAttribute("inventories", list);
		if (id == null) {
			model.addAttribute("inventory", new Inventory());
		} else {
			Inventory i = inventoryDao.get(Integer.parseInt(id));
			if (i == null) {
				model.addAttribute("messageType", "error");
				model.addAttribute("message", "Không thể lấy được thông tin kho hàng!");
				return "inventoryList";
			}
			model.addAttribute("inventory", i);
		}
		return "inventoryList";
	}
	
	@RequestMapping("tao-moi")
	public String addInventory(ModelMap model) {
		int maxId = inventoryDao.getMaxId();
		Inventory inventory = new Inventory();
		inventory.setId(maxId + 1);
		
		List<Product> products = productDao.getAll();		
		Collection<InventoryCapability> inventoryCapability = inventory.getInventoryCapability();
		
		inventory.setInventoryCapability(inventoryCapability);
		
		model.addAttribute("pageType", "add");
		model.addAttribute("inventory", inventory);
		model.addAttribute("products", products);
		
		
		return "addInventory";
	}
	
	
	@RequestMapping(value = "chinh-sua")
	public String edit(ModelMap model, 
			RedirectAttributes redirectAttributes,
			@RequestParam(value="id") String id) {
		Inventory inventory = inventoryDao.get(Integer.parseInt(id));
		if (inventory == null) {
			redirectAttributes.addFlashAttribute("messageType", "error");
			redirectAttributes.addFlashAttribute("message", "Không thể lấy được thông tin kho hàng!");
			return "redirect:danh-sach.htm";
		}
		List<Product> products = productDao.getAll();
		for (Product product : products) {
		
		}
		
		model.addAttribute("pageType", "edit");
		model.addAttribute("products", products);
		model.addAttribute("inventory", inventory);
		return "addInventory";
	}
	
	@RequestMapping(value = "xac-thuc", method = RequestMethod.POST)
	public String validate(ModelMap model,
							RedirectAttributes redirectAttributes, 
							@ModelAttribute("inventory") Inventory inventory,
							@RequestParam(value = "pageType") String pageType,
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
		model.addAttribute("pageType", pageType);
		model.addAttribute("products", products);
		model.addAttribute("inventory", inventory);
		if (errors.hasErrors()) {
			model.addAttribute("messageType", "error");	
			model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
		} else {
			if (pageType.equals("add")) {				
				String message = inventoryDao.save(inventory);
				if ("Thêm mới kho hàng thành công!".equals(message)) {
					/*
				// Test xem message có đúng như trong inventoryDao không
				System.out.println("equal");
					 */
					redirectAttributes.addFlashAttribute("messsageType", "success");
					redirectAttributes.addFlashAttribute("inventory", inventory);
					inventoryCapabilityDao.saveList(inventoryCapability);
				} else if ("Thêm mới thất bại!".equals(message)) {
					//System.out.println("equal");
					redirectAttributes.addFlashAttribute("messageType", "error");
				}
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/kho-hang/danh-sach.htm";
			} else if (pageType.equals("edit")) {
				String message = inventoryDao.update(inventory);
				if ("Cập nhật kho hàng thành công!".equals(message)) {
					redirectAttributes.addFlashAttribute("messageType", "success");
					redirectAttributes.addFlashAttribute("inventory", inventory);
					inventoryCapabilityDao.saveList(inventoryCapability);
				} else if ("Cập nhật thất bại!".equals(message)) {
					redirectAttributes.addAttribute("messageType", "error");
				}
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/kho-hang/danh-sach.htm";
			}
		}
		
		return "addInventory";
	}
}
