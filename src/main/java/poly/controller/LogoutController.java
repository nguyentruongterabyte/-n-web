package poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value="/dang-xuat")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:trang-chu.htm";
	}
}
