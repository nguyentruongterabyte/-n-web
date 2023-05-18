package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phieu-no-nha-cung-cap")
public class VendorDebtController {
	@RequestMapping("them-moi")
	public String add()
	{
		return "addVendorDebt";
	}
}
