package com.pji.alexa.util;

/**
 * @author bharath
 * Re-using Payment category codes from web-platform
 */
public enum PaymentCategoryCode {

    UNKNOWN(0, "UNKNOWN"),
    CASH(1, "CASH"),
    CHECK(2, "CHECK"),
    CREDIT_CARD(3, "CREDIT CARD"),
    GIFT_CARD(4, "GIFT CARD"),
    STORED_CARD(5, "Stored Card"),
    NI_CREDIT_CARD(6, "NI CREDIT CARD"),
    GOOGLE_WALLET(7, "Google Wallet"),
    PAYPAL(8, "PAYPAL"),
    VISA_CHECKOUT(9, "Visa Checkout"),
    APPLE_PAY(10, "Apple Pay");

    private int categoryId;
    private String categoryName;

    private PaymentCategoryCode(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    
}