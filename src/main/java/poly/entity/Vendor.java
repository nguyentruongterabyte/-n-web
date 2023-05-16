package poly.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Vendor {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Vui lòng nhập tên nhà cung cấp!")
	private String name;
	
	private String phone;
	private String email;
	@NotBlank(message = "Vui lòng nhập địa chỉ!")
	private String address;
	
	@OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
	private Collection<VendorBill> vendorBills;
	
	@OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
	private Collection<VendorBill> vendorOrders;
	
	@OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
	private Collection<VendorBill> vendorDebts;

	public Vendor() {
		super();

	}

	public Vendor(int id, String name, String phone, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Collection<VendorBill> getVendorBills() {
		return vendorBills;
	}

	public void setVendorBills(Collection<VendorBill> vendorBills) {
		this.vendorBills = vendorBills;
	}

	public Collection<VendorBill> getVendorOrders() {
		return vendorOrders;
	}

	public void setVendorOrders(Collection<VendorBill> vendorOrders) {
		this.vendorOrders = vendorOrders;
	}

	public Collection<VendorBill> getVendorDebts() {
		return vendorDebts;
	}

	public void setVendorDebts(Collection<VendorBill> vendorDebts) {
		this.vendorDebts = vendorDebts;
	}

}
