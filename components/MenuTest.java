package ch.swissre.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MenuTest {

	static Menu menu;
	static List<Product> listOfProducts;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		listOfProducts = Menu.getSetOfProducts();
	}

	@Test
	void testCollection() {
		assertEquals(8,listOfProducts.size());
	}
	@Test
	void testMainMenu() {
		Menu.displayMainMenu();
		assertFalse(listOfProducts.get(0).isExtras());
		assertFalse(listOfProducts.get(1).isExtras());
		assertFalse(listOfProducts.get(2).isExtras());
		assertFalse(listOfProducts.get(3).isExtras());
		assertFalse(listOfProducts.get(4).isExtras());
	}
	@Test
	void testExtraMenu() {
		Menu.displayExtraMenu();
		assertTrue(listOfProducts.get(5).isExtras());
		assertTrue(listOfProducts.get(6).isExtras());
		assertTrue(listOfProducts.get(7).isExtras());
		
	}
}
