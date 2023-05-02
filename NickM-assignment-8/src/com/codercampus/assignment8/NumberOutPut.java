package com.codercampus.assignment8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberOutPut {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Assignment8 a8 = new Assignment8();

		List<CompletableFuture<List<Integer>>> numbers = new ArrayList<>();

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++) {
			CompletableFuture<List<Integer>> number = CompletableFuture.supplyAsync(() -> a8.getNumbers(), executor);
			numbers.add(number);
		}

		List<Integer> allNumbers = new ArrayList<>();
		for (CompletableFuture<List<Integer>> number : numbers) {
			allNumbers.addAll(number.get());

		}

		Map<Integer, Integer> countNums = new HashMap<>();
		for (Integer nums : allNumbers) {
			countNums.put(nums, countNums.getOrDefault(nums, 0) + 1);

		}

		for (int i = 0; i <= 14; i++) {
			System.out.println(i + "=" + countNums.getOrDefault(i, 0));
		}

		executor.shutdown();

	}

}
