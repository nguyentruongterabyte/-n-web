package poly.entity;


import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class OrderDetail{
	
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 7830032371641218402L;

		@ManyToOne
		@JoinColumn(name = "orderId")
		private Order order;
		
		@ManyToOne
		@JoinColumn(name = "productId")
		private Product product;

		public Id() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Id(Order order, Product product) {
			super();
			this.order = order;
			this.product = product;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
	}
	
	@EmbeddedId
	private Id embeddedId;
	
	private int quantity;
	
	private int price;

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public OrderDetail(Id embeddedId, int quantity, int price) {
		super();
		this.embeddedId = embeddedId;
		this.quantity = quantity;
		this.price = price;
	}



	public Id getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(Id embeddedId) {
		this.embeddedId = embeddedId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}
	

	
}