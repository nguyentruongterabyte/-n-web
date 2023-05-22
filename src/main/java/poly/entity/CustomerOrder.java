package poly.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CustomerOrder {
	@Id
	private int id;
	
	@NotBlank(message = "Vui lòng nhập mã khách hàng!")
	@ManyToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;
	
	@NotNull(message = "Vui lòng nhập số tiền giảm cho khách!")
	@DecimalMin(value = "0", message = "Giá giảm không được âm!")
	private float discount;
	
	@NotNull(message = "Vui lòng nhập số tiền phụ thu dịch vụ!")
	@DecimalMin(value = "0", message = "Giá phụ thu không được âm!")
	private float extraPaid;
	

	@OneToOne
	@JoinColumn(name = "id")
	private Document document;
	public CustomerOrder() {
		super();
	}
	
	
	
	public CustomerOrder(int id, Customer customer, float discount, float extraPaid, Document document) {
		super();
		this.id = id;
		this.customer = customer;
		this.discount = discount;
		this.extraPaid = extraPaid;
		this.document = document;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getExtraPaid() {
		return extraPaid;
	}
	public void setExtraPaid(float extraPaid) {
		this.extraPaid = extraPaid;
	}

	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}

	
	
	
}
