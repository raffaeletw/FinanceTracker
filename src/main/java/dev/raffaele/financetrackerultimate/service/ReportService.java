package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.dto.CategoryReport;
import dev.raffaele.financetrackerultimate.dto.ReportDTO;
import dev.raffaele.financetrackerultimate.exception.UserNotFoundException;
import dev.raffaele.financetrackerultimate.repository.TransactionRepository;
import dev.raffaele.financetrackerultimate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public ReportDTO generateReport(int userId) {
        double balance = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException())
                .getBalance();

        Double totalIncome = transactionRepository.calculateTotalIncome(userId);
        Double totalExpenses = transactionRepository.calculateTotalExpenses(userId);
        List<CategoryReport> categoryReports = transactionRepository.calculateExpensesByCategory(userId);

        totalIncome = (totalIncome != null) ? totalIncome : 0.0;
        totalExpenses = (totalExpenses != null) ? totalExpenses : 0.0;

        ReportDTO report = new ReportDTO();
        report.setBalance(balance);
        report.setTotalIncome(totalIncome);
        report.setTotalExpenses(totalExpenses);
        report.setCategoryReports(categoryReports);

        return report;
    }

}
