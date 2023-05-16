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
import poly.message.Message;
 
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
	public String showList(
			@RequestParam(value="id", required = false) String id, 
			ModelMap model) {		
		List<Inventory> list = inventoryDao.getAll();
		model.addAttribute("inventories", list);
		if (id == null) {
			model.addAttribute("inventory", new Inventory());
		} else {
			Inventory i = inventoryDao.get(Integer.parseInt(id));
			if (i == null) {
				Message message = new Message("error", "Không thể lấy được thông tin kho hàng!");
				model.addAttribute("message", message);
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
		Collection<InventoryCapability> inventoryCapability = inventory.getInventoryCapabilities();
		
		inventory.setInventoryCapabilities(inventoryCapability);
		
		model.addAttribute("pageType", "add");
		model.addAttribute("inventory", inventory);
		model.addAttribute("products", products);
		
		
		return "addInventory";
	}
	
	
	@RequestMapping(value = "xoa")
	public String delete(RedirectAttributes redirectAttributes,
			@RequestParam(value="id") String id) {
		Message message = new Message();
		if (id == null) {
			message.setType("error");
			message.setContent("Lỗi lấy thông tin!");
		} else {
			message = inventoryDao.delete(Integer.parseInt(id));
			if (message.getType().equals("error")) {
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:danh-sach.htm?id=" + id;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:danh-sach.htm";
	}
	
	@RequestMapping(value = "chinh-sua")
	public String edit(ModelMap model, 
			RedirectAttributes redirectAttributes,
			@RequestParam(value="id") String id) {
		Inventory inventory = inventoryDao.get(Integer.parseInt(id));
		if (inventory == null) {
			Message message = new Message("error", "Không thể lấy được thông tin kho hàng!");
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:danh-sach.htm";
		}
		List<Product> products = productDao.getAll();
		// Loại bỏ sản phẩm đã có trong sức chứa kho hàng
		for (InventoryCapability inventoryCapability : inventory.getInventoryCapabilities()) {
			for (Product product : products) {
				if (product.getId() == inventoryCapability.getEmbeddedId().getProduct().getId()) {
					products.remove(product);
					break;
				}
			}
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
		inventory.setInventoryCapabilities(inventoryCapability);
		model.addAttribute("pageType", pageType);
		model.addAttribute("products", products);
		model.addAttribute("inventory", inventory);
		if (errors.hasErrors()) {
			Message message = new Message("error", "Vui lòng sửa những lỗi sau đây!");
 			model.addAttribute("message", message);
		} else {
			if (pageType.equals("add")) {				
				Message message = inventoryDao.save(inventory);
				if (message.getType().equals("success")) {
					/*
					// Test xem message có đúng như trong inventoryDao không
					System.out.println("equal");
					 */
					inventoryCapabilityDao.saveList(inventoryCapability);
				} 
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/kho-hang/danh-sach.htm?id=" + inventory.getId();
			} else if (pageType.equals("edit")) {
				Message message = inventoryDao.update(inventory);
				if (message.getType().equals("success")) {
					
					Inventory inventory2 = inventoryDao.get(inventory.getId());
					if (productsId != null) {		
						// Xóa những sản phẩm trong sức chứa kho hàng không còn trong inventory2
						for (InventoryCapability inventoryCapability2 : inventory2.getInventoryCapabilities()) {
							int index = 0;
							for (String productId : productsId) {
								if (Integer.parseInt(productId) == inventoryCapability2.getEmbeddedId().getProduct().getId()) {
									break;
								}
								index++;
							}							
							
							
							if (index == productsId.length) {
								// Nếu chạy hết productsId mà không có sản phẩm tức là sản phẩm đã bị xóa trên giao diện
								// vì vậy ta sẽ xóa ở trong database
								inventoryCapabilityDao.delete(inventoryCapability2.getEmbeddedId());
							}
						}
						
						// Lưu mới, cập nhật sức chứa kho hàng
						for (int i = 0; i < productsId.length; i++) {
							int index = 0;
							for (InventoryCapability inventoryCapability2 : inventory2.getInventoryCapabilities()) {
								if (inventoryCapability2.getEmbeddedId().getProduct().getId() == Integer.parseInt(productsId[i])) {
									inventoryCapability2.setMaxCount(Integer.parseInt(maxCounts[i]));
									inventoryCapability2.setLast(Integer.parseInt(lasts[i]));
									inventoryCapability2.setCurrentCount(Integer.parseInt(currentCounts[i]));
									inventoryCapabilityDao.update(inventoryCapability2);
									break;
								}
								index++;
							}
							if (index == inventory2.getInventoryCapabilities().toArray().length) {
								Product product = productDao.get(Integer.parseInt(productsId[i]));
								InventoryCapability.Id embeddedId = new InventoryCapability.Id(product, inventory2);
								int maxCount = Integer.parseInt(maxCounts[i]);
								int last = Integer.parseInt(lasts[i]);
								int currentCount = Integer.parseInt(currentCounts[i]);
								inventoryCapabilityDao.save(new InventoryCapability(embeddedId, maxCount, last, currentCount));
							}
						}	
					} else {
						// nếu productsId null thì có nghĩa là người dùng đã xóa hết sản phẩm trong giao diện
						// vì vậy ta chỉ cần xóa hết trong database của sức chứa kho hàng
	
						for (InventoryCapability inventoryCapability2 : inventory2.getInventoryCapabilities()) {
							inventoryCapabilityDao.delete(inventoryCapability2.getEmbeddedId());
						}
					}
				} 
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/kho-hang/danh-sach.htm?id=" + inventory.getId();
			}
		}
		
		return "addInventory";
	}
}
