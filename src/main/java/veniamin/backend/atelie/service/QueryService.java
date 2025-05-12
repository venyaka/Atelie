package veniamin.backend.atelie.service;

import java.time.LocalDate;
import java.util.List;

public interface QueryService {

    List<String> findOrdersBySurname(String surname);

    List<String> countOrdersPerCutterCurrentYear();

    Integer getPurchasedFabricCount(String article);

    List<String> getOrdersDueByDate(LocalDate dueDate);

    List<String> getProductCategoriesByMonth(Integer month);
}
