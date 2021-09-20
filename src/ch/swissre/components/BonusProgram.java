/*Bonus Program provides stamp card for xustomers. It checks if customer has 5 stamps for free drink
 * or snack + beverage gives free drink with extras.
 */
package ch.swissre.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BonusProgram {

	private static Map<Integer, StampCard> stampCardList;

	private StampCard stampCard;

	public BonusProgram() {
		stampCardList = new HashMap<Integer, StampCard>();

	}

	/* Check if bonus card exists for the existing client */
	public boolean bonusCardIdExists(int stampCardId) {
		StampCard stampCard = stampCardList.get(stampCardId);
		return (stampCard == null ? false : true);
	}

	public StampCard getStampCard(int stampCardId) {
		return stampCardList.get(stampCardId);
	}

	public void createStampCard(int stampCardId) {
		stampCard = new StampCard(stampCardId);
		stampCardList.put(stampCardId, stampCard);
	}

	/* Check if the stamp card already has rewards */
	public void checkForBonus(int stampCardId, Order order) {
		if (bonusCardIdExists(stampCardId)) {
			StampCard stampCard = stampCardList.get(stampCardId);
			List<OrderEntry> list = order.getEntrySet().stream().map(v -> v.getValue()).collect(Collectors.toList());
			Stream<OrderEntry> streamOfBeverages = list.stream().filter(p -> p.getProduct().isBeverage() == true);
			int sumOfBeverages = streamOfBeverages.mapToInt(p -> p.getQuantity()).sum();
			stampCard.addStamp(sumOfBeverages);
			stampCardList.put(stampCardId, stampCard);
			provideReward(order, stampCard);
		}
	}

	/*
	 * If customer buys 5 drinks, 1 is free.Use Stream to filter only beverages and
	 * apply rabbat on every 5th.
	 */
	private void provideReward(Order order, StampCard stampCard) {

		int rewards = stampCard.getRewards();
		if (rewards > 0) {
			System.out.println("Apply Loyalty card, every 5th drink is free");
			List<OrderEntry> list = order.getEntrySet().stream().map(v -> v.getValue()).collect(Collectors.toList());
			Stream<OrderEntry> streamOfReward = list.stream().filter(p -> p.getProduct().isBeverage() == true);
			List<OrderEntry> listOfBeverages = streamOfReward.collect(Collectors.toList());
			Collections.reverse(listOfBeverages);

			for (int i = 0, j = listOfBeverages.size(); i < j && rewards > 0; i++) {

				OrderEntry freeBeverageTemp = listOfBeverages.get(i);
				Product freeBeverage = freeBeverageTemp.getProduct();
				double price = freeBeverageTemp.getAmount() / freeBeverageTemp.getQuantity();
				freeBeverage.setPrice(-price);
				order.validateOrder(freeBeverage.getProductId(), 1, freeBeverage);
				stampCard.setRewards(rewards);
				--rewards;
			}
		}

	}

	/* If customer buys snack and beverage, next item is free */
	public void provideExtrasFree(Order order) {

		Map<Integer, OrderEntry> orderEntryMap = order.getOrder();
		List<OrderEntry> entries = new ArrayList<OrderEntry>(orderEntryMap.values());

		List<OrderEntry> list = order.getEntrySet().stream().map(v -> v.getValue()).collect(Collectors.toList());
		Stream<OrderEntry> streamOfBeverages = list.stream().filter(p -> p.getProduct().isBeverage() == true);
		Stream<OrderEntry> streamOfSnacks = list.stream().filter(p -> p.getProduct().getProductId() == 4);
		Stream<OrderEntry> streamOfTotal = list.stream()
				.filter(p -> p.getProduct().getProductId() == 4 || p.getProduct().isBeverage() == true);

		int numberOfItemsInOrder = streamOfTotal.mapToInt(p -> p.getQuantity()).sum();
		List<OrderEntry> listOfSelectedItems = entries.stream()
				.filter(p -> p.getProduct().getProductId() == 4 || p.getProduct().isBeverage() == true)
				.collect(Collectors.toList());

		if ((numberOfItemsInOrder > 2) && (streamOfBeverages.count() > 0) && (streamOfSnacks.count() > 0)) {
			System.out.println("Buy beverage & Rolls and get next stuff for free");
			OrderEntry orderEntryTemp = listOfSelectedItems.get(listOfSelectedItems.size() - 1);
			Product freeProduct = orderEntryTemp.getProduct();
			double price = orderEntryTemp.getAmount() / orderEntryTemp.getQuantity();
			freeProduct.setPrice(-price);
			order.validateOrder(freeProduct.getProductId(), 1, freeProduct);
		}

	}
}
