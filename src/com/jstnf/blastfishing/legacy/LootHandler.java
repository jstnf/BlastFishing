package com.jstnf.blastfishing.legacy;

import java.util.LinkedList;

public class LootHandler {

	private LinkedList<String> loot;
	private LinkedList<Integer> weights;
	public int totalWeight;
	
	public LootHandler() {
		loot = new LinkedList<String>();
		weights = new LinkedList<Integer>();
		totalWeight = 0;
	}
	
	public void add(String material, int weight) {
		loot.add(material);
		weights.add(weight);
		totalWeight += weight;
	}
	
	public String get(int index) {
		int selector = 0, parser = 0;
		while (parser < loot.size() && selector < index) {
			selector += weights.get(parser);
			parser++;
		}
		return loot.get(parser - 1);
	}
	
}
