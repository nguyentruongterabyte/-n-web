package poly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Document {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Vui lòng nhập loại đơn!")
	private String type;
	
	@NotNull(message = "Vui lòng nhập mã nhân viên tạo đơn!")
	@ManyToOne
	@JoinColumn(name ="Creator")
	private Staff staff;
	
	@NotBlank(message = "Vui lòng nhập ngày tạo")
	private String createDate;

	public Document() {
		super();
	}

	public Document(int id, String type, Staff staff, String createDate) {
		super();
		this.id = id;
		this.type = type;
		this.staff = staff;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
