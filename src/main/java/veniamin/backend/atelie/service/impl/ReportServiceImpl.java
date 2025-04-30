package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.dto.CompletedOrderDto;
import veniamin.backend.atelie.dto.CutterMonthOrdersDto;
import veniamin.backend.atelie.dto.FinancialReportDto;
import veniamin.backend.atelie.repository.ReportRepository;
import veniamin.backend.atelie.service.ReportService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<CompletedOrderDto> getCompletedOrdersByCutter(Long cutterId) {
        return reportRepository.getCompletedOrdersByCutter(cutterId);
    }

    @Override
    public List<FinancialReportDto> getFinancialReport(int day) {
        return reportRepository.getFinancialReportByDay(day);
    }

    @Override
    public List<CutterMonthOrdersDto> getCurrentMonthCutterOrders() {
        return reportRepository.getCurrentMonthCutterOrders();
    }
}
