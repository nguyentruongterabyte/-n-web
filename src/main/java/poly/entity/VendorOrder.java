package poly.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class VendorOrder {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "VendorId")
	private Vendor vendor;
	
	@OneToMany(mappedBy = "vendorOrder", fetch = FetchType.EAGER)
	private Collection<VendorDebt> vendorDebts;

	@OneToOne
	@JoinColumn(name = "id")
	private Document document;
	
	public VendorOrder() {
		super();
		vendorDebts = new ArrayList<>();
	}

	
	public VendorOrder(int id, Vendor vendor, Collection<VendorDebt> vendorDebts, Document document) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.vendorDebts = vendorDebts;
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

	public Collection<VendorDebt> getVendorDebts() {
		return vendorDebts;
	}

	public void setVendorDebts(Collection<VendorDebt> vendorDebts) {
		this.vendorDebts = vendorDebts;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
