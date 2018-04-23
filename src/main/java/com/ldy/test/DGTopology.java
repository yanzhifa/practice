package com.ldy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum DGTopology {
	PHOENIX(0, 4), QUANTA(0, 6), R630(0, 5, 5), R730(0, 4, 8, 12, 4), R730xd(12, 13, 7);

	private DGTopology(int... cacheSlots) {
		this.cacheSlots = cacheSlots;
		for (int i = 0; i < cacheSlots.length; i++) {
			if (i == cacheSlots.length - 1) {
				numberOfDisksPerDG = cacheSlots[i];
			} else {
				result.put(cacheSlots[i], new ArrayList<Integer>());
			}
		}
		populateDGMapping();
	}

	private Map<Integer, List<Integer>> populateDGMapping() {
		if (result == null) {
			result = new LinkedHashMap<Integer, List<Integer>>();
			if (Arrays.asList(cacheSlots).contains(13)) {
				for (int i = 0; i < 12; i++) {
					if (i < 6) {
						result.get(12).add(i);
					} else {
						result.get(13).add(i);
					}
				}
			} else {
				result.forEach((cache, capacities) -> {
					for (int i = cache + 1; i < cache + numberOfDisksPerDG; i++) {
						capacities.add(i);
					}
				});
			}
		}
		return result;
	}

	public int[] getCacheSlots() {
		return cacheSlots;
	}

	public Map<Integer, List<Integer>> getCacheCapacityMapping() {
		return result;
	}

	private Map<Integer, List<Integer>> result = new LinkedHashMap<Integer, List<Integer>>();

	private int[] cacheSlots;

	private int numberOfDisksPerDG;
}
