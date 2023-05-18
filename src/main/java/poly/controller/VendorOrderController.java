package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("don-nhap-hang")
public class VendorOrderController {
	@RequestMapping("them-moi")
	public String add() {
		return "addVendorOrder";
	}
}
