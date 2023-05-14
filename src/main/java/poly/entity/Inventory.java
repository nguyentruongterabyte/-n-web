package poly.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Inventory {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	@NotBlank(message = "Vui lòng nhập tên kho hàng!")
	private String name;
	@NotBlank(message= "Vui lòng nhập địa chỉ!")
	private String address;
	@NotNull(message="Vui lòng nhập giá thuê!")
	private float rentPrice;
	@NotNull(message="Vui lòng nhập kỳ!")
	private int term;
	@OneToMany(mappedBy = "embeddedId.inventory", fetch = FetchType.EAGER)
	private Collection<InventoryCapability> inventoryCapabilitys;
	
	@OneToMany(mappedBy = "inventory", fetch = FetchType.EAGER)
	private Collection<InOutInventory> inOutInventorys;
	
	public Inventory() {
		super();
		this.inventoryCapabilitys = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	

	public Inventory(int id, String name, String address, float rentPrice, int term) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rentPrice = rentPrice;
		this.term = term;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public Collection<InventoryCapability> getInventoryCapability() {
		return inventoryCapabilitys;
	}

	public void setInventoryCapability(Collection<InventoryCapability> inventoryCapability) {
		this.inventoryCapabilitys = inventoryCapability;
	}
	
	public Collection<InOutInventory> getInOutInventory() {
		return inOutInventorys;
	}

	public void setInOutInventory(Collection<InOutInventory> inOutInventory) {
		this.inOutInventorys = inOutInventory;
	}
	
}
