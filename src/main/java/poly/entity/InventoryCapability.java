package poly.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class InventoryCapability {
	
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name="ProductId")
		private Product product;
		
		@ManyToOne
		@JoinColumn(name="InventoryId")
		private Inventory inventory;

		public Id() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Id(Product product, Inventory inventory) {
			super();
			this.product = product;
			this.inventory = inventory;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Inventory getInventory() {
			return inventory;
		}

		public void setInventory(Inventory inventory) {
			this.inventory = inventory;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
	}
	
	@EmbeddedId
	private Id embeddedId;
	@NotNull(message = "Vui lòng nhập số lượng tối đa!")
	@DecimalMin(value = "0", message = "Số lượng tối đa phải lớn hơn 0")
	private int maxCount;
	@NotNull(message = "Vui lòng nhập số lượng tồn kì trước!")
	@DecimalMin(value = "0", message = "Số lượng tồn phải lớn hơn hoặc bằng 0")
	private int last;
	@NotNull(message = "Vui lòng nhập số lượng hiện tại!")
	@DecimalMin(value = "0", message = "Số lượng hiện phải lớn hơn hoặc bằng 0")
	private int currentCount;
	public InventoryCapability() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	
	
	public InventoryCapability(Id embeddedId, int maxCount, int last, int currentCount) {
		super();
		this.embeddedId = embeddedId;
		this.maxCount = maxCount;
		this.last = last;
		this.currentCount = currentCount;
	}

	public Id getEmbeddedId() {
		return embeddedId;
	}
	public void setEmbeddedId(Id embeddedId) {
		this.embeddedId = embeddedId;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

}

