package poly.controller;


import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.bean.Mailer;
import poly.dao.UserDao;
import poly.entity.User;
import poly.message.Message;

@Controller
public class ChangePasswordController {
	@Autowired 
	private Mailer mailer;
	@Autowired
	private UserDao userDao;
	
	// Tạo ra mã xác thực gồm 6 chữ số
	private String generateVerificationCode() {
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		
		for (int i = 0; i < 6; i++) {
			int digit = random.nextInt(10);
			code.append(digit);
		}
		return code.toString();
	}
	
	// Hàm gửi mã xác thực cho tài khoản
	private boolean sendVerificationCode(String email, String code) {
		String from = "flashdropshop8@gmail.com";
		String to = email;
		String subject = "Xác thực đổi mật khẩu";
		
		String body = "<html>"
                + "<body>"
                + "<p>Dear " + email + ",</p>"
                + "<p>Chúng tôi đã nhận được yêu cầu đổi mật khẩu từ tài khoản của bạn. Để hoàn thành quá trình đổi mật khẩu, vui lòng sử dụng mã gồm 6 chữ số dưới đây để thiết lập mật khẩu mới:</p>"
                + "<h2>" + code + "</h2>"
                + "<p>Nếu bạn không yêu cầu đổi mật khẩu, vui lòng bỏ qua email này hoặc liên hệ với chúng tôi ngay để bảo vệ tài khoản của bạn.</p>"
                + "<p>Trân trọng,</p>"
                + "<p>DropFlash</p>"
                + "</body>"
                + "</html>";
		try {
			mailer.send(from, to, subject, body);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	@RequestMapping(value="doi-mat-khau", method = RequestMethod.GET)
	public String show(HttpSession session,
			RedirectAttributes redirectAttributes,
			ModelMap model) {
		String email = (String) session.getAttribute("email");
		Message message = new Message();
		if (email == null) {
			message.setType("error");
			message.setContent("Đã xảy ra lỗi vui lòng thử lại sau!");
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:quen-mat-khau.htm";
		}
		

		if (session.getAttribute("verificationCode") == null) {
			String code = generateVerificationCode();
			boolean success = sendVerificationCode(email, code);
			if (success) {
				message.setType("info");
				message.setContent("Một email xác thực đã được gửi đến " + email + "\nVui lòng kiểm tra");
				session.setAttribute("verificationCode", code);	
				
			}
			else {
				message.setType("error");
				message.setContent("Đã xảy ra lỗi khi gửi email xác thực!");
			}
			// Khởi tạo số lần xác thực để giới hạn
			// một mã xác thực được bao nhiêu lần 
			int verificationTimes = 0;
			session.setAttribute("verificationTimes", verificationTimes);
		} 
		model.addAttribute("message", message);
		model.addAttribute("email", email);
		return "changePassword";
	}
	
	
	@RequestMapping(value = "doi-mat-khau", method = RequestMethod.POST)
	public String changePassword(HttpSession session, 
			ModelMap model,
			RedirectAttributes redirectAttributes,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("rePassword") String rePassword,
			@RequestParam("verificationCode") String verificationCode) {
		String vCode = (String) session.getAttribute("verificationCode");
		boolean verify = false;
		Message message = new Message();
		if (!password.equals(rePassword)) {
			model.addAttribute("rePassword", "Mật khẩu nhập lại không đúng!");
			return "changePassword";
		}
		
		if (vCode.equals(verificationCode)) {
			verify = true;
		} else {
			int verificationTimes = (int) session.getAttribute("verificationTimes");
			if (verificationTimes > 5) {
				model.addAttribute("reVerification", "Bạn đã nhập sai mã xác thực quá 5 lần!");
				session.setAttribute("verificationCode", generateVerificationCode());
				return "changePassword";
			}
			
			session.setAttribute("verificationTimes", verificationTimes + 1);
			verify = false;
		}
		
		if (verify) {
			User user = (User) session.getAttribute("user");
			user.setPassword(password);
			message = userDao.update(user);
		
			redirectAttributes.addFlashAttribute("message", message);
			if (message.getType().equals("success")) {
				session.removeAttribute("verificationCode");
				session.removeAttribute("user");
			}
		} else {
			model.addAttribute("reVerification", "Mã xác thực không đúng!");
			return "changePassword";
		}
		
		return "redirect:dang-nhap.htm";
	}
}
