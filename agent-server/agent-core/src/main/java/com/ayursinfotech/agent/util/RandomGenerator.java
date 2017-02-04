package com.ayursinfotech.agent.util;

import java.util.Random;

public final class RandomGenerator {

	public static String randomNumber(int size) {
		return String.valueOf(size < 1 ? 0 : new Random()
		.nextInt((9 * (int) Math.pow(10, size - 1)) - 1)
		+ (int) Math.pow(10, size - 1));
	}

	private RandomGenerator() {
	}

}
