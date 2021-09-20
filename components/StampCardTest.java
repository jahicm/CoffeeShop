package ch.swissre.components;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StampCardTest {

	private static StampCard stampCard;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		stampCard = new StampCard(1);
	}

	@Test
	void testStampCard1() {
		stampCard.setStamps(2);
		stampCard.addStamp(3);
		
		assertEquals(stampCard.getRewards(),1);
		assertEquals(stampCard.getNumberOfStamps(),0);
	}
	@Test
	void testStampCard2() {
		stampCard.setStamps(4);
		stampCard.addStamp(3);
		
		assertEquals(stampCard.getRewards(),1);
		assertEquals(stampCard.getNumberOfStamps(),2);
	}
	@Test
	void testStampCard3() {
		stampCard.setStamps(3);
		stampCard.addStamp(1);
		
		assertEquals(stampCard.getRewards(),0);
		assertEquals(stampCard.getNumberOfStamps(),4);
	}
}
