package tests;

import pages.LoginPage;
import utils.ReaderCSVData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicLoginTest extends BaseTest {
    @DataProvider(name = "loginCsvData")
    public Object[][] getLoginData() {
        String csvPath = "src/test/resources/testdata/test_data_login.csv";
        return ReaderCSVData.getCSVData(csvPath);
    }

    @Test(dataProvider = "loginCsvData", description = "Dynamic Data-Driven Test covering TC2, TC3, TC4 & Edge Cases")
    public void testDynamicLoginScenarios(String email, String password, String scenario) {
        LoginPage loginPage = new LoginPage(driver);

        switch (scenario) {
            case "VALID_CREDENTIALS":
                // TC2: Verify successful login
                loginPage.login(email, password);
                Assert.assertFalse(driver.getCurrentUrl().contains("/login"),
                        "User failed to login with valid credentials.");
                break;

            case "INVALID_CREDENTIALS":
                // TC3: Invalid credentials
                loginPage.login(email, password);
                Assert.assertFalse(loginPage.getErrorMessage().isEmpty(),
                        "Error message expected for invalid credentials.");
                break;

            case "EMPTY_EMAIL":
            case "EMPTY_PASSWORD":
                // TC3: Leaving email or password field empty
                loginPage.enterEmail(email);
                loginPage.enterPassword(password);
                Assert.assertFalse(loginPage.isSignInButtonEnabled(),
                        "Sign in button should remain disabled when fields are empty.");
                break;

            case "INVALID_EMAIL_FORMAT":
                // TC4 & Security: Invalid email format (e.g. testuser.com)
                loginPage.enterEmail(email);
                loginPage.enterPassword(password);
                if (loginPage.isSignInButtonEnabled()) {
                    loginPage.clickSignIn();
                    Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("email")
                                    || loginPage.getErrorMessage().toLowerCase().contains("invalid"),
                            "Validation error expected for invalid email format.");
                } else {
                    Assert.assertFalse(loginPage.isSignInButtonEnabled(),
                            "Sign in button disabled for invalid email format.");
                }
                break;

            case "EXCESSIVE_EMAIL_LENGTH":
            case "EXCESSIVE_PASSWORD_LENGTH":
                // TC4: Excessively long email (>255 chars) or password (>100 chars)
                loginPage.enterEmail(email);
                loginPage.enterPassword(password);
                loginPage.clickSignIn();
                Assert.assertFalse(loginPage.getErrorMessage().isEmpty(),
                        "Error message expected for excessive input length.");
                break;

            default:
                Assert.fail("Unknown scenario specified in CSV: " + scenario);
        }
    }
}
