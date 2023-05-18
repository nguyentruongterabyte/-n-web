package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phieu-no-khach-hang")
public class CustomerDebtController {
	@RequestMapping("them-moi")
	public String add() {
		return "addCustomerDebt";
	}
}
