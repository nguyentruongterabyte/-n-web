package poly.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CustomerBill {
	@Id
	private int id;
	
	@NotBlank(message = "Vui lòng nhập mã khách hàng!")
	@ManyToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;
	
	@NotNull(message = "Vui lòng nhập số tiền nhận!")
	@DecimalMin(value = "0", message = "Số tiền nhận không được âm!")
	private float receivedMoney;
	
	@NotNull(message = "Vui lòng nhập số tiền trả lại!")
	@DecimalMin(value = "0", message = "Số tiền trả lại không được âm!")
	private float changeMoney;
	
	@OneToOne
	@JoinColumn(name = "id")
	private Document document;

	public CustomerBill() {
		super();
	}

	public CustomerBill(int id, Customer customer, float receivedMoney, float changeMoney, Document document) {
		super();
		this.id = id;
		this.customer = customer;
		this.receivedMoney = receivedMoney;
		this.changeMoney = changeMoney;
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

	public float getReceivedMoney() {
		return receivedMoney;
	}

	public void setReceivedMoney(float receivedMoney) {
		this.receivedMoney = receivedMoney;
	}

	public float getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(float changeMoney) {
		this.changeMoney = changeMoney;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
}
