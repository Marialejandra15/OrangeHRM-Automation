package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Employee {
    private WebDriver webDriver;
    private WebDriverWait wait;

    private By addButton = By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Add']");
    private By userRole = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    private By employeeName = By.xpath("//input[@placeholder='Type for hints...']");
    private By usernameInput = By.xpath("//label[normalize-space()='Username']/following::input[1]");
    private By status = By.xpath("(//div[contains(@class,'oxd-select-text')])[2]");
    private By passwordInput = By.xpath("//label[normalize-space()='Password']/following::input[@type='password'][1]");
    private By confirmPasswordInput = By.xpath("(//input[@type='password'])[2]");
    private By saveButton = By.xpath("//label[normalize-space()='Confirm Password']/following::input[@type='password'][1]");
    private By adminButton = By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Admin'])[1]");

    public Employee (WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    public void clickAdminButton(){
        wait.until(ExpectedConditions.elementToBeClickable(adminButton))
                .click();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.urlContains("/admin/viewSystemUsers"));

        wait.until(ExpectedConditions.presenceOfElementLocated(addButton));
        webDriver.findElement(addButton).getLocation();

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.urlContains("/admin/saveSystemUser"));
    }

    public void selectUserRole(String role) {
        wait.until(ExpectedConditions.elementToBeClickable(userRole)).click();

        By roleOption = By.xpath(
                "//div[contains(@class,'oxd-select-option')]//span[normalize-space()='" + role + "']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(roleOption));

        wait.until(ExpectedConditions.elementToBeClickable(roleOption)).click();
    }

    public void typeEmployeeName(String name) {

        WebDriverWait employeeWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement employeeInput = employeeWait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));

        employeeInput.sendKeys(name);

        By employeeOption = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')]");

        employeeWait.until(ExpectedConditions.visibilityOfElementLocated(employeeOption));

        By specificEmployee = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')]"
                        + "//span[contains(normalize-space(),'"
                        + name + "')]");
        employeeWait.until(ExpectedConditions.elementToBeClickable(specificEmployee)).click();
    }

    public void typeUsername(String username) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        input.clear();
        input.sendKeys(username);
    }

    public void selectStatus(String statusValue) {
        wait.until(ExpectedConditions.elementToBeClickable(status)).click();
        By statusOption = By.xpath(
                "//div[contains(@class,'oxd-select-option')]"
                        + "[normalize-space()='" + statusValue + "']"
        );
        wait.until(ExpectedConditions.elementToBeClickable(statusOption)).click();
    }

    public void typePassword(String password) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        input.clear();
        input.sendKeys(password);
    }

    public void typeConfirmPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput));
        input.clear();
        input.sendKeys(password);
    }


    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }



    public void addUser(
        String role,
        String employee,
        String username,
        String statusValue,
        String password
    ){
        clickAddButton();
        selectUserRole(role);
        typeEmployeeName(employee);
        typeUsername(username);
        selectStatus(statusValue);
        typePassword(password);
        typeConfirmPassword(password);
        clickSaveButton();
    }


}

