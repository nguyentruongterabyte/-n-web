package poly.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.CustomerDao;
import poly.dao.DocumentDao;
import poly.dao.InventoryDao;
import poly.dao.OrderDao;
import poly.dao.StaffDao;
import poly.entity.Customer;
import poly.entity.CustomerBill;
import poly.entity.CustomerDebt;
import poly.entity.CustomerOrder;
import poly.entity.Document;
import poly.entity.InOutInventory;
import poly.entity.Inventory;
import poly.entity.Order;
import poly.entity.Staff;
import poly.entity.VendorBill;
import poly.entity.VendorDebt;
import poly.entity.VendorOrder;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("don-tu")
public class DocumentController {
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired 
	private CustomerDao customerDao;
	
	@RequestMapping("danh-sach")
	public String ShowList(ModelMap model, 
			@RequestParam(value = "id", required = false) String id
			) {
		if (id != null) {
			Document document = documentDao.get(Integer.parseInt(id));
			model.addAttribute("document", document);
		}
		List<Document> list = documentDao.getAll();
		List<Staff> creators = staffDao.getAll();
		model.addAttribute("creators", creators);
		model.addAttribute("documents", list);
		return "documentList";
	}
	
	@RequestMapping("them-moi")
	public String add(ModelMap model
			) {
		List<Staff> creators = staffDao.getAll();
		if (creators.toArray().length == 0) {
			Message message = new Message();
			message.setType("warning");
			message.setContent("Chưa có nhân viên nào trong danh sách để lập đơn từ!");
			List<Document> documents = documentDao.getAll();
			model.addAttribute("documents", documents);
			model.addAttribute("message", message);
			return "documentList";
		}
		int maxId = documentDao.getMaxId();
		Document document = new Document();
		document.setId(maxId + 1);
		model.addAttribute("creators", creators);
		model.addAttribute("document", document);
		return "addDocument";
	}
	
	@RequestMapping("xac-thuc")
	public String validate(ModelMap model,
			RedirectAttributes redirectAttributes,
			HttpSession session,
			@Validated @ModelAttribute("document") Document document,
			BindingResult errors) {
		Message message = new Message();
		if (errors.hasErrors()) {
			message.setType("error");
			message.setContent("Vui lòng sửa các lỗi sau");
			List<Staff> creators = staffDao.getAll();
			model.addAttribute("creators", creators);
			model.addAttribute("message", message);
			model.addAttribute("document", document);
			return "addDocument";
		}
		
		String date = document.getCreateDate().split("T")[0] + " " + document.getCreateDate().split("T")[1];
		document.setCreateDate(date);

		switch (document.getType()) {
		case "outinventory": {
			List<Order> orders = orderDao.getOrdersWithoutInOutInventory();
			
			InOutInventory inOutInventory = new InOutInventory();
			inOutInventory.setId(document.getId());
			inOutInventory.setDocument(document);
			inOutInventory.setType(true);
			redirectAttributes.addFlashAttribute("inOutInventory", inOutInventory);
			redirectAttributes.addFlashAttribute("orders", orders);
			return "redirect:../don-nhap-xuat-kho/them-moi.htm";
		}
		case "ininventory": {			
			List<Order> orders = orderDao.getOrdersWithoutInOutInventory();
			
			InOutInventory inOutInventory = new InOutInventory();
			inOutInventory.setId(document.getId());
			inOutInventory.setDocument(document);
			inOutInventory.setType(false);
			redirectAttributes.addFlashAttribute("inOutInventory", inOutInventory);
			redirectAttributes.addFlashAttribute("orders", orders);
			return "redirect:../don-nhap-xuat-kho/them-moi.htm";
		}
		case "vendororder": {
			VendorOrder vendorOrder = new VendorOrder();
			vendorOrder.setId(document.getId());
			vendorOrder.setDocument(document);
			redirectAttributes.addFlashAttribute("vendorOrder", vendorOrder);
			return "redirect:../don-nhap-hang/them-moi.htm";
		}
		case "customerorder": {
			List<Inventory> inventories = inventoryDao.getAll();
			List<Customer> customers = customerDao.getAll();
			CustomerOrder customerOrder = new CustomerOrder();
			
			customerOrder.setId(document.getId());
			customerOrder.setDocument(document);
			
			session.setAttribute("customers", customers);
			session.setAttribute("inventories", inventories);
			session.setAttribute("customerOrder", customerOrder);
			return "redirect:../don-ban-hang/them-moi.htm";
			
			
		}
		case "vendordebt": {
			VendorDebt vendorDebt = new VendorDebt();
			vendorDebt.setId(document.getId());
			vendorDebt.setDocument(document);
			redirectAttributes.addFlashAttribute("vendorDebt", vendorDebt);
			return "redirect:../phieu-no-nha-cung-cap/them-moi.htm";
		}
		case "customerdebt": {
			CustomerDebt customerDebt = new CustomerDebt();
			customerDebt.setId(document.getId());
			customerDebt.setDocument(document);
			redirectAttributes.addFlashAttribute("customerDebt", customerDebt);
			return "redirect:../phieu-no-khach-hang/them-moi.htm";
		}
		case "vendorbill": {
			VendorBill vendorBill = new VendorBill();
			vendorBill.setId(document.getId());
			vendorBill.setDocument(document);
			redirectAttributes.addFlashAttribute("vendorBill", vendorBill);
			return "redirect:../phieu-thanh-toan-nha-cung-cap/them-moi.htm";
		}
		case "customerbill": {
			CustomerBill customerBill = new CustomerBill();
			customerBill.setId(document.getId());
			customerBill.setDocument(document);
			redirectAttributes.addFlashAttribute("customerBill", customerBill);
			return "redirect:../phieu-thanh-toan-khach-hang/them-moi.htm";
		}
	
		default:
			message.setType("error");
			message.setContent("Không thể xác định loại đơn từ!");
			List<Staff> creators = staffDao.getAll();
			redirectAttributes.addFlashAttribute("creators", creators);
			redirectAttributes.addFlashAttribute("message", message);
			redirectAttributes.addFlashAttribute("document", document);
		}
		return "redirect:them-moi.htm";
	}
	
	@RequestMapping("xoa")
	public String delete(
			RedirectAttributes redirectAttributes,
			@RequestParam("id") String id,
			@RequestParam("type") String type
			) {
		if (id != null && type != null) {
			System.out.println("----------------------"
					+ "\ntype = " + type
					+ "\nid = " + id);
			switch (type) {
			case "outinventory": {		
				break;
			}
			case "ininventory": {
				break;
			}
			case "vendororder": {
				break;
			}
			case "customerorder": {
				break;
			}
			case "vendordebt": {
				break;
			}
			case "customerdebt": {
				break;
			}
			case "vendorbill": {
				break;
			}
			case "customerbill": {
				break;
			}
		
			default:
				
			}
		}
		return "redirect:danh-sach.htm";
	}
}
