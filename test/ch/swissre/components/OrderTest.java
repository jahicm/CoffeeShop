package ch.swissre.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderTest {

	static Order order;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		order = new Order();
	}

	@Test
	void testExceptionValidateOrderId() {
		Product product1 = new Product(1, "Coffee", "S", 2.50, true, false);
		assertThrows(IllegalArgumentException.class, () -> {
			order.validateOrder(-1, 2, product1);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			order.validateOrder(9, 2, product1);
		});
	}

	@Test
	void testExceptionValidateOrderQuantity() {
		Product product1 = new Product(1, "Coffee", "S", 2.50, true, false);
		assertThrows(IllegalArgumentException.class, () -> {
			order.validateOrder(1, -2, product1);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			order.validateOrder(1, 101, product1);
		});
	}

	@Test
	void testExceptioValidateOrdernNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			order.validateOrder(1, 2, null);
		});

	}

	@Test
	void testOrderAmountCorrect() {
		Product product1 = new Product(1, "Coffee", "S", 2.50, true, false);
		Product product2 = new Product(2, "Coffee", "M", 3.00, true, false);
		Product product3 = new Product(3, "Coffee", "L", 3.50, true, false);
		order.validateOrder(1, 2, product1);
		order.validateOrder(2, 1, product2);
		order.validateOrder(3, 2, product3);

		assertEquals(15.0, order.getTotalOrderAmount());

	}

	@Test
	void testExceptionValidateExtrasOrder() {
		Product product6 = new Product(6, "Extra milk", "N/A", 0.30, false, true);
		Product product7 = new Product(7, "Foamed milk", "N/A", 0.50, false, true);
		assertThrows(IllegalArgumentException.class, () -> {
			order.validateExtrasOrder(5, product7, product6, 1);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			order.validateExtrasOrder(9, product7, product6, 1);
		});
	}

}
