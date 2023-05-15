package poly.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	@Column(name = "TransactionDate")
	private String transactionDate;
	@DecimalMin(value = "0", message = "Tổng giá không được âm!")
	@NotNull(message = "Vui lòng nhập tổng giá!")
	private float totalPrice;
	
	@DecimalMin(value = "0", message = "Thuế không được âm!")
	@NotNull(message = "Vui lòng nhập thuế!")
	private float vat;
	
	@DecimalMin(value = "0", message = "Giá trị cuối cùng không được âm!")
	@NotNull(message = "Vui lòng nhập giá trị cuối cùng!")
	private float finalPrice;
	@NotNull(message = "Vui lòng nhập trạng thái")
	private String status;
	
	@OneToMany(mappedBy = "embeddedId.order", fetch = FetchType.EAGER)
	private Collection<InOutInventoryDetail> inOutInventoryDetails;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	@OneToOne
	@JoinColumn(name = "id")
	private Document document;
	
	public Order() {
		super();
		inOutInventoryDetails = new ArrayList<>();
		orderDetails = new ArrayList<>();
	}
	
	public Order(int id, String transactionDate, float totalPrice, float vat, float finalPrice, String status,
			Collection<InOutInventoryDetail> inOutInventoryDetails, Collection<OrderDetail> orderDetails,
			Document document) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.totalPrice = totalPrice;
		this.vat = vat;
		this.finalPrice = finalPrice;
		this.status = status;
		this.inOutInventoryDetails = inOutInventoryDetails;
		this.orderDetails = orderDetails;
		this.document = document;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public float getVat() {
		return vat;
	}

	public void setVat(float vat) {
		this.vat = vat;
	}

	public float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Collection<InOutInventoryDetail> getInOutInventoryDetails() {
		return inOutInventoryDetails;
	}

	public void setInOutInventoryDetails(Collection<InOutInventoryDetail> inOutInventoryDetails) {
		this.inOutInventoryDetails = inOutInventoryDetails;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	public Document getDocument() {
		return document;
	}


	public void setDocument(Document document) {
		this.document = document;
	}
	
}