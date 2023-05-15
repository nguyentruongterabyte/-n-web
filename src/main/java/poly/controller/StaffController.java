package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nhan-vien")
public class StaffController {
	@RequestMapping("danh-sach")
	public String showList() {
		return "staffList";
	}
}
