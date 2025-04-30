package veniamin.backend.atelie.dto;

import java.time.LocalDate;

// CompletedOrderDto.java
public interface CompletedOrderDto {
    Long getId();
    LocalDate getDateOrder();
    String getClientName();
    String getClientAddress();
    String getClientPhone();
    Double getBonus();
    String getProductName();
}
