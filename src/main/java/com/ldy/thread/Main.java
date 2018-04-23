package com.ldy.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		final int threadCount = 2;
		final ExecutorService exService = Executors.newFixedThreadPool(threadCount);
		final ScoreBoard scoreBoard = new ScoreBoard();
		exService.execute(new ScoreUpdateThread(scoreBoard));
		exService.execute(new ScoreHealthThread(scoreBoard));			
		exService.shutdown();
	}
}
