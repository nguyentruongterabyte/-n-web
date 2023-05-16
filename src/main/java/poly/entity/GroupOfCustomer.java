package poly.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
 class GroupOfCustomer {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Vui lòng nhập tên nhóm khách!")
	private String label;
	
	@NotBlank(message = "Vui lòng nhập tỉ lệ giảm giá!")
	private float discount;
	
	@OneToMany(mappedBy = "groupOfCustomer", fetch = FetchType.EAGER)
	private Collection<Customer> customers;

	public GroupOfCustomer() {
		super();
	}

	public GroupOfCustomer(int id, String label, float discount) {
		super();
		this.id = id;
		this.label = label;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}
	
}
