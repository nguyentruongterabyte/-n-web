package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("don-ban-hang")
public class CustomerOrderController {
	@RequestMapping("them-moi")
	public String add() {
		return "addCustomerOrder";
	}
}
