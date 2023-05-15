package poly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class VendorDebt {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Vui lòng chọn mã nhà cung cấp!")
	@ManyToOne
	@JoinColumn(name = "VendorId")
	private Vendor vendor;
	
	@NotBlank(message = "Vui lòng chọn mã đơn dặt hàng")
	@ManyToOne
	@JoinColumn(name = "VendorOrderId")
	private VendorOrder vendorOrder;
	
	@NotBlank(message = "Vui lòng nhập số tiền")
	private float outCome;
	
	@OneToOne
	@JoinColumn(name = "id")
	private Document document;

	public VendorDebt() {
		super();
	}

	public VendorDebt(int id, Vendor vendor, VendorOrder vendorOrder, float outCome, Document document) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.vendorOrder = vendorOrder;
		this.outCome = outCome;
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

	public VendorOrder getVendorOrder() {
		return vendorOrder;
	}

	public void setVendorOrder(VendorOrder vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	public float getOutCome() {
		return outCome;
	}

	public void setOutCome(float outCome) {
		this.outCome = outCome;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
}
