package dev.raffaele.financetrackerultimate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String accountName;
        private double balance;
}
