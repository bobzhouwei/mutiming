package org.mutiming.common.utils;

import java.math.BigDecimal;

/**
 * @author: Wei.Zhou on 2019/6/26
 * Utilities for BigDecimal
 */
public class BigDecimalUtil {
    /**
     * Returns the bigger one between b1 and b2
     *
     * @param b1 a BigDecimal value to be compared
     * @param b2 another BigDecimal value to be compared
     * @return the BigDecimal value of bigger one
     */
    public static BigDecimal max(BigDecimal b1, BigDecimal b2) {
        return b1.max(b2);
    }

    /**
     * Returns the smaller one between b1 and b2
     *
     * @param b1 a BigDecimal value to be compared
     * @param b2 another BigDecimal value to be compared
     * @return the BigDecimal value of smaller one
     */
    public static BigDecimal min(BigDecimal b1, BigDecimal b2) {
        return b1.min(b2);
    }


}
