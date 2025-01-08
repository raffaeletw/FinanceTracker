package dev.raffaele.financetrackerultimate.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private List<CategoryReport> categoryReports;

}

