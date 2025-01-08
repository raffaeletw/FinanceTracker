package dev.raffaele.financetrackerultimate.repository;

import dev.raffaele.financetrackerultimate.dto.CategoryReport;
import dev.raffaele.financetrackerultimate.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.type = 'DEPOSIT' AND t.user.id = :userId")
    Double calculateTotalIncome(@Param("userId") int userId);

    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.type = 'EXPENSE' AND t.user.id = :userId")
    Double calculateTotalExpenses(@Param("userId") int userId);

    @Query("SELECT new dev.raffaele.financetrackerultimate.dto.CategoryReport(c.name, SUM(t.amount)) " +
            "FROM TransactionEntity t JOIN t.category c WHERE t.user.id = :userId AND c IS NOT NULL " +
            "GROUP BY c.name")
    List<CategoryReport> calculateExpensesByCategory(@Param("userId") int userId);
}
