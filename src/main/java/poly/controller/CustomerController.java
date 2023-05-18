package poly.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.CustomerDao;
import poly.dao.GroupOfCustomerDao;
import poly.entity.Customer;
import poly.entity.GroupOfCustomer;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("/khach-hang")
public class CustomerController {
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	GroupOfCustomerDao groupOfCustomerDao;
	
	@RequestMapping("danh-sach")
	public String showList(
			ModelMap model, 
			@RequestParam(value="id", required = false) String id
			) {
		if (id != null) {
			Customer customer = customerDao.get(Integer.parseInt(id));
			model.addAttribute("customer", customer);
		}
		List<Customer> list = customerDao.getAll();
		model.addAttribute("customers", list);
		return "customerList";
	}
	
	@RequestMapping("them-moi")
	public String add(ModelMap model) {
		int maxId = customerDao.getMaxId();
		Customer customer = new Customer();
		customer.setId(maxId + 1);
		List<GroupOfCustomer> groupOfCustomers = groupOfCustomerDao.getAll();
		model.addAttribute("groupOfCustomers", groupOfCustomers);
		model.addAttribute("customer", customer);
		model.addAttribute("pageType", "add");
		return "addCustomer";
	}
	
	@RequestMapping(value="xac-thuc", method = RequestMethod.POST)
	public String validate(
			ModelMap model,
			RedirectAttributes redirectAttributes,
			@Validated @ModelAttribute("customer") Customer customer,
			BindingResult errors
			) {
		Message message = new Message();
		Customer checkExist = customerDao.get(customer.getId());
		if (errors.hasErrors()) {
			message.setType("error");
			message.setContent("Vui lòng sửa các lỗi sau!");
			if (checkExist == null) {
				model.addAttribute("pageType", "add");
			} else {
				model.addAttribute("pageType", "edit");
			}
			List<GroupOfCustomer> groupOfCustomers = groupOfCustomerDao.getAll();
			model.addAttribute("groupOfCustomers", groupOfCustomers);
			model.addAttribute("message", message);
			model.addAttribute("customer", customer);
			return "addCustomer";
		}
		
		if (checkExist == null) {
			message = customerDao.save(customer);
			if (message.getType().equals("error")) {
				model.addAttribute("message", message);
				model.addAttribute("customer", customer);
				model.addAttribute("pageType", "add");
				return "addCustomer";
			}
		}
		else {
			message = customerDao.update(customer);
			if (message.getType().equals("error")) {
				model.addAttribute("message", message);
				model.addAttribute("customer", customer);
				model.addAttribute("pageType", "edit");
				return "addCustomer";
			}
		}
		
		List<Customer> list = customerDao.getAll();
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("customer", customer);
		redirectAttributes.addFlashAttribute("customers", list);
		
		return "redirect:danh-sach.htm?id=" + customer.getId();
	}
	
	@RequestMapping("chi-tiet")
	public String showDetail(
			ModelMap model,
			@RequestParam(value="id") String id
			) {
		Customer customer = customerDao.get(Integer.parseInt(id));
		if (customer != null) {
			customer.setBirthday(customer.getBirthday().split(" ")[0]);
			model.addAttribute("customer", customer);
		}
		List<GroupOfCustomer> groupOfCustomers = groupOfCustomerDao.getAll();
		model.addAttribute("groupOfCustomers", groupOfCustomers);
		model.addAttribute("pageType", "edit");
		return "customerDetail";
	}
	
	@RequestMapping("xoa")
	public String delete(
			RedirectAttributes redirectAttributes,
			@RequestParam(value="id") String id) {
		Message message = new Message();
		if (id == null) {
			message.setType("error");
			message.setContent("Lỗi lấy thông tin");
		} else {
			message = customerDao.delete(Integer.parseInt(id));
			if (message.getType().equals("error")) {
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:danh-sach.htm?id=" + id;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:danh-sach.htm";
	}
	
	@RequestMapping("chinh-sua")
	public String edit(
			ModelMap model, 
			@RequestParam("id") String id
			) {
		Customer customer = customerDao.get(Integer.parseInt(id));
		List<GroupOfCustomer> groupOfCustomers = groupOfCustomerDao.getAll();
		if (customer != null) {
			customer.setBirthday(customer.getBirthday().split(" ")[0]);
			model.addAttribute("customer", customer);
		}
		model.addAttribute("groupOfCustomers", groupOfCustomers);
		model.addAttribute("pageType", "edit");
		return "addCustomer";
	}
	
}
