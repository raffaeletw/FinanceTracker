package dev.raffaele.financetrackerultimate.model;

import dev.raffaele.financetrackerultimate.entity.CategoryEntity;
import dev.raffaele.financetrackerultimate.entity.UserEntity;
import dev.raffaele.financetrackerultimate.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {

    private int id;
    private double amount;
    private TransactionType type;
    private CategoryEntity category;
    private LocalDateTime createdOn;
    private UserEntity user;

}
