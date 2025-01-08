package dev.raffaele.financetrackerultimate.repository;

import dev.raffaele.financetrackerultimate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t WHERE t.type = 'DEPOSIT' AND t.user.id = :userId")
    Double calculateUserIncome(@Param("userId") int userId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t WHERE t.type = 'EXPENSE' AND t.user.id = :userId")
    Double calculateUserExpenses(@Param("userId") int userId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t WHERE t.type = 'WITHDRAWAL' AND t.user.id = :userId")
    Double calculateUserWithdrawals(@Param("userId") int userId);


}
