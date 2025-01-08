package dev.raffaele.financetrackerultimate.entity;

import dev.raffaele.financetrackerultimate.entity.enums.TransactionType;
import dev.raffaele.financetrackerultimate.exception.InvalidTransactionTypeException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @Column(name="transaction_id")
    private int id;

    @Column(name="amount")
    private double amount;

    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryEntity category;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @ManyToOne
    private UserEntity user;

    @PrePersist
    @PreUpdate
    //transactions only have a category IF type = expense
    private void validateCategory() {
        if (type != TransactionType.EXPENSE && category != null) {
            throw new InvalidTransactionTypeException("Category can only be set for transactions of type EXPENSE.");
        }
    }


}
