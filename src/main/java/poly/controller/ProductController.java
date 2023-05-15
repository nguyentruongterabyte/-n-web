package poly.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.ProductDao;
import poly.entity.Product;
import poly.message.Message;
import poly.message.MessageLog;

@Transactional
@Controller
@RequestMapping("/san-pham")
public class ProductController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	ServletContext context;
	
	private static final String UPLOAD_DIRECTORY = "/resource/images/product/";
	// RequestMapping
	@RequestMapping("danh-sach")
	public String showList(ModelMap model, 
			@RequestParam(value="id", required = false) String id) {
		if (id != null) {
			Product product = productDao.get(Integer.parseInt(id));			
			model.addAttribute("product", product);
		}
		List<Product> list = productDao.getAll();
		model.addAttribute("products", list);
		return "productList";
	}
	
	@RequestMapping(value = "xac-thuc", method = RequestMethod.POST)
	public String validate(
			ModelMap model,
			RedirectAttributes redirectAttributes,
			HttpSession session,
			@RequestParam("productId") String productId,
			@RequestParam("productName") String productName,
			@RequestParam("barcode") String barCode,
			@RequestParam("productUnit") String productUnit,
			@RequestParam("inPrice") String inPrice,
			@RequestParam("outPrice") String outPrice,
			@RequestParam("photoPath") String photoPath, 
			@RequestParam("productImage") MultipartFile picture,
			@RequestParam("pageType") String pageType
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
		model.addAttribute("pageType", pageType);

		
		if (!picture.isEmpty() && picture != null) {
//			if (picture.getContentType() != "image/jpeg") {
//				message.setType("error");
//				message.setContent("File bắt buộc là file ảnh");
//				model.addAttribute("message", message);
//				return "addProduct";
//			}
			try {
				String photoPathProcessing = UPLOAD_DIRECTORY + picture.getOriginalFilename();
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
		
		if (pageType.equals("add")) {
			Product newProduct = new Product(
					Integer.parseInt(productId),
					productName,
					barCode,
					photoPath,
					Integer.parseInt(inPrice),
					Integer.parseInt(outPrice),
					productUnit
					);
			message = productDao.save(newProduct);
			redirectAttributes.addFlashAttribute("message", message);
			redirectAttributes.addFlashAttribute("product", newProduct);
		} else {
			Product product = new Product(
					Integer.parseInt(productId),
					productName,
					barCode,
					photoPath,
					Integer.parseInt(inPrice),
					Integer.parseInt(outPrice),
					productUnit
					);
			message = productDao.update(product);
			redirectAttributes.addFlashAttribute("message", message);
			redirectAttributes.addFlashAttribute("product", product);
		}
		List<Product> list = productDao.getAll();
		redirectAttributes.addFlashAttribute("products", list);
		return "redirect:danh-sach.htm";
	}
	
	@RequestMapping("chinh-sua")
	public String edit(ModelMap model, 
			@RequestParam("id") String id) {
		Product product = productDao.get(Integer.parseInt(id));
		model.addAttribute("product", product);
		product.getId();
		model.addAttribute("pageType", "edit");
		model.addAttribute("productId", product.getId());
		model.addAttribute("productName", product.getName());
		model.addAttribute("barcode", product.getBarCode());
		model.addAttribute("productUnit", product.getUnit());
		model.addAttribute("inPrice", product.getInPrice());
		model.addAttribute("outPrice", product.getOutPrice());
		model.addAttribute("photoPath", product.getPicture());
		return "addProduct";
	}
	
	@RequestMapping(value="xoa")
	public String delete(RedirectAttributes redirectAttributes,
			@RequestParam(value="id") String id) {
		Message message = new Message();
		if (id == null) {
			message.setType("error");
			message.setContent("Lỗi lấy thông tin");
			MessageLog.showLog(message);
		} else {
			message = productDao.delete(Integer.parseInt(id));
			MessageLog.showLog(message);
			if (message.getType().equals("error")) {
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:danh-sach.htm?id=" + id;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		MessageLog.showLog(message);
		return "redirect:danh-sach.htm";
	}
	
	@RequestMapping(value = "them-moi")
	public String add(ModelMap model) {
		int maxId = productDao.getMaxId();
		model.addAttribute("photoPath", "");
		model.addAttribute("productId", maxId);
		model.addAttribute("pageType", "add");
		return "addProduct";
	}
	
	@RequestMapping(value = "chi-tiet")
	public String showDetail(
			ModelMap model,
			@RequestParam(value="id") String id) {
		Product product = productDao.get(Integer.parseInt(id));
		model.addAttribute("product", product);
		return "productDetail";
	}
}

