/*This class creates single order entry in the Order class. For each item one OrderEntry is created.*/
package ch.swissre.components;

public class OrderEntry {

	private int quantity;
	private Product product;
	private double amount;
	private String extras;
	private static int orderId = 0;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public static int createOrderId() {
		return ++orderId;
	}

	public static int getOrderId() {
		return orderId;
	}

}
