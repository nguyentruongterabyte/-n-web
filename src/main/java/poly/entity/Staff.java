package poly.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Staff {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	@NotBlank(message = "Không được để trống họ tên!")
	private String name;
	
	@NotBlank(message = "Không được để trống số điện thoại!")
	private String phone;
	private boolean gender;
	
	@NotBlank(message = "Không được để trống email!")
	@Email(message= "Email không hợp lệ!")
	private String email;
	private String address;
	
	@NotBlank(message = "Không được để trống số CMND/CCCD")
	private String identifyNumber;
	private String func; // Chức vụ
	
	@NotBlank(message="Không được để trống ngày sinh!")
	@NotNull(message="Không được để trống ngày sinh!")
	private String birthday;
	private float salary;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(int id, String name, String phone, boolean gender, String email, String address, String identifyNumber,
			String func, String birthday, float salary) {
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
	
	
}
