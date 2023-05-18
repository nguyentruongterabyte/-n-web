package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phieu-thanh-toan-khach-hang")
public class CustomerBillController {
	@RequestMapping("them-moi")
	public String add() {
		return "addCustomerBill";
	}
}
