package poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.InventoryDao;
import poly.entity.Inventory;

@Controller
@RequestMapping("/")
public class InventoryListController {
	@Autowired
	private InventoryDao inventoryDao;
	
	@RequestMapping("danh-sach-kho-hang")
	public String showList(@RequestParam(value="id", required = false) String id, ModelMap model) {		
		List<Inventory> list = inventoryDao.getAll();
		model.addAttribute("inventories", list);
		if (id == null) {
			model.addAttribute("inventory", new Inventory());
		} else {
			Inventory i = inventoryDao.get(Integer.parseInt(id));
			model.addAttribute("inventory", i);
		}
		return "inventoryList";
	}
	
}
