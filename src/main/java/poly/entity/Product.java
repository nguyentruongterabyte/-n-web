package poly.entity;


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
public class Product {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Vui lòng nhập tên sản phẩm!")
	private String name;
	@NotBlank(message = "Vui lòng nhập mã vạch!")
	private String barCode;
	private String picture;
	@DecimalMin(value = "0", message = "Giá nhập không được âm!")
	@NotNull(message = "Vui lòng giá nhập!")
	private int inPrice;
	@DecimalMin(value = "0", message = "Giá bán không được âm!")
	@NotNull(message = "Vui lòng nhập giá bán!")
	private int outPrice;
	private String unit;
	
	@OneToMany(mappedBy = "embeddedId.product", fetch = FetchType.EAGER)
	private Collection<InventoryCapability> inventoryCapabilities;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	public Product() {
		super();
	}

	
	public Product(int id, String name, String barCode, String picture, int inPrice, int outPrice, String unit) {
		super();
		this.id = id;
		this.name = name;
		this.barCode = barCode;
		this.picture = picture;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.unit = unit;
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
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

	public int getInPrice() {
		return inPrice;
	}

	public void setInPrice(int inPrice) {
		this.inPrice = inPrice;
	}

	public int getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(int outPrice) {
		this.outPrice = outPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	public Collection<InventoryCapability> getInventoryCapabilities() {
		return inventoryCapabilities;
	}

	public void setInventoryCapabilities(Collection<InventoryCapability> inventoryCapabilities) {
		this.inventoryCapabilities = inventoryCapabilities;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
}
