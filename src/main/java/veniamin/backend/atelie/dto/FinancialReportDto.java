package veniamin.backend.atelie.dto;

import java.time.LocalDate;

// FinancialReportDto.java
public interface FinancialReportDto {
    Long getId();
    LocalDate getDateOrder();
    String getCutterName();
    String getClientName();
    Double getTotalOrderAmount();
}
