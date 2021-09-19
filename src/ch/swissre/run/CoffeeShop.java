/*Major entry point CoffeeShop class from where the application is created.Class displays menu from the Menu class
 * and displays it in the loop
 */
package ch.swissre.run;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ch.swissre.components.Menu;
import ch.swissre.components.Order;
import ch.swissre.components.OrderEntry;
import ch.swissre.components.Product;

public class CoffeeShop {

	private static boolean status = true;
	private static String choice;
	private static DecimalFormat priceFormat = new DecimalFormat("0.00");

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Order order = new Order();
		Menu.displayMainMenu();

		do {
			System.out.print(
					" Press A to add new item to order,ENTER to complete order, C to cancel order, E to exit program:");
			choice = scanner.nextLine();

			switch (choice) {

			case "A":
				addItem(order);
				break;
			case "C":
				cancelOrder(order);
				break;
			case "":
				completeOrder(order);
				break;
			case "E":
				exitProgram();
				break;
			default:
				System.out.println("****Invalid input only valid (A,C,E or ENTER)****");
				Menu.displayMainMenu();
				break;

			}

		} while (status);

		scanner.close();
	}

	/* Switch status flag to false to exit program */
	private static void exitProgram() {
		status = false;
		System.out.println("**Exit program**");

	}

	/*
	 * Here we process and add major items from the Menu (in 1 to 5 range), if
	 * selected.
	 */
	private static void addItem(Order order) {

		Scanner scannerAdd = new Scanner(System.in);
		try {

			List<Product> products = Menu.getSetOfProducts();
			int id, quantity;

			System.out.print(" Enter product ID :");
			id = scannerAdd.nextInt();
			System.out.print(" Enter quantity :");
			quantity = scannerAdd.nextInt();

			Product selectedProduct = products.stream().filter(p -> p.getProductId() == id).findFirst().orElse(null);
			Product clonedProduct = (Product) selectedProduct.clone();
			order.validateOrder(id, quantity, clonedProduct);

			if (selectedProduct.isHasExtras()) {
				checkForExtras(order, products, clonedProduct, quantity);
			}

		} catch (NumberFormatException ex) {
			System.out.println("***Invalid input, please use only numbers in the range 1 to 5 ,thanks***");
			Menu.displayMainMenu();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Menu.displayMainMenu();
		}
	}

	/*
	 * In this method Extras are extracted from the order and validated.User gets
	 * prompted to select Extras (in 6 to 8 range).If user does not want Extras he
	 * just hits ENTER key
	 */
	private static void checkForExtras(Order order, List<Product> products, Product parentProduct, int quantity) {

		Scanner scannerAddExtras = new Scanner(System.in);
		try {
			Menu.displayExtraMenu();
			System.out.print(" Enter Extras ID (if any) and press ENTER:");
			String idString = scannerAddExtras.nextLine().trim();
			int id;

			if (!idString.equals("")) {
				id = Integer.parseInt(idString);
				Product selectedExtrasProduct = products.stream().filter(p -> p.getProductId() == id).findFirst()
						.orElse(null);
				order.validateExtrasOrder(id, selectedExtrasProduct, parentProduct, quantity);

			}

		} catch (NumberFormatException ex) {
			System.out.println("***Invalid input, please use only numbers in the range 5 to 8 ,thanks***");
			cancelOrder(order);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			cancelOrder(order);
		}

	}

	/*
	 * If user cancels the order from the menu, all info is deleted in Order class,
	 * Main menu start gets redisplayed.
	 */
	private static void cancelOrder(Order order) {

		System.out.println("**Canceled order**");
		order.cancelOrder();
		Menu.displayMainMenu();
	}

	/*
	 * Print the receipt of the order, if no elements return from order ,
	 * RuntimeException is thrown.
	 */
	private static void completeOrder(Order order) {

		try {

			Map<Integer, OrderEntry> receipt = order.getOrder();

			if (receipt.size() == 0) {
				throw new RuntimeException("**Order invalid, no items selected**");
			}
			System.out
					.println("*****************************************RECEIPT***************************************");
			System.out.printf("%-30s%-10s%-10s%-30s\n", "PRODUCT", "TYPE", "QUANTITY", "AMOUNT");
			System.out
					.println("***************************************************************************************");
			receipt.forEach((k, v) -> System.out.printf("%-30s%-10s%-10s%-30s\n", v.getProduct().getProductName(),
					v.getProduct().getSize(), v.getQuantity(), priceFormat.format(v.getAmount())));
			System.out
					.println("***************************************************************************************");
			System.out.println(
					"Total :" + priceFormat.format(order.getTotalOrderAmount()) + " " + priceFormat.getCurrency());
			System.out.println("Receipt ID :" + order.createReceiptId());
			System.out
					.println("***************************************************************************************");
			status = false;

		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
			Menu.displayMainMenu();
		} catch (Exception ex) {
			System.out.println("**Exception while complete the order***");
			Menu.displayMainMenu();
		}
	}
}
