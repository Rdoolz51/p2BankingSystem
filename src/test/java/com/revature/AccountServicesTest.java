package com.revature;
import com.revature.daos.*;
import com.revature.models.*;
import com.revature.services.AccountServices;
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
public class AccountServicesTest {

    @Mock
    AccountDAO accountDAO;
    @InjectMocks
    AccountServices accountServices;

    @Mock
    UserDAO userDAO;
    @InjectMocks
    UserServices userServices;
    @Mock
    AccountTypeDAO AccountType;
    @Mock
    LoanDAO loanDAO;
    @Mock
    CreditCardDAO creditCardDAO;


    @Test
    public void testRegisterAccount() {
        // Create a mock account
        User mockUser = Mockito.mock(User.class);
        AccountType mockAccountType = Mockito.mock(AccountType.class);
        Account mockAccount = new Account(10, "100000", mockAccountType, "4343", mockUser);

        // Configure the accountDAO mock behavior
        when(accountDAO.save(mockAccount)).thenReturn(mockAccount);

        // Call the registerAccount method
        Account result = accountServices.registerAccount(mockAccount);

        // Verify the result
        Assertions.assertNotNull(result, "Registered account should not be null");
        Assertions.assertEquals(mockAccount, result, "Registered account should match the mock account");
        verify(accountDAO, times(1)).save(mockAccount);
    }


    @Test
    public void testRegisterAccountWithNullAccount() {
        // Call the registerAccount method with a null account
        Assertions.assertThrows(NullPointerException.class, () -> accountServices.registerAccount(null),
                "Null account should throw NullPointerException");
        verify(accountDAO, never()).save(any(Account.class));
    }

    @Test
    public void testGetUserAccounts() {
        // Mock Role
        Role mockRole = Mockito.mock(Role.class);
        // Mock Address
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1,"John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");

