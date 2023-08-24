package com.asianaidt.dutyfree.domain.stock.domain;

import lombok.Getter;

@Getter
public enum StockStatus {
    PROGRESS("PROGRESS"),
    COMPLETED("SUCCESS");

    private final String description;
    StockStatus(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
