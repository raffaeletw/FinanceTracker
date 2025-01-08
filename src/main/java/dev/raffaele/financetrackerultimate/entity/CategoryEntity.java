package dev.raffaele.financetrackerultimate.entity;

/*
could have been an enum or attribute in transaction, but:
in this way we can 1)query 2)add user-defined categories
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class CategoryEntity {

    @Id
    @Column(name="category_id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="icon")
    private String icon;


}




















