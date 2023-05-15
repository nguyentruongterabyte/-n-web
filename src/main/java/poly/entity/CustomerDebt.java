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
public class CustomerDebt {
	@Id
	private int id;
	
	@NotBlank(message = "Vui lòng nhập mã khách hàng!")
	@ManyToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;
	
	@NotBlank(message = "Vui lòng nhập mã hóa đơn!")
	@ManyToOne
	@JoinColumn(name = "CustomerOrderId")
	private CustomerOrder customerOrder;
	
	@NotNull(message = "Vui lòng nhập số tiền thu nhập!")
	@DecimalMin(value = "0", message = "Thu nhập không được âm!")
	private float inCome;

	@OneToOne
	@JoinColumn(name = "id")
	private Document document;
	public CustomerDebt() {
		super();
	}

	public CustomerDebt(int id, Customer customer, CustomerOrder customerOrder, float inCome, Document document) {
		super();
		this.id = id;
		this.customer = customer;
		this.customerOrder = customerOrder;
		this.inCome = inCome;
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

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public float getInCome() {
		return inCome;
	}

	public void setInCome(float inCome) {
		this.inCome = inCome;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
