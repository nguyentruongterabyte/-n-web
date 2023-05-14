package poly.entity;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class InOutInventory {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	
	@NotBlank(message = "Vui lòng nhập Respond!")
	private int respond;
	@NotBlank(message = "VUi lòng nhập mã Kho!")
	
	@ManyToOne
	@JoinColumn(name = "InventoryId")
	private Inventory inventory;
	
	@DecimalMin(value = "0", message = "Giá mang hàng không được âm!")
	@NotBlank(message = "Vui lòng nhập phí mang hàng!")
	private float carryFee;
	@NotBlank(message = "Vui lòng nhập kỳ!")
	private int term;
	@NotBlank (message = "Vui lòng nhập loại")
	private boolean type;
	
	@OneToMany(mappedBy ="embeddedId.inOutInventory", fetch = FetchType.EAGER)
	private Collection<InOutInventoryDetail> inOutInventoryDetails;

	public InOutInventory() {
		super();
	}

	public InOutInventory(int id, int respond, Inventory inventory, float carryFee, int term, boolean type) {
		super();
		this.id = id;
		this.respond = respond;
		this.inventory = inventory;
		this.carryFee = carryFee;
		this.term = term;
		this.type = type;
	}

	public Collection<InOutInventoryDetail> getInOutInventoryDetails() {
		return inOutInventoryDetails;
	}

	public void setInOutInventoryDetails(Collection<InOutInventoryDetail> inOutInventoryDetails) {
		this.inOutInventoryDetails = inOutInventoryDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRespond() {
		return respond;
	}

	public void setRespond(int respond) {
		this.respond = respond;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public float getCarryFee() {
		return carryFee;
	}

	public void setCarryFee(float carryFee) {
		this.carryFee = carryFee;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
}
