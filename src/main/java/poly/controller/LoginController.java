package poly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dao.UserDao;
import poly.entity.User;
import poly.message.Message;

@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.GET) 
	public String show(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
	public String login(ModelMap model, 
				@Validated @ModelAttribute("user") User user,
				BindingResult errors,
				HttpSession session,
				HttpServletRequest request
			) {
		
		Message message = new Message();
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		
		boolean verify = false;
		
		if (captcha.equals(verifyCaptcha)) {
			verify = true;
		} else {
			verify = false;
		}
			
		if (errors.hasErrors() || !verify) {
			
			if (!verify) {
				model.addAttribute("reCaptcha", "Vui lòng nhập reCaptcha");
			}
			message.setType("error");
			message.setContent("Vui lòng sửa các lỗi sau đây!");
			model.addAttribute("user", user);
			model.addAttribute("message", message);
			return "login";
		}
		String username = user.getUsername();
		String password = user.getPassword();
		User user2 = userDao.get(user.getUsername());
		
		if (user2 == null) {
			message.setType("error");
			message.setContent("Tên tài khoản hoặc mật khẩu không đúng");
			model.addAttribute("user", user);
			model.addAttribute("message", message);
			return "login";
		}
		
		if (!user2.getUsername().trim().equals(username) || !user2.getPassword().trim().equals(password)) {
			message.setType("error");
			message.setContent("Tên tài khoản hoặc mật khẩu không đúng");
			model.addAttribute("user", user);
			model.addAttribute("message", message);
			return "login";
		}
		
		session.setAttribute("user", user);
		return "redirect:trang-chu.htm";
	}
}
