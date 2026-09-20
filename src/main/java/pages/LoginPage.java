package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators - Nakivo Web Interface
    private final By emailInput = By.cssSelector("input[placeholder='Username'], input[name='username']");
    private final By passwordInput = By.cssSelector("input[type='password']");
    private final By signInButton = By.xpath("//*[@title='Log In'] | //*[contains(text(),'LOG IN')]");
    private final By forgotPasswordLink = By.xpath("//*[contains(text(),'Forgot the password')]");
    private final By errorMessageLabel = By.cssSelector(".msg-error-field");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).isDisplayed()
                && driver.findElement(passwordInput).isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailElement.clear();
        if (email != null && !email.isEmpty()) {
            emailElement.sendKeys(email);
        }
    }

    public void enterPassword(String password) {
        WebElement passElement = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passElement.clear();
        if (password != null && !password.isEmpty()) {
            passElement.sendKeys(password);
        }
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLabel)).getText().trim();
    }

    public boolean isSignInButtonEnabled() {
        return driver.findElement(signInButton).isEnabled();
    }

    public boolean isForgotPasswordLinkVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordLink)).isDisplayed();
    }

    public boolean isForgotPasswordLinkFunctional() {
        WebElement link = driver.findElement(forgotPasswordLink);
        return link.isEnabled() && link.getAttribute("href") != null;
    }

    public boolean isPasswordMasked() {
        WebElement passElement = driver.findElement(passwordInput);
        return "password".equalsIgnoreCase(passElement.getAttribute("type"));
    }

    public String getEmailInputValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }
}
