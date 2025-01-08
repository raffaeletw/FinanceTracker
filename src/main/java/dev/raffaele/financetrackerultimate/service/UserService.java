package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.model.UserModel;
import java.util.List;

public interface UserService {
    UserModel createUser(UserModel userModel);
    UserModel getUserById(int id);
    List<UserModel> getAllUsers();
    UserModel updateUser(int id, UserModel userModel);
    void deleteUser(int id);
}
