package poly.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.dao.CustomerDao;
import poly.dao.CustomerOrderDao;
import poly.dao.DocumentDao;
import poly.dao.InventoryDao;
import poly.dao.OrderDao;
import poly.dao.OrderDetailDao;
import poly.dao.ProductDao;
import poly.entity.Customer;
import poly.entity.CustomerOrder;
import poly.entity.Document;
import poly.entity.Inventory;
import poly.entity.Order;
import poly.entity.OrderDetail;
import poly.entity.Product;
import poly.message.Message;

@Controller
@RequestMapping("don-ban-hang")
public class CustomerOrderController {
	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	CustomerDao customerDao;
	
	@Autowired 
	DocumentDao documentDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderDetailDao orderDetailDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
//	final double VATDefault = 0.1;
	
	
	@RequestMapping("them-moi")
	public String add(
			ModelMap model,
			HttpSession session
			) {
		@SuppressWarnings("unchecked")
		List<Inventory> inventories = (List<Inventory>) session.getAttribute("inventories");
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) session.getAttribute("customers");
		CustomerOrder customerOrder = (CustomerOrder) session.getAttribute("customerOrder");
		
		// Test
//		System.out.println(customerOrder.getDocument().getCreateDate());
//		model.addAttribute("VATDefault", VATDefault);
		model.addAttribute("customers", customers);
		model.addAttribute("customerOrder", customerOrder);
		model.addAttribute("inventories", inventories);
		return "addCustomerOrder";
	}
	@RequestMapping("chon-kho-hang")
	public String selectInventory(
			RedirectAttributes redirectAttributes,
			@RequestParam("id") String id,
			HttpSession session
			) {
		if (id != "") {
			Inventory inventory = inventoryDao.get(Integer.parseInt(id));
			if (inventory != null) {
				redirectAttributes.addFlashAttribute("inventory", inventory);
			}			
		}
		return "redirect:them-moi.htm";
	}
	
	@RequestMapping("xac-thuc")
	public String validate(
			RedirectAttributes redirectAttributes,
			
			HttpSession session,
			@RequestParam(value = "inventory", required = false) String inventoryId,
			@RequestParam(value = "productsId[]", required = false) String [] productsId,
			@RequestParam(value = "productsPrice[]", required = false) String [] productsPrice,
			@RequestParam(value = "productsQuantity[]", required = false) String [] productsQuantity,
			@RequestParam(value = "customer", required = false) String customerId,
			@RequestParam(value = "payment", required = false) String payment,
			@RequestParam(value = "receivedMoney", required = false) String receivedMoney,
			@RequestParam(value = "changeMoney", required = false) String changeMoney,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "totalPrice", required = false) String totalPrice,
			@RequestParam(value = "finalPrice", required = false) String finalPrice,
			@RequestParam(value = "discount", required = false) String discount,
			@RequestParam(value = "VAT", required = false) String VAT,
			@RequestParam(value = "extraPaid", required = false) String extraPaid
			
			) {
		// Test
		Message message = new Message();
//	 
		Collection<OrderDetail> orderDetails = new ArrayList<>();
		Order order = new Order();
		if (productsId != null) {
			for (int i = 0; i < productsId.length; i++) {
				Product product = productDao.get(Integer.parseInt(productsId[i]));
				int productPrice = Integer.parseInt(productsPrice[i]);
				int productQuantity = Integer.parseInt(productsQuantity[i]);
				
				OrderDetail.Id embeddedId = new OrderDetail.Id(order, product);
				orderDetails.add(new OrderDetail(embeddedId, productQuantity, productPrice));
			}
		}
		redirectAttributes.addFlashAttribute("orderDetails", orderDetails);
		redirectAttributes.addFlashAttribute("totalPrice", totalPrice);
		redirectAttributes.addFlashAttribute("extraPaid", extraPaid);
		redirectAttributes.addFlashAttribute("finalPrice", finalPrice);
		redirectAttributes.addFlashAttribute("payment", payment);
		redirectAttributes.addFlashAttribute("status", status);
		redirectAttributes.addFlashAttribute("receivedMoney", receivedMoney);
		redirectAttributes.addFlashAttribute("changeMoney", changeMoney);
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		// Convert kiểu string của discount thành kiểu double
		double discountDouble = Double.parseDouble(discount);
		
		
		// Convert kiểu string của VAT thành kiểu double
		double VATDouble = Double.parseDouble(VAT);

		redirectAttributes.addFlashAttribute("discount", decimalFormat.format(discountDouble).replace(',', '.'));
		redirectAttributes.addFlashAttribute("VAT", decimalFormat.format(VATDouble).replace(',', '.'));
		Customer customer = new Customer();
		if (customerId.trim().length() != 0) {
			customer = customerDao.get(Integer.parseInt(customerId));
			redirectAttributes.addFlashAttribute("customer", customer);
		}
		if (inventoryId.trim().length() != 0) {
			Inventory inventory = inventoryDao.get(Integer.parseInt(inventoryId));
			redirectAttributes.addFlashAttribute("inventory", inventory);
		}
		
		// Xác thực
		if (productsId == null) {
			message.setType("error");
			message.setContent("Vui lòng không để trống giỏ hàng!");
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:them-moi.htm";

		}
		
		if (Integer.parseInt(receivedMoney) < Integer.parseInt(finalPrice)) {
			message.setType("error");
			message.setContent("Số tiền nhận chưa đủ!");
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:them-moi.htm";

		}
		
		// Lấy document từ session về
		Document document = (Document) session.getAttribute("document");
		// Lưu document vào database
		documentDao.save(document);
		
		// Cài đặt các tham số cho đơn đặt hàng
		order.setDocument(document);
		order.setId(document.getId());
		order.setFinalPrice(Float.parseFloat(finalPrice));
		order.setTotalPrice(Float.parseFloat(totalPrice));
		order.setVat(Float.parseFloat(VAT));
		order.setStatus(status);
		order.setOrderDetails(orderDetails);
		// Lưu đơn đặt hàng vào database
		Message msg = orderDao.save(order);
		if (!msg.getType().equals("error")) {
			for (OrderDetail orderDetail : orderDetails) {
				OrderDetail.Id id = orderDetail.getEmbeddedId();
				id.setOrder(order);
				orderDetail.setEmbeddedId(id);
				
				// Lưu chi tiết đơn hàng vào cơ sở dữ liệu
				orderDetailDao.save(orderDetail);
			}
		}
		
		
		CustomerOrder customerOrder = new CustomerOrder(document.getId(), customer, Float.parseFloat(discount), Float.parseFloat(extraPaid), document);
		customerOrderDao.save(customerOrder);
		return "redirect:../don-tu/danh-sach.htm";
	}
}
