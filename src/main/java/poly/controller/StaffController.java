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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.StaffDao;
import poly.entity.Staff;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("/nhan-vien")
public class StaffController {
	@Autowired
	private StaffDao staffDao;
	
	@RequestMapping("danh-sach")
	public String showList(
			ModelMap model) {
		List<Staff> list = staffDao.getAll();
		model.addAttribute("staffs", list);
		return "staffList";
	}
	
	@RequestMapping("them-moi")
	public String add(ModelMap model) {
		int maxId = staffDao.getMaxId();
		Staff staff = new Staff();
		staff.setId(maxId + 1);
		model.addAttribute("staff", staff);
		model.addAttribute("pageType", "add");
		return "addStaff";
	}
	
	@RequestMapping(value="xac-thuc", method = RequestMethod.POST)
	public String validate(ModelMap model,
			RedirectAttributes redirectAttributes,
			@Validated @ModelAttribute("staff") Staff staff,
			BindingResult errors
			) {
		Message message = new Message();
		if (errors.hasErrors()) {
			message.setType("error");
			message.setContent("Vui lòng sửa các lỗi sau!");
			model.addAttribute("message", message);
			model.addAttribute("staff", staff);
			return "addStaff";
		}
		
		return "redirect:danh-sach.htm?id=" + staff.getId();  
	}
}
