package poly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	@OneToOne
	@JoinColumn(name ="id")
	private Document document;
	
	public VendorBill() {
		super();
	}

	public VendorBill(int id, Vendor vendor, Staff staff, Document document) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.staff = staff;
		this.document = document;
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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
