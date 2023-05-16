package poly.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Vui lòng nhập nhóm khách hàng!")
	@ManyToOne
	@JoinColumn(name = "GroupId")
	private GroupOfCustomer groupOfCustomer;
	
	@NotBlank(message = "Vui lòng nhập tên khách hàng!")
	private String name;
	
	@NotNull(message = "Vui lòng nhập giới tính!")
	private boolean gender;
	
	private String phone;
	@Email(message = "Email không hợp lệ!")
	private String email;
	@NotBlank(message = "Vui lòng nhập địa chỉ")
	private String address;
	
	@NotBlank(message = "vui lòng nhập mã định danh")
	private String identifyNumber;
	private String birthday;
	
	@OneToMany(mappedBy = "customer", fetch =  FetchType.EAGER)
	private Collection<CustomerOrder> customerOrders;
	
	@OneToMany(mappedBy = "customer", fetch =  FetchType.EAGER)
	private Collection<CustomerDebt> customerDebts;
	
	@OneToMany(mappedBy = "customer", fetch =  FetchType.EAGER)
	private Collection<CustomerBill> customerBills;

	public Customer() {
		super();
	}

	public Customer(int id, GroupOfCustomer groupOfCustomer, String name, boolean gender, String phone, String email,
			String address, String identifyNumber, String birthday) {
		super();
		this.id = id;
		this.groupOfCustomer = groupOfCustomer;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.identifyNumber = identifyNumber;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GroupOfCustomer getGroupOfCustomer() {
		return groupOfCustomer;
	}

	public void setGroupOfCustomer(GroupOfCustomer groupOfCustomer) {
		this.groupOfCustomer = groupOfCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Collection<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(Collection<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Collection<CustomerDebt> getCustomerDebts() {
		return customerDebts;
	}

	public void setCustomerDebts(Collection<CustomerDebt> customerDebts) {
		this.customerDebts = customerDebts;
	}

	public Collection<CustomerBill> getCustomerBills() {
		return customerBills;
	}

	public void setCustomerBills(Collection<CustomerBill> customerBills) {
		this.customerBills = customerBills;
	}
	
	
}
