package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Employe_admin {

    private WebDriver webDriver;
    private WebDriverWait wait;

    // Admin
    private By adminButton =
            By.xpath("//a[contains(@href,'/admin/viewAdminModule')]");

    // Add
    private By addButton =
            By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Add']");

    // User Role
    private By userRole =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");

    // Employee Name
    private By employeeName =
            By.xpath("//input[@placeholder='Type for hints...']");

    // Username
    private By usernameInput =
            By.xpath("//label[normalize-space()='Username']/following::input[1]");

    // Status
    private By status =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[2]");

    // Password
    private By passwordInput =
            By.xpath("//div[contains(@class,'user-password-cell')]//input[@type='password']");

    // Confirm Password
    private By confirmPasswordInput =
            By.xpath(
                    "//div[contains(@class,'oxd-grid-item--gutters')]" +
                            "[.//label[normalize-space()='Confirm Password']]" +
                            "//input[@type='password']"
            );

    // Save
    private By saveButton =
            By.xpath("//button[normalize-space()='Save']");


    public Employe_admin(WebDriver webDriver) {

        this.webDriver = webDriver;

        this.wait = new WebDriverWait(
                webDriver,
                Duration.ofSeconds(30)
        );
    }


    public void clickAdminButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(adminButton)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/admin/viewSystemUsers")
        );

        System.out.println(
                "URL después de entrar a Admin: "
                        + webDriver.getCurrentUrl()
        );
    }


    public void clickAddButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/admin/saveSystemUser")
        );
    }


    public void selectUserRole(String role) {

        wait.until(
                ExpectedConditions.elementToBeClickable(userRole)
        ).click();

        By roleOption =
                By.xpath(
                        "//div[contains(@class,'oxd-select-option')]" +
                                "//span[normalize-space()='" +
                                role +
                                "']"
                );

        wait.until(
                ExpectedConditions.elementToBeClickable(roleOption)
        ).click();
    }


    public void typeEmployeeName(String name) {

        WebElement employeeInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));
        employeeInput.clear();
        employeeInput.sendKeys(name);

        By employeeOption = By.xpath("//div[contains(@class,'oxd-autocomplete-option')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeOption));

        By specificEmployee = By.xpath("//div[contains(@class,'oxd-autocomplete-option')]" + "//span[contains(normalize-space(),'" + name + "')]");

        wait.until(ExpectedConditions.elementToBeClickable(specificEmployee)).click();
    }


    public void typeUsername(String username) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                usernameInput
                        )
                );

        input.clear();
        input.sendKeys(username);
    }


    public void selectStatus(String statusValue) {

        wait.until(
                ExpectedConditions.elementToBeClickable(status)
        ).click();

        By statusOption =
                By.xpath(
                        "//div[contains(@class,'oxd-select-option')]" +
                                "[normalize-space()='" +
                                statusValue +
                                "']"
                );

        wait.until(
                ExpectedConditions.elementToBeClickable(statusOption)
        ).click();
    }


    public void typePassword(String password) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                passwordInput
                        )
                );

        input.clear();
        input.sendKeys(password);
    }


    public void typeConfirmPassword(String password) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                confirmPasswordInput
                        )
                );

        input.clear();
        input.sendKeys(password);
    }


    public void clickSaveButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }


    public void addUser(
            String role,
            String employee,
            String username,
            String statusValue,
            String password
    ) {

        clickAdminButton();
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