        // Create a list of mock accounts associated with the user
        User mockUsers = Mockito.mock(User.class);
        AccountType mockAccountType = Mockito.mock(AccountType.class);
        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new Account(10, "100000", mockAccountType, "4343", mockUsers));
        mockAccounts.add(new Account(10, "100000", mockAccountType, "4343", mockUsers));

        // Configure the accountDAO mock behavior
        when(accountDAO.findByUser(mockUser)).thenReturn(mockAccounts);

        // Call the getUserAccounts method
        List<Account> result = accountServices.getUserAccounts(mockUser);

        // Verify the result
        Assertions.assertNotNull(result, "User accounts should not be null");
        Assertions.assertEquals(mockAccounts.size(), result.size(), "Number of user accounts should match the mock accounts");
        Assertions.assertEquals(mockAccounts, result, "User accounts should match the mock accounts");
        verify(accountDAO, times(1)).findByUser(mockUser);
    }
    @Test
    public void testDeposit() {

        // Create a mock user
        Role mockRole = Mockito.mock(Role.class);
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1, "John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");


        AccountType mockAccountType = Mockito.mock(AccountType.class);
        Account mockAccount = new Account(1,"100", mockAccountType, "4040", mockUser);

        // Define the deposit amount
        when(accountDAO.save(mockAccount)).thenReturn(mockAccount);
        // Call the deposit method
        Account result = accountServices.deposit(mockAccount, "50", mockUser);

        // Verify the result
        Assertions.assertNotNull(result, "Deposited account should not be null");
        Assertions.assertEquals(mockAccount, result, "Deposited account should match the mock account");
        Assertions.assertEquals("150", result.getBalance(), "Account balance should reflect the deposit amount");
        verify(accountDAO, times(1)).save(mockAccount);
    }
    @Test
    public void testWithdrawal(){
        // Create a mock user
        Role mockRole = Mockito.mock(Role.class);
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1, "John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");

        AccountType mockAccountType = Mockito.mock(AccountType.class);
        Account mockAccount = new Account(1,"100", mockAccountType, "4040", mockUser);
        String withdrawalAmount = "50.25";

        // Configure the accountDAO mock behavior
        when(accountDAO.save(mockAccount)).thenReturn(mockAccount);

        // Call the withdrawal method
        Account result = accountServices.withdrawal(mockAccount, withdrawalAmount, mockUser);

        // Verify the result
        Assertions.assertNotNull(result, "Withdrawn account should not be null");
        Assertions.assertEquals(mockAccount, result, "Withdrawn account should match the mock account");
        Assertions.assertEquals("49.75", result.getBalance(), "Account balance should reflect the withdrawal amount");
        verify(accountDAO, times(1)).save(mockAccount);
    }
    @Test
    public void testTransfer() {
        // Create mock accounts
        Role mockRole = Mockito.mock(Role.class);
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1, "John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");

        AccountType mockAccountType = Mockito.mock(AccountType.class);
        Account mockFromAccount = new Account(1,"1000", mockAccountType, "4040", mockUser);
        Account mockToAccount = new Account(5,"500", mockAccountType, "4040", mockUser);


        // Define the transfer amount
        String transferAmount = "100.00";

        // Configure the accountDAO mock behavior
        when(accountDAO.save(mockFromAccount)).thenReturn(mockFromAccount);
        when(accountDAO.save(mockToAccount)).thenReturn(mockToAccount);

        // Call the transfer method
        List<Account> result = accountServices.transfer(mockFromAccount, mockToAccount, transferAmount, mockUser);

        // Verify the result
        Assertions.assertNotNull(result, "Transfer result should not be null");
        Assertions.assertEquals(2, result.size(), "Transfer result should contain two accounts");

        Account transferredFromAccount = result.get(0);
        Account transferredToAccount = result.get(1);

        Assertions.assertEquals(mockFromAccount, transferredFromAccount, "Transferred from account should match the mock from account");
        Assertions.assertEquals(mockToAccount, transferredToAccount, "Transferred to account should match the mock to account");

        Assertions.assertEquals("900.00", transferredFromAccount.getBalance(), "Balance of the from account should reflect the transfer");
        Assertions.assertEquals("600.00", transferredToAccount.getBalance(), "Balance of the to account should reflect the transfer");

        verify(accountDAO, times(1)).save(mockFromAccount);
        verify(accountDAO, times(1)).save(mockToAccount);
    }
    @Test
    public void testLoanApplication() {
        // Create a mock user
        Role mockRole = Mockito.mock(Role.class);
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1, "John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");

        LoanType mockLoantype = Mockito.mock(LoanType.class);
        Status mockStatus = Mockito.mock(Status.class);

        // Create a mock loan
        Loan mockLoan = new Loan(1, "100000", mockLoantype, "20000","10",mockUser,mockStatus);

        // Configure the loanDAO mock behavior
        when(loanDAO.save(mockLoan)).thenReturn(mockLoan);

        // Call the loanApplication method
        Loan result = accountServices.loanApplication(mockLoan, mockUser);

        // Verify the result
        Assertions.assertNotNull(result, "Loan application result should not be null");
        Assertions.assertEquals(mockLoan, result, "Loan application result should match the mock loan");
        verify(loanDAO, times(1)).save(mockLoan);
    }
    @Test
    public void testCreditCardApplication(){
        Role mockRole = Mockito.mock(Role.class);
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1, "John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");

        Status mockStatus = Mockito.mock(Status.class);
        CreditCard mockCreditCard = new CreditCard(10, "5000", "456732123456", "09/23", "20000", "10", "100", mockUser, mockStatus);

        when(creditCardDAO.save(mockCreditCard)).thenReturn(mockCreditCard);

        // Call the creditCardApplication method
        CreditCard result = accountServices.creditCardApplication(mockCreditCard, mockUser);

        // Verify the result
        Assertions.assertNotNull(result, "Credit card application result should not be null");
        Assertions.assertEquals(mockCreditCard, result, "Credit card application result should match the mock credit card");
        verify(creditCardDAO, times(1)).save(mockCreditCard);
    }

}