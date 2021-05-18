package com.deepblue.inaction.system_out_error.chapter_03_share.thread_007_OneValueCache;

import java.math.BigInteger;
import java.util.Arrays;

public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
    }

    public BigInteger[] getFactors(BigInteger i) {
        if(lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
