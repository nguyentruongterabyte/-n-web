package poly.entity;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class InOutInventoryDetail {
	@Embeddable
	public static class Id implements Serializable{
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name="OrderId")
		private Order order;
		
		@ManyToOne
		@JoinColumn(name="InOutInventoryId", referencedColumnName = "Id")
		private InOutInventory inOutInventory;

		public Id() {
			super();
		}

		public Id(Order order, InOutInventory inOutInventory) {
			super();
			this.order = order;
			this.inOutInventory = inOutInventory;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public InOutInventory getInOutInventory() {
			return inOutInventory;
		}

		public void setInOutInventory(InOutInventory inOutInventory) {
			this.inOutInventory = inOutInventory;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	}
	
	@EmbeddedId
	private Id embeddedId;

	public InOutInventoryDetail() {
		super();
	}

	public InOutInventoryDetail(Id embeddedId) {
		super();
		this.embeddedId = embeddedId;
	}

	public Id getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(Id embeddedId) {
		this.embeddedId = embeddedId;
	}
	
}
