package org.mutiming.trade;

class TradeTestData {
    // Positive cases
    static final String NO_DISCOUNT_TOTAL_200 = "[\"001\",\"001\"]";
    static final String DISCOUNT_3_FOR_200 = "[\"001\",\"001\",\"001\"]";
    static final String DISCOUNT_TOTAL_300 = "[\"001\",\"001\",\"001\",\"001\"]";
    static final String DISCOUNT_TOTAL_600 = "[\"001\",\"001\",\"001\",\"001\",\"001\",\"001\",\"001\",\"001\"]";
    static final String WATCH_002_1_FOR_80 = "[\"002\"]";
    static final String WATCH_002_DISCOUNT_2_FOR_120 = "[\"002\",\"002\"]";
    static final String WATCH_002_3_TOTAL_200 = "[\"002\",\"002\",\"002\"]";
    static final String MULTIPLE_CATEGORY_NO_DISCOUNT_TOTAL_330 = "[\"001\",\"002\",\"001\",\"003\"]";
    static final String MULTIPLE_CATEGORY_DISCOUNT_TOTAL_320 = "[\"002\",\"001\",\"002\",\"001\",\"001\"]";

    // negative cases
    static final String EMPTY_LIST_TOTAL_0 = "[]";
    static final String EMPTY_ID_TOTAL_0 = "[\"\"]";
    static final String BLANK_ID_TOTAL_0 = "[\" \"]";
    static final String ID_NOT_FOUND_1 = "[\"002\",\"000\",\"001\",\"001\"]";
    static final String ID_NOT_FOUND_2 = "[\"002\",\"001\",\"1\",\"001\"]";
    static final String ID_NOT_FOUND_3 = "[\"002\",\"02\"]";
    static final String ID_NOT_FOUND_4 = "[\"005\",\"001\"]";

}
