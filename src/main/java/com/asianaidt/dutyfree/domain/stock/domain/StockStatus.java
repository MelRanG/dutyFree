package com.asianaidt.dutyfree.domain.stock.domain;

import lombok.Getter;

@Getter
public enum StockStatus {
    PROGRESS("PROGRESS"),
    COMPLETED("SUCCESS");

    private String value;

    StockStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
