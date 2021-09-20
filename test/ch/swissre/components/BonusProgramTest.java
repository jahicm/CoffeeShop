package ch.swissre.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BonusProgramTest {

	static BonusProgram bonusProgram;
	static Order order, order2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		Product product3 = new Product(3, "Coffee", "L", 3.50, true, false, true);
		Product product4 = new Product(4, "Bacon Roll", "N/A", 4.50, false, false, false);
		Product product5 = new Product(5, "Freshly squeezed orange", "0.25L", 3.95, false, false, true);

		bonusProgram = new BonusProgram();
		bonusProgram.createStampCard(1);
		bonusProgram.createStampCard(2);

		order = new Order();
		order.validateOrder(3, 2, product3);
		order.validateOrder(4, 1, product4);
		order.validateOrder(5, 1, product5);

		order2 = new Order();
		order2.validateOrder(3, 2, product3);
		order2.validateOrder(4, 1, product4);
		order2.validateOrder(5, 5, product5);
	}

	@Test
	void testBonusCheck() {
		//Apply Loyalty card, every 5th drink is free
		bonusProgram.checkForBonus(1, order);
		boolean test = bonusProgram.bonusCardIdExists(1);

		StampCard stampCard = bonusProgram.getStampCard(1);
		int rewards = stampCard.getRewards();
		int stamps = stampCard.getNumberOfStamps();

		assertEquals(rewards, 0);
		assertEquals(stamps, 4);
		assertTrue(test);

	}

	@Test
	void testBonusCheck2() {

		bonusProgram.checkForBonus(2, order2);
		boolean test = bonusProgram.bonusCardIdExists(2);

		StampCard stampCard = bonusProgram.getStampCard(2);
		int rewards = stampCard.getRewards();
		int stamps = stampCard.getNumberOfStamps();

		assertEquals(rewards, 1);
		assertEquals(stamps, 2);
		assertTrue(test);

	}

	@Test
	void testExtrasFree() {
		// Buy beverage & Rolls and get next stuff for free
		bonusProgram.provideExtrasFree(order);
		assertEquals(order.getTotalOrderAmount(), 11.5);

	}
}
