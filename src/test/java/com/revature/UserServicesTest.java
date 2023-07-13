package com.revature;

import com.revature.daos.UserDAO;
import com.revature.models.*;
import com.revature.services.UserServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServicesTest {
    @Mock
    UserDAO userDAO;

    @InjectMocks
    UserServices userServices;

    @Test
    public void test_findAllUser_ReturnAllAvailableUser() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();

        userList.add(user1);
        userList.add(user2);
        when(userDAO.findAll()).thenReturn(userList);

        int actualSize = userServices.getAllUsers().size();
        verify(userDAO, times(1)).findAll();

        Assertions.assertEquals(2, actualSize);
    }

    @Test
    public void TestGetUserById() {
        Role mockRole = new Role(1, "Admin");
        State mockState = new State(1, "Alabama");
        ZipCode mockZipCode = new ZipCode(1, "73460");
        Address mockAddress = new Address(1, "123 paradise st", "Denver", mockState, mockZipCode);
        User user = new User("Justin", "Paxton", "Jpaxton@test.com", "password", "720 212 2323", mockRole, mockAddress, "100000");

        Assertions.assertNull(userServices.getUserById(0));
        Assertions.assertNull(userServices.getUserById(-4));
        Assertions.assertFalse(user.getId() < 0, "ID should not be negative");
    }

    @Test
    public void testGetUserByEmailWithNullEmail() {
        String email = null;

        User result = userServices.getUserByEmail(email);

        Assertions.assertNull(result, "User should be null");
        verify(userDAO, never()).findByEmail(anyString());
    }

    @Test
    public void testGetUserByEmailWithEmptyEmail() {
        String email = "";

        User result = userServices.getUserByEmail(email);

        Assertions.assertNull(result, "User should be null");
        verify(userDAO, never()).findByEmail(anyString());
    }

    @Test
    public void testUpdateUser(){
        // Mock Role
        Role mockRole = Mockito.mock(Role.class);
        // Mock Address
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1,"John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");
        User mockUpdatedUser = new User(1,"Temesgen", "Halilu", "Thailu@test.com", "password", "0987654321", mockRole, mockAddress, "90000");

        // Configure the userDAO mock behavior
        when(userDAO.existsById(mockUser.getId())).thenReturn(true);
        when(userDAO.save(mockUser)).thenReturn(mockUpdatedUser);

        // Call the updateUser method
        User result = userServices.updateUser(mockUser, mockUpdatedUser);

        // Verify the result
        Assertions.assertNotNull(result, "Updated user should not be null");
        Assertions.assertEquals(mockUpdatedUser, result, "Updated user should match the mock updated user");
        verify(userDAO, times(1)).existsById(mockUser.getId());
        verify(userDAO, times(1)).save(mockUser);

    }

    @Test
    public void testDeleteUser_UserExists() {

        // Mock Role
        Role mockRole = Mockito.mock(Role.class);

        // Mock Address
        Address mockAddress = Mockito.mock(Address.class);

        // Arrange
        User existingUser = new User(1,"John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");
        when(userDAO.existsById(existingUser.getId())).thenReturn(true);

        // Act
        boolean result = userServices.deleteUser(existingUser);

        // Assert
        Assertions.assertTrue(result, "Deletion should succeed and return true");
        verify(userDAO, times(1)).existsById(existingUser.getId());
        verify(userDAO, times(1)).delete(existingUser);
    }
}
