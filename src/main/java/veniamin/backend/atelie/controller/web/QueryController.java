package veniamin.backend.atelie.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.service.impl.QueryServiceImpl;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class QueryController {

    private final QueryServiceImpl queryService;


    @GetMapping("/queries")
    public String showQueriesPage() {
        return "query";
    }

    // 1. Поиск заказов по фамилии
    @GetMapping("/queries/surname")
    public String getOrdersBySurname(@RequestParam String surname, Model model) {
        List<String> results = queryService.findOrdersBySurname(surname);
        model.addAttribute("surnameResults", results);
        return "queries";
    }

    // 2. Кол-во заказов у закройщиков за текущий год
    @GetMapping("/queries/cutter-orders")
    public String getCutterOrderCounts(Model model) {
        List<String> results = queryService.countOrdersPerCutterCurrentYear();
        model.addAttribute("cutterOrders", results);
        return "queries";
    }

    // 3. Кол-во выкупленной ткани по артикулу
    @GetMapping("/queries/fabric-count")
    public String getFabricCount(@RequestParam String article, Model model) {
        String result = queryService.getPurchasedFabricCount(article).toString();
        model.addAttribute("fabricCount", result);
        return "queries";
    }

    // 4. Заказы до указанной даты
    @GetMapping("/queries/orders-due")
    public String getOrdersDueByDate(@RequestParam String dueDate, Model model) {
        LocalDate date = LocalDate.parse(dueDate);
        List<String> results = queryService.getOrdersDueByDate(date);
        model.addAttribute("ordersDue", results);
        return "queries";
    }

    // 5. Категории изделий по заказам в указанном месяце
    @GetMapping("/queries/categories")
    public String getCategoriesByMonth(@RequestParam int month, Model model) {
        List<String> results = queryService.getProductCategoriesByMonth(month);
        model.addAttribute("categories", results);
        return "queries";
    }
}
