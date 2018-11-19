package com.coderzgonwild.admin.fixify;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    Account account = new Account("User1", "Password", "user");
    @Test
    public void testgetUsername() {
        String expected = "User1";
        assertEquals(account.getUsername(), expected);


    }

    @Test
    public void testgetPassword() {
        String expected = "Password";
        assertEquals(account.getPassword(), expected);
    }

    @Test
    public void testgetAccountType() {
        String expected = "user";
        assertEquals(account.getAccountType(), expected);
    }
}