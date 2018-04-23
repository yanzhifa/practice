package com.ldy.thread;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ScoreBoard {
    private boolean scoreUpdated = false;
    private int score = 0;
    String health = "Not Available";
    final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    public String getMatchHealth() {
        rrwl.readLock().lock();
        if (scoreUpdated) {
            rrwl.readLock().unlock();
            rrwl.writeLock().lock();
            try {
                if (scoreUpdated) {
                    score = fetchScore();
                    scoreUpdated = false;
                }
                rrwl.readLock().lock();
            } finally {
                rrwl.writeLock().unlock();
            }
        }
        try {
            if (score % 2 == 0) {
                health = "Bad Score";
            } else {
                health = "Good Score";
            }
        } finally {
            rrwl.readLock().unlock();
        }
        return health;
    }

    public void updateScore() {
        try {
            rrwl.writeLock().lock();
            // perform more task
            scoreUpdated = true;
        } finally {
            rrwl.writeLock().unlock();
        }
    }

    private int fetchScore() {
        Calendar calender = Calendar.getInstance();
        return calender.get(Calendar.MILLISECOND);
    }
}
