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


import poly.dao.VendorDao;
import poly.entity.Vendor;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("/nha-cung-cap")
public class VendorController {
	@Autowired
	private VendorDao vendorDao;
	
	@RequestMapping("danh-sach")
	public String showList(ModelMap model,
			@RequestParam(value="id", required = false) String id
			) {
		if (id != null) {
			Vendor vendor = vendorDao.get(Integer.parseInt(id));
			model.addAttribute("vendor", vendor);
		}
		List<Vendor> list = vendorDao.getAll();
		
		model.addAttribute("vendors", list);
		return "vendorList";
	}
	
	@RequestMapping("them-moi")
	public String add(ModelMap model) {
		int maxId = vendorDao.getMaxId();
		Vendor vendor = new Vendor();
		vendor.setId(maxId + 1);
		model.addAttribute("vendor", vendor);
		model.addAttribute("pageType", "add");
		return "addVendor";
	}
	
	@RequestMapping("chinh-sua")
	public String edit(
			ModelMap model,
			@RequestParam("id") String id
			) {
		Vendor vendor = vendorDao.get(Integer.parseInt(id));
		if (vendor != null) {
			model.addAttribute("vendor", vendor);
		}
		model.addAttribute("pageType", "edit");
		return "addVendor";
	}
	
	
	@RequestMapping(value = "xac-thuc", method = RequestMethod.POST) 
	public String validate(ModelMap model,
			RedirectAttributes redirectAttributes,
			@Validated @ModelAttribute("vendor") Vendor vendor,
			BindingResult errors) {
		Message message = new Message();
		Vendor checkExist = vendorDao.get(vendor.getId());
		if (errors.hasErrors()) {
			message.setType("error");
			message.setContent("Vui lòng sửa các lỗi sau!");
			if (checkExist == null) {
				model.addAttribute("pageType", "add");
			} else {
				model.addAttribute("pageType", "edit");
			}
			model.addAttribute("message", message);
			model.addAttribute("vendor", vendor);
			return "addVendor";
		}
		if (checkExist == null) {
			message = vendorDao.save(vendor);
			if (message.getType().equals("error")) {
				model.addAttribute("pageType", "add");
				model.addAttribute("vendor", vendor);
				model.addAttribute("message", message);
				return "addVendor";
			}
		} else {
			message = vendorDao.update(vendor);
			if (message.getType().equals("error")) {
				model.addAttribute("pageType", "edit");
				model.addAttribute("vendor", vendor);
				model.addAttribute("message", message);
				return "addVendor";
			}
		}
		List<Vendor> list = vendorDao.getAll();
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("vendor", vendor);
		redirectAttributes.addFlashAttribute("vendors", list);
		return "redirect:danh-sach.htm?id=" + vendor.getId();
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
			message = vendorDao.delete(Integer.parseInt(id));
			if (message.getType().equals("error")) {
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:danh-sach.htm?id=" + id;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:danh-sach.htm";
	}
 }
