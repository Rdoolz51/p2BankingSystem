package com.revature;

import com.revature.daos.*;
import com.revature.models.*;
import com.revature.services.AccountServices;
import com.revature.services.AdminServices;
import com.revature.services.UserServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AdminServicesTest {
    @Mock
    AccountDAO accountDAO;
    @Mock
    LoanDAO loanDAO;
    @InjectMocks
    AdminServices adminServices;
    @Mock
    StatusDAO statusDAO;

    @Mock
    CreditCardDAO creditCardDAO;

    @Mock
    UserDAO userDAO;
    @InjectMocks
    UserServices userServices;

    @Test
    @WithMockUser(roles = "Admin")
    public void testGetAllLoan (){
        List<Loan> mockLoans = new ArrayList<>();
        // Add mock loans to the list
        Loan mockLoan1 = Mockito.mock(Loan.class);
        Loan mockLoan2 = Mockito.mock(Loan.class);
        // Add the mock loan to the list
        mockLoans.add(mockLoan1);
        mockLoans.add(mockLoan2);

        // Configure the loanDAO mock behavior
        when(loanDAO.findAll()).thenReturn(mockLoans);

        // Call the getAllLoans method
        List<Loan> result = adminServices.getAllLoans();

        // Verify the result
        Assertions.assertNotNull(result, "Returned loan list should not be null");
        Assertions.assertEquals(mockLoans, result, "Returned loan list should match the mock loan list");
        verify(loanDAO, times(1)).findAll();

    }
    @Test
    @WithMockUser(roles = "Admin")
    public void testGetAllCreditCards() {
        // Create a list of mock credit card objects
        List<CreditCard> mockCreditCards = new ArrayList<>();
        CreditCard mockCreditCard1 = Mockito.mock(CreditCard.class);
        CreditCard mockCreditCard2 = Mockito.mock(CreditCard.class);
        mockCreditCards.add(mockCreditCard1);
        mockCreditCards.add(mockCreditCard2);

        // Configure the creditCardDAO mock behavior
        when(creditCardDAO.findAll()).thenReturn(mockCreditCards);

        // Call the getAllCreditCards method
        List<CreditCard> result = adminServices.getAllCreditCards();

        // Verify the result
        Assertions.assertNotNull(result, "Returned credit card list should not be null");
        Assertions.assertEquals(mockCreditCards.size(), result.size(), "Returned list should have the same size as mockCreditCards");
        verify(creditCardDAO, times(1)).findAll();

    }
    @Test
    public void testUpdateLoanStatus() {
        // Mock the loan object
        Loan mockLoan = Mockito.mock(Loan.class);
        Status mockStatus = Mockito.mock(Status.class);

        // Set up the mock behavior
        when(mockLoan.getStatus()).thenReturn(mockStatus);
        when(mockStatus.getStatus()).thenReturn("Approved");
        when(loanDAO.save(mockLoan)).thenReturn(mockLoan);

        // Call the updateLoanStatus method
        Loan result = adminServices.updateLoanStatus(mockLoan);

        // Verify the result
        Assertions.assertNotNull(result, "Updated loan should not be null");
        Assertions.assertEquals(mockLoan, result, "Updated loan should match the mock loan");
        verify(loanDAO, times(1)).save(mockLoan);
    }
    @Test
    public void testUpdateCreditCardStatus(){
        //mock credit object
        CreditCard mockCreditCard = Mockito.mock(CreditCard.class);
        Status mockStatus = Mockito.mock(Status.class);

        when(mockCreditCard.getStatus()).thenReturn(mockStatus);
        when(mockStatus.getStatus()).thenReturn("Approved");
        when(creditCardDAO.save(mockCreditCard)).thenReturn(mockCreditCard);

        CreditCard result = adminServices.updateCreditCardStatus(mockCreditCard);

        Assertions.assertNotNull(result,"updated CreditCard should not be null");
        Assertions.assertEquals(mockCreditCard, result, "updated mock should match the mock CreditCard");
        verify(creditCardDAO, times(1)).save(mockCreditCard);

    }

    @Test
    public void testGetAllUserLoanById() {
        Role mockRole = Mockito.mock(Role.class);
        Address mockAddress = Mockito.mock(Address.class);
        User mockUser = new User(1, "John", "Doe", "johndoe@test.com", "password", "1234567890", mockRole, mockAddress, "100000");

        List<Loan> mockLoans = new ArrayList<>();
        LoanType mockLoanType = Mockito.mock(LoanType.class);
        Status mockStatus = Mockito.mock(Status.class);

        // Create mock loans
        Loan mockLoan1 = new Loan(1, "100000", mockLoanType, "20000", "10", mockUser, mockStatus);
        Loan mockLoan2 = new Loan(2, "100000", mockLoanType, "20000", "10", mockUser, mockStatus);

        mockLoans.add(mockLoan1);
        mockLoans.add(mockLoan2);

        when(loanDAO.findByUser_Id(1)).thenReturn(mockLoans);

        List<Loan> result = adminServices.getAllUserLoansById(1);

        Assertions.assertNotNull(result, "Returned loan list should not be null");
        Assertions.assertEquals(mockLoans, result, "Returned loan list should match the mock loan list");
        verify(loanDAO, times(1)).findByUser_Id(1);
    }



}
