package com.revature.Exceptions;

public class InsufficientFundsException extends RuntimeException
{
    public InsufficientFundsException(String message)
    {
        super(message);
    }

}
