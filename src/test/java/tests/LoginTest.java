package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    // Test Case 1: Verify login page loads successfully
    @Test(priority = 1, description = "TC1: Verify login page loads successfully")
    public void testLoginPageLoadsSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page failed to load expected elements.");
    }

    // UI Validation: "Forgot your password?" Link visibility & functionality
    @Test(priority = 2, description = "UI: Validate 'Forgot your password?' link")
    public void testForgotPasswordLinkUI() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isForgotPasswordLinkVisible(), "'Forgot your password?' link is not visible.");
        Assert.assertTrue(loginPage.isForgotPasswordLinkFunctional(), "'Forgot your password?' link is not functional.");
    }

    // UI Validation: Sign-in button disabled state when fields are empty
    @Test(priority = 3, description = "UI: Verify Sign-in button is disabled when fields are empty")
    public void testSignInButtonDisabledWhenFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        Assert.assertFalse(loginPage.isSignInButtonEnabled(), "'Sign in' button should be disabled when fields are empty.");
    }

    // Security Validation: Password masking
    @Test(priority = 4, description = "Security: Verify password field is masked")
    public void testPasswordInputIsMasked() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("SecretPass123!");
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password input field is not masked (type != password).");
    }
}
