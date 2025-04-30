package veniamin.backend.atelie.service;


import veniamin.backend.atelie.dto.CompletedOrderDto;
import veniamin.backend.atelie.dto.CutterMonthOrdersDto;
import veniamin.backend.atelie.dto.FinancialReportDto;

import java.util.List;

public interface ReportService {
    List<CompletedOrderDto> getCompletedOrdersByCutter(Long cutterId);
    List<FinancialReportDto> getFinancialReport(int day);
    List<CutterMonthOrdersDto> getCurrentMonthCutterOrders();
}
