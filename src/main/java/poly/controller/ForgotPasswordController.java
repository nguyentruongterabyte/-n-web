package poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dao.UserDao;
import poly.entity.User;
import poly.message.Message;

@Controller
public class ForgotPasswordController {
	@Autowired 
	private UserDao userDao;
	@RequestMapping(value="/quen-mat-khau", method = RequestMethod.GET)
	public String showForm() {
		return "forgotPassword";
	}
	
	@RequestMapping(value="/quen-mat-khau", method = RequestMethod.POST)
	public String searchEmail(@RequestParam("email") String email, 
			ModelMap model, 
			HttpSession session) {
		User user = userDao.getByEmail(email);
		Message message = new Message();
		if (user == null) {
			message.setContent("Không tồn tại tài khoản với email này!");
			message.setType("error");
			model.addAttribute("message", message);
			return "forgotPassword";
		}
		
		session.setAttribute("user", user);
		session.setAttribute("email", email);
		session.removeAttribute("verificationCode");
		return "redirect:doi-mat-khau.htm";
	}
}
