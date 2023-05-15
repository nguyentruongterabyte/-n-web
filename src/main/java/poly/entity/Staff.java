package poly.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;


import org.hibernate.validator.constraints.Email;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Staff {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;

	@NotBlank(message = "Không được để trống họ tên!")

	private String name;

	@NotBlank(message = "Không được để trống số điện thoại!")
	private String phone;
	@NotBlank(message = "Vui lòng nhập giới tính!")
	private boolean gender;

	@NotBlank(message = "Không được để trống email!")
	@Email(message = "Email không hợp lệ!")
	private String email;
	@NotBlank(message = "Vui lòng nhập địa chỉ!")
	private String address;

	@NotBlank(message = "Vui lòng nhập mã định danh!")

	private String identifyNumber;
	@NotBlank(message = "Vui lòng chọn chức vụ!")
	private String func; // Chức vụ

	@NotBlank(message = "Vui lòng nhập ngày sinh!")
	private String birthday;
	@NotBlank(message = "Không ai làm không lương đâu")
	private float salary;
	
	private boolean resigned;

	@OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
	private Collection<VendorBill> vendorBills;
	
	@OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
	private Collection<Document> documents;

	public Staff() {
		super();
		vendorBills = new ArrayList<>();
		documents = new ArrayList<>();
	}

	public Staff(int id, String name, String phone, boolean gender, String email, String address, String identifyNumber,
			String func, String birthday, float salary, boolean resigned, Collection<VendorBill> vendorBills,
			Collection<Document> documents) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.identifyNumber = identifyNumber;
		this.func = func;
		this.birthday = birthday;
		this.salary = salary;
		this.resigned = resigned;
		this.vendorBills = vendorBills;
		this.documents = documents;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public boolean isResigned() {
		return resigned;
	}

	public void setResigned(boolean resigned) {
		this.resigned = resigned;
	}

	public Collection<VendorBill> getVendorBills() {
		return vendorBills;
	}

	public void setVendorBills(Collection<VendorBill> vendorBills) {
		this.vendorBills = vendorBills;
	}

	public Collection<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}

}
