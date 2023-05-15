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
	private Collection<VendorBill> vendorBill;
	
	@OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
	private Collection<VendorBill> vendorOrder;
	
	@OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
	private Collection<VendorBill> vendorDebt;

	public Vendor() {
		super();
	}

	public Vendor(int id, String name, String phone, String email, String address, Collection<VendorBill> vendorBill,
			Collection<VendorBill> vendorOrder, Collection<VendorBill> vendorDebt) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.vendorBill = vendorBill;
		this.vendorOrder = vendorOrder;
		this.vendorDebt = vendorDebt;
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

	public Collection<VendorBill> getVendorBill() {
		return vendorBill;
	}

	public void setVendorBill(Collection<VendorBill> vendorBill) {
		this.vendorBill = vendorBill;
	}

	public Collection<VendorBill> getVendorOrder() {
		return vendorOrder;
	}

	public void setVendorOrder(Collection<VendorBill> vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	public Collection<VendorBill> getVendorDebt() {
		return vendorDebt;
	}

	public void setVendorDebt(Collection<VendorBill> vendorDebt) {
		this.vendorDebt = vendorDebt;
	}

}
