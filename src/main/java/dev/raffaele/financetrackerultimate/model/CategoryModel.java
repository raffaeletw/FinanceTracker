package dev.raffaele.financetrackerultimate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private int id;
    private String name;
    private String description;
    private String icon;
}
