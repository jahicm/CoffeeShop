/*Order is represented in this class. All items that are selected are added here*/
package ch.swissre.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Order {

	private int receiptId;
	private Map<Integer, OrderEntry> order;
	private double totalOrderAmount;
	private Random rand;

	public Order() {
		rand = new Random();
		order = new HashMap<Integer, OrderEntry>();

	}

	/* Generate Receipt Id using Random class */
	public int createReceiptId() {
		receiptId = rand.nextInt(1000);
		return receiptId;
	}

	/*
	 * Validate if the productId , and quantity have valid values. Quantity must not
	 * be negative or above 100 (unreasonably big)
	 */
	public void validateOrder(int id, int quantity, Product product) {
		if (id < 1 || id > 5) {
			throw new IllegalArgumentException("**Invalid product ID  , should be between 1 and 5**");
		} else if (quantity < 1 || quantity > 100) {
			throw new IllegalArgumentException("**Invalid product quantity  , should be between 1 and 100**");
		} else if (product == null) {
			throw new IllegalArgumentException("**Product is not found**");
		} else {

			addProductToOrder(quantity, product);
		}

	}

	/* Order entry is one record in order. Here it is created and added to order */
	private void addProductToOrder(int quantity, Product product) {
		OrderEntry orderEntry = new OrderEntry();
		orderEntry.setProduct(product);
		orderEntry.setQuantity(quantity);
		orderEntry.setAmount(quantity * product.getPrice());

		order.put(OrderEntry.createOrderId(), orderEntry);

		totalOrderAmount += orderEntry.getAmount();

	}

	/*
	 * Validated Extras in the order. If any Extras exists order price and entry are
	 * updated
	 */
	public void validateExtrasOrder(int id, Product product, Product parentProduct, int quantity) {

		if (id < 6 || id > 8) {
			throw new IllegalArgumentException("**Invalid extras ID  , should be between 5 and 8**");
		} else if (product == null) {
			throw new IllegalArgumentException("**Extras product is null**");

		} else if (!parentProduct.isExtrasAdded()) {
			OrderEntry updatedEntry = order.get(OrderEntry.getOrderId());
			Product prod = updatedEntry.getProduct();
			String prodName = prod.getProductName();
			prod.setProductName(prodName + "(" + product.getProductName() + ")");
			double updatedEntryAmount = updatedEntry.getAmount() + product.getPrice() * updatedEntry.getQuantity();
			updatedEntry.setAmount(updatedEntryAmount);
			totalOrderAmount += (product.getPrice() * updatedEntry.getQuantity());
			prod.setExtrasAdded(true);

		} else {
			Map<Integer, Product> newMap = Menu.getMapOfProducts();
			Product newProductEntry = newMap.get(parentProduct.getProductId());
			String updatedName = newProductEntry.getProductName() + "(" + product.getProductName() + ")";
			newProductEntry.setProductName(updatedName);
			addProductToOrder(quantity, newProductEntry);
		}

	}

	public Map<Integer, OrderEntry> getOrder() {
		return order;
	}

	public Set<Entry<Integer, OrderEntry>> getEntrySet() {
		return order.entrySet();
	}

	public double getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public void cancelOrder() {
		receiptId = 0;
		order.clear();
		totalOrderAmount = 0;

	}

	public void setOrderEntry(OrderEntry orderEntry) {

		this.order.put(OrderEntry.createOrderId(), orderEntry);
	}

}
