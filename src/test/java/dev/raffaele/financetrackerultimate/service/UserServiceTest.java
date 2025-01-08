package dev.raffaele.financetrackerultimate.service;

import dev.raffaele.financetrackerultimate.entity.UserEntity;
import dev.raffaele.financetrackerultimate.model.UserModel;
import dev.raffaele.financetrackerultimate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUserTest() {
        UserModel userModel = new UserModel(1, "Raffaele", "Chiarolanza",
                "raff@gmail.com", "raff123", 20000);
        UserEntity userEntity = new UserEntity(1, "Raffaele", "Chiarolanza",
                "raff@gmail.com", "raff123", 20000);

        when(userRepository.save(ArgumentMatchers.any(UserEntity.class))).thenReturn(userEntity);

        UserModel createdUser = userService.createUser(userModel);

        assertNotNull(createdUser);
        assertEquals("Raffaele", createdUser.getFirstName());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testGetAllUsers() {
        List<UserEntity> mockEntities = List.of(
                new UserEntity(1, "John", "Doe", "john.doe@example.com", "john123", 1000.0),
                new UserEntity(2, "Jane", "Smith", "jane.smith@example.com", "jane123", 2000.0)
        );

        when(userRepository.findAll()).thenReturn(mockEntities);

        List<UserModel> userModels = userService.getAllUsers();

        assertNotNull(userModels);
        assertEquals(2, userModels.size());
        assertEquals("Jane", userModels.get(1).getFirstName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        UserEntity existingUser = new UserEntity(1, "John", "Doe", "john.doe@example.com", "john123", 1000.0);
        UserEntity updatedUser = new UserEntity(1, "Johnny", "Doe", "johnny.doe@example.com", "johnny123", 1500.0);
        UserModel updateRequest = new UserModel(1, "Johnny", "Doe", "johnny.doe@example.com", "johnny123", 1500.0);

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUser);

        UserModel updatedUserModel = userService.updateUser(1, updateRequest);

        assertNotNull(updatedUserModel);
        assertEquals("Johnny", updatedUserModel.getFirstName());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testDeleteUser() {
        UserEntity existingUser = new UserEntity(1, "John", "Doe", "john.doe@example.com", "john123", 1000.0);

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        userService.deleteUser(1);

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).delete(existingUser);
    }
}
