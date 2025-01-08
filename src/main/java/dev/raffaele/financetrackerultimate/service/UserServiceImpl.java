package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.entity.UserEntity;
import dev.raffaele.financetrackerultimate.exception.UserNotFoundException;
import dev.raffaele.financetrackerultimate.model.UserModel;
import dev.raffaele.financetrackerultimate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        UserEntity userEntity = mapToEntity(userModel);
        UserEntity savedEntity = userRepository.save(userEntity);
        return mapToModel(savedEntity);
    }

    @Override
    public UserModel getUserById(int id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return mapToModel(userEntity);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel updateUser(int id, UserModel userModel) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        existingUser.setFirstName(userModel.getFirstName());
        existingUser.setLastName(userModel.getLastName());
        existingUser.setEmail(userModel.getEmail());
        existingUser.setAccountName(userModel.getAccountName());
        existingUser.setBalance(userModel.getBalance());

        UserEntity updatedEntity = userRepository.save(existingUser);
        return mapToModel(updatedEntity);
    }

    @Override
    public void deleteUser(int id) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(existingUser);
    }

    private UserEntity mapToEntity(UserModel userModel) {
        return new UserEntity(
                userModel.getId(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                userModel.getAccountName(),
                userModel.getBalance()
        );
    }

    private UserModel mapToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setEmail(userEntity.getEmail());
        userModel.setAccountName(userEntity.getAccountName());
        userModel.setBalance(userEntity.getBalance());
        return userModel;
    }
}
