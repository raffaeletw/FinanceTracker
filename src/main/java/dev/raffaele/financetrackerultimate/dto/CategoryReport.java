package dev.raffaele.financetrackerultimate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReport {
    private String categoryName;
    private double totalAmount;
}