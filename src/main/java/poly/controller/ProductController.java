package poly.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dao.ProductDao;
import poly.entity.Product;
import poly.message.Message;

@Transactional
@Controller
@RequestMapping("/san-pham")
public class ProductController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	ServletContext context;
	// RequestMapping
	@RequestMapping("danh-sach")
	public String showList(ModelMap model) {
		List<Product> list = productDao.getAll();
		model.addAttribute("products", list);
		return "productList";
	}
	
	@RequestMapping(value = "xac-thuc", method = RequestMethod.POST)
	public String validate(
			ModelMap model,
			@RequestParam("productId") String productId,
			@RequestParam("productName") String productName,
			@RequestParam("barcode") String barCode,
			@RequestParam("productUnit") String productUnit,
			@RequestParam("inPrice") String inPrice,
			@RequestParam("outPrice") String outPrice,
			@RequestParam("photoPath") String photoPath, 
			@RequestParam("productImage") MultipartFile picture
			)
	{
		Message message = new Message();
		model.addAttribute("productId", productId);
		model.addAttribute("productName", productName);
		model.addAttribute("barcode", barCode);
		model.addAttribute("productUnit", productUnit);
		model.addAttribute("inPrice", inPrice);
		model.addAttribute("outPrice", outPrice);
		model.addAttribute("photoPath", photoPath);
		model.addAttribute("picture", picture);

		
		if (!picture.isEmpty()) {
			if (picture.getContentType() != "image/jpeg") {
				message.setType("error");
				message.setContent("File bắt buộc là file ảnh");
				model.addAttribute("message", message);
				return "addProduct";
			}
			try {
				String photoPathProcessing = "/resource/images/product/" + picture.getOriginalFilename();
				photoPath = photoPathProcessing;
				String photoRealPath = context.getRealPath(photoPathProcessing);
				picture.transferTo(new File(photoRealPath));
				model.addAttribute("photoPath", photoPath);
			} catch(Exception e) {
				photoPath = "";
				model.addAttribute("photoPath", photoPath);
				message.setType("error");
				message.setContent("Lỗi lưu file ảnh!\n" + e);
			}
		}
		
		if (productName.trim().length() == 0) {
			message.setType("error");
			message.setContent("Không được để trống tên sản phẩm!");
			model.addAttribute("message", message);
			return "addProduct";
		}
		if (barCode.trim().length() == 0) {
			message.setType("error");
			message.setContent("Không được để trống mã vạch sản phẩm!");
			model.addAttribute("message", message);
			return "addProduct";
		}
		
		if (inPrice.trim().length() == 0) {
			message.setType("error");
			message.setContent("Vui lòng nhập giá nhập!");
			model.addAttribute("message", message);
			return "addProduct";
		}
		
		if (Integer.parseInt(inPrice) < 0) {
			message.setType("error");
			message.setContent("Giá nhập không hợp lệ!");
			model.addAttribute("message", message);
			return "addProduct";
		}
		if (outPrice.trim().length() == 0) {
			message.setType("error");
			message.setContent("Vui lòng nhập giá bán!");
			model.addAttribute("message", message);
			return "addProduct";
		}
		
		if (Integer.parseInt(outPrice) < 0) {
			message.setType("error");
			message.setContent("Giá bán không hợp lệ!");
			model.addAttribute("message", message);
			return "addProduct";
		}
		
	
		Product newProduct = new Product(
				Integer.parseInt(productId),
				productName,
				barCode,
				photoPath,
				Float.parseFloat(inPrice),
				Float.parseFloat(outPrice),
				productUnit
				);
		productDao.save(newProduct);
		message.setType("success");
		message.setContent("Thêm sản phẩm mới thành công!");
		List<Product> list = productDao.getAll();
		model.addAttribute("products", list);
		model.addAttribute("message", message);
		return "productList";
	}
	
	@RequestMapping(value = "/{id}", params = "lnkEdit")
	public String edit(ModelMap model, @PathVariable("id") int id) {
		Product product = productDao.get(id);
		model.addAttribute("product", product);
		product.getId();
		return "productEdit";
	}
	
	@RequestMapping(value = "them-moi")
	public String add(ModelMap model) {
		int maxId = productDao.getMaxId();
		model.addAttribute("photoPath", "");
		model.addAttribute("productId", maxId);
		model.addAttribute("pageType", "add");
		
		return "addProduct";
	}
}

