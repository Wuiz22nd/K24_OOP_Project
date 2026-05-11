/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AuthServiceTest;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;


import service.AuthService;



/**
 *
 * @author wuiz
 */
public class AuthServiceTest {

    @Test
    public void testSignUpSuccess() {

        AuthService auth = new AuthService();

        boolean result = auth.signUp(
        "user_" + System.currentTimeMillis(),
        "123");

        assertTrue(result);
    }

    @Test
    public void testSignUpFail_UserExist() {

        AuthService auth = new AuthService();

        auth.signUp("admin", "123");

        boolean result = auth.signUp("admin", "456");

        assertFalse(result);
    }

    @Test
    public void testLoginSuccess() {

        AuthService auth = new AuthService();

        auth.signUp("admin", "123");

        boolean result = auth.login("admin", "123");

        assertTrue(result);
    }

    @Test
    public void testLoginFail() {

        AuthService auth = new AuthService();

        auth.signUp("admin", "123");

        boolean result = auth.login("admin", "999");

        assertFalse(result);
    }

    @Test
    public void testLogout() {

        AuthService auth = new AuthService();

        auth.signUp("admin", "123");
        auth.login("admin", "123");

        auth.logout();

        assertFalse(auth.isLoggedIn());
    }
}
