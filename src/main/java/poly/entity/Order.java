package poly.entity;


import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private Date transactionDate;
	@DecimalMin(value = "0", message = "Tổng giá không được âm!")
	@NotNull(message = "Vui lòng nhập tổng giá!")
	private float totalPrice;
	@DecimalMin(value = "0", message = "Giá trị cuối cùng không được âm!")
	@NotNull(message = "Vui lòng nhập giá trị cuối cùng!")
	private float finalPrice;
	@NotNull(message = "Vui lòng nhập trạng thái")
	private String status;
	
	@OneToMany(mappedBy = "embeddedId.order", fetch = FetchType.EAGER)
	private Collection<InOutInventoryDetail> inOutInventoryDetails;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	public Order() {
		super();
	}

	public Order(int id, Date transactionDate, float totalPrice, float finalPrice, String status) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.totalPrice = totalPrice;
		this.finalPrice = finalPrice;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
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

	
}
