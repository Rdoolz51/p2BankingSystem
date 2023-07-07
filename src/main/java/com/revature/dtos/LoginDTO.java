package com.revature.dtos;

public class LoginDTO
{
    private static String username;
    private String password;

    public LoginDTO()
    {
    }

    public LoginDTO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public static String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}