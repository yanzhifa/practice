package com.ldy.password;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 */
public class PasswdQcParams {

    private int min[] = {2147483647, 2147483647, 2147483647, 7, 7};

    private int max = 40;

    private int passphraseWords = 3;

    private int matchLength = 4;

    private int similarDeny = 1;

    private int randomBits = 47;

    public int[] getMin() {
        return min;
    }

    public void setMin(int min[]) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPassphraseWords() {
        return passphraseWords;
    }

    public void setPassphraseWords(int passphraseWords) {
        this.passphraseWords = passphraseWords;
    }

    public int getMatchLength() {
        return matchLength;
    }

    public void setMatchLength(int matchLength) {
        this.matchLength = matchLength;
    }

    public int getSimilarDeny() {
        return similarDeny;
    }

    public void setSimilarDeny(int similarDeny) {
        this.similarDeny = similarDeny;
    }

    public int getRandomBits() {
        return randomBits;
    }

    public void setRandomBits(int randomBits) {
        this.randomBits = randomBits;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
