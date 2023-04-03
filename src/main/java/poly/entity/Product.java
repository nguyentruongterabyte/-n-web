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
	@Column(name = "Id")
	private int id;
	@NotBlank(message = "Vui lòng nhập tên sản phẩm!")
	private String name;
	@NotBlank(message = "Vui lòng nhập mã vạch!")
	private String barCode;
	private String picture;
	@DecimalMin(value = "0", message = "Giá nhập không được âm!")
	@NotNull(message = "Vui lòng giá nhập!")
	private float inPrice;
	@DecimalMin(value = "0", message = "Giá bán không được âm!")
	@NotNull(message = "Vui lòng nhập giá bán!")
	private float outPrice;
	
	@OneToMany(mappedBy = "embeddedId.product", fetch = FetchType.EAGER)
	private Collection<InventoryCapability> inventoryCapability;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
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
	public float getInPrice() {
		return inPrice;
	}
	public void setInPrice(float inPrice) {
		this.inPrice = inPrice;
	}
	public float getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(float outPrice) {
		this.outPrice = outPrice;
	}
	
}
