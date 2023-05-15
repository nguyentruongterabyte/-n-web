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
			ModelMap model, 
			@RequestParam(value="id", required = false) String id) {
		
		if (id != null) {
			Staff staff = staffDao.get(Integer.parseInt(id));
			model.addAttribute("staff", staff);
		}
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
		Staff checkExist = staffDao.get(staff.getId());
		
		if (errors.hasErrors()) {
			message.setType("error");
			message.setContent("Vui lòng sửa các lỗi sau!");
			if (checkExist == null) {
				model.addAttribute("pageType", "add");
			} else {
				model.addAttribute("pageType", "edit");
			}
			model.addAttribute("message", message);
			model.addAttribute("staff", staff);
			return "addStaff";
		}
		
		if (checkExist == null) {
			message = staffDao.save(staff);
		} else {
			message = staffDao.update(staff);
		}
		
		List<Staff> list = staffDao.getAll();
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("staff", staff);
		redirectAttributes.addFlashAttribute("staffs", list);
		
		return "redirect:danh-sach.htm?id=" + staff.getId();  
	}
	
	@RequestMapping("chi-tiet")
	public String showDetail(
			ModelMap model,
			@RequestParam(value="id") String id
			) {
		Staff staff = staffDao.get(Integer.parseInt(id));
		if (staff != null) {
			
			staff.setBirthday(staff.getBirthday().split(" ")[0]);
			model.addAttribute("staff", staff);
		}
		return "staffDetail";
	}
	
	@RequestMapping("chinh-sua")
	public String edit(
			ModelMap model,
			@RequestParam("id") String id
			) {
		Staff staff = staffDao.get(Integer.parseInt(id));
		if (staff != null) {
			staff.setBirthday(staff.getBirthday().split(" ")[0]);
			model.addAttribute("staff", staff);
			
		}
		model.addAttribute("pageType", "edit");
		return "addStaff";
	}
	
	@RequestMapping("xoa")
	public String delete(
			RedirectAttributes redirectAttributes,
			@RequestParam(value="id") String id
			) {
		Message message = new Message();
		if (id == null) {
			message.setType("error");
			message.setContent("Lỗi lấy thông tin!");
		} else {
			message = staffDao.delete(Integer.parseInt(id));
			if (message.getType().equals("error")) {
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:danh-sach.htm?id=" + id;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:danh-sach.htm";
	}
}
