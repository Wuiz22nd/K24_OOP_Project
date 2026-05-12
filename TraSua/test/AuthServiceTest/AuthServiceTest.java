/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AuthServiceTest;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import service.AuthService;

public class AuthServiceTest {

    @Test
    public void testSignUpSuccess() {

        AuthService auth = new AuthService();

        boolean result = auth.signUp(
                "user123@gmail.com",
                "password123"
        );

        assertTrue(result);
    }

    @Test
    public void testSignUpFail_UserExist() {

        AuthService auth = new AuthService();

        auth.signUp(
                "admin12@gmail.com",
                "password123"
        );

        boolean result = auth.signUp(
                "admin12@gmail.com",
                "password456"
        );

        assertFalse(result);
    }

    @Test
    public void testSignUpFail_InvalidEmail() {

        AuthService auth = new AuthService();

        boolean result = auth.signUp(
                "abc",
                "password123"
        );

        assertFalse(result);
    }

    @Test
    public void testSignUpFail_InvalidPassword() {

        AuthService auth = new AuthService();

        boolean result = auth.signUp(
                "user999@gmail.com",
                "123"
        );

        assertFalse(result);
    }

    @Test
    public void testLoginSuccess() {

        AuthService auth = new AuthService();

        auth.signUp(
                "login12@gmail.com",
                "password123"
        );

        boolean result = auth.login(
                "login12@gmail.com",
                "password123"
        );

        assertTrue(result);
    }

    @Test
    public void testLoginFail() {

        AuthService auth = new AuthService();

        auth.signUp(
                "wrong12@gmail.com",
                "password123"
        );

        boolean result = auth.login(
                "wrong12@gmail.com",
                "99999999"
        );

        assertFalse(result);
    }

    @Test
    public void testLogout() {

        AuthService auth = new AuthService();

        auth.signUp(
                "logout12@gmail.com",
                "password123"
        );

        auth.login(
                "logout12@gmail.com",
                "password123"
        );

        auth.logout();

        assertFalse(auth.getCurrentUser() != null);
    }
}