/*Menu class contains all the data that will be displayed. Data is loaded from the static block only once, when this class is created*/
package ch.swissre.components;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Menu {

	private static List<Product> setOfProducts;
	private static Map<Integer, Product> mapOfProducts;
	private static DecimalFormat priceFormat = new DecimalFormat("0.00");

	static {

		Product product1 = new Product(1, "Coffee", "S", 2.50, true, false);
		Product product2 = new Product(2, "Coffee", "M", 3.00, true, false);
		Product product3 = new Product(3, "Coffee", "L", 3.50, true, false);
		Product product4 = new Product(4, "Bacon Roll", "N/A", 4.50, false, false);
		Product product5 = new Product(5, "Freshly squeezed orange", "0.25L", 3.95, false, false);
		Product product6 = new Product(6, "Extra milk", "N/A", 0.30, false, true);
		Product product7 = new Product(7, "Foamed milk", "N/A", 0.50, false, true);
		Product product8 = new Product(8, "Special roast coffee", "N/A", 0.90, false, true);

		setOfProducts = new Vector<Product>();
		setOfProducts.add(product1);
		setOfProducts.add(product2);
		setOfProducts.add(product3);
		setOfProducts.add(product4);
		setOfProducts.add(product5);
		setOfProducts.add(product6);
		setOfProducts.add(product7);
		setOfProducts.add(product8);

		mapOfProducts = new HashMap<Integer, Product>();
		mapOfProducts.put(1, product1);
		mapOfProducts.put(2, product2);
		mapOfProducts.put(3, product3);
		mapOfProducts.put(4, product4);
		mapOfProducts.put(5, product5);
		mapOfProducts.put(6, product6);
		mapOfProducts.put(7, product7);
		mapOfProducts.put(8, product8);
	}

	/*
	 * In this method we display main items from the menu. Loop is used to display
	 * all items with Id value 1 to 5. Boolean isExtras() is used to check if item
	 * is main.
	 */
	public static void displayMainMenu() {
		System.out.println("*******************************MAIN MENU****************************");
		System.out.printf("%-10s%-30s%-10s%-10s%-30s\n", "ID", "PRODUCT", "SIZE", "PRICE", "CURRENCY");
		System.out.println("********************************************************************");

		for (Product product : setOfProducts) {
			if (!product.isExtras())
				System.out.printf("%-10s%-30s%-10s%-10s%-30s\n", product.getProductId(), product.getProductName(),
						product.getSize(), priceFormat.format(product.getPrice()), priceFormat.getCurrency());
		}
		System.out.println("*******************************************************************");
	}

	/*
	 * In this method we display extras items from the menu. Loop is used to display
	 * all items with Id value 6 to 8.Boolean isExtras() is used to check if item is
	 * extra.
	 */
	public static void displayExtraMenu() {
		System.out.println("***************************EXTRAS*************************");
		System.out.printf("%-10s%-30s%-10s%-30s\n", "ID", "PRODUCT", "PRICE", "CURRENCY");
		System.out.println("**********************************************************");

		for (Product product : setOfProducts) {
			if (product.isExtras())
				System.out.printf("%-10s%-30s%-10s%-30s\n", product.getProductId(), product.getProductName(),
						priceFormat.format(product.getPrice()), priceFormat.getCurrency());
		}
		System.out.println("**********************************************************");
	}

	public static List<Product> getSetOfProducts() {
		return setOfProducts;
	}

	public static Map<Integer, Product> getMapOfProducts() {
		return mapOfProducts;
	}

}
