package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phieu-thanh-toan-nha-cung-cap")
public class VendorBillController {
	@RequestMapping("them-moi")
	public String add() {
		return "addVendorBill";
	}
}
