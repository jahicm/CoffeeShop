/*Class Product represents single product from the menu. It is used for both , main and extras products.*/
package ch.swissre.components;

import ch.swissre.interfaces.Clonable;

public class Product implements Clonable {

	private int productId;
	private String productName;
	private String size;
	private double price;
	private boolean hasExtras;
	private boolean isExtras;
	private boolean extrasAdded;
	private boolean isBeverage;

	public Product(int productId, String productName, String size, double price, boolean hasExtras, boolean isExtras,
			boolean isBeverage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.size = size;
		this.price = price;
		this.hasExtras = hasExtras;
		this.isExtras = isExtras;
		this.extrasAdded = false;
		this.isBeverage = isBeverage;

	}

	public int getProductId() {
		return productId;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof Product)) {
			return false;
		}

		Product prod = (Product) object;
		return this.productId == prod.getProductId();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + productName.hashCode();
		result = 31 * result + productId;
		result = 31 * result + size.hashCode();
		return result;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isHasExtras() {
		return hasExtras;
	}

	public void setHasExtras(boolean hasExtras) {
		this.hasExtras = hasExtras;
	}

	public boolean isExtras() {
		return isExtras;
	}

	public void setExtras(boolean isExtras) {
		this.isExtras = isExtras;
	}

	public boolean isExtrasAdded() {
		return extrasAdded;
	}

	public void setExtrasAdded(boolean extrasAdded) {
		this.extrasAdded = extrasAdded;
	}

	@Override
	/*
	 * Deep copy the master instance of Product so that cloned copy is used for changes.
	 */
	public Object clone() {
		try {
			return (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			return new Product(this.productId, this.productName, this.size, this.price, this.hasExtras, this.isExtras,
					this.isBeverage);
		}
	}

	public boolean isBeverage() {
		return isBeverage;
	}

}
