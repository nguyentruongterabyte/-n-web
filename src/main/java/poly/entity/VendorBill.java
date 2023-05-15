package poly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VendorBill {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name  = "VendorId")
	private Vendor vendor;
	
	@ManyToOne
	@JoinColumn(name = "PaidStaff")
	private Staff staff;

	public VendorBill() {
		super();
	}

	public VendorBill(int id, Vendor vendor, Staff staff) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}
