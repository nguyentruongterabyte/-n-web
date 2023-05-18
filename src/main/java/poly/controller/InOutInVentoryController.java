package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("don-nhap-xuat-kho")
public class InOutInVentoryController {
	@RequestMapping("them-moi")
	public String add(
			@RequestParam("type") String type
			) {
		return "addInOutInventory";
	}
}
