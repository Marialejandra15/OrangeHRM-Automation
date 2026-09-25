package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Employe {

    private WebDriver webDriver;
    private WebDriverWait wait;

    // Menu PIM
    private By pimButton =
            By.xpath("//a[normalize-space()='PIM']");

    // Boton Add
    private By addButton =
            By.xpath("//button[normalize-space()='Add']");

    // Datos del empleado
    private By firstNameInput =
            By.xpath("//input[@placeholder='First Name']");

    private By middleNameInput =
            By.xpath("//input[@placeholder='Middle Name']");

    private By lastNameInput =
            By.xpath("//input[@placeholder='Last Name']");

    private By employeeIdInput =
            By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");

    // Switch
    private By switchInput =
            By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");

    // Username
    private By usernameInput =
            By.xpath("//label[normalize-space()='Username']/following::input[1]");

    // Password
    private By passwordInput =
            By.xpath("//div[contains(@class,'user-password-cell')]//input[@type='password']");

    // Confirm Password
    private By confirmPasswordInput =
            By.xpath("//div[contains(@class,'oxd-grid-item--gutters')][.//label[normalize-space()='Confirm Password']]//input[@type='password']");

    // Save
    private By saveButton =
            By.xpath("//button[normalize-space()='Save']");

    // Employee List
    private By employeeListButton =
            By.xpath("//li[@class='oxd-topbar-body-nav-tab --visited']");

    // Campo Employee Name
    private By employeeNameSearchInput =
            By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]");

    // Search
    private By searchButton =
            By.xpath("//button[normalize-space()='Search']");


    public Employe(WebDriver webDriver) {

        this.webDriver = webDriver;

        this.wait = new WebDriverWait(
                webDriver,
                Duration.ofSeconds(30)
        );
    }


    public void clickPIM() {

        wait.until(
                ExpectedConditions.elementToBeClickable(pimButton)
        ).click();
    }


    public void clickEmployeeList() {

        wait.until(
                ExpectedConditions.elementToBeClickable(employeeListButton)
        ).click();
    }


    public void clickAddButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        ).click();
    }


    public void searchEmployee(
            String firstName,
            String middleName
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeNameSearchInput
                        )
                );

        input.clear();

        String employeeName =
                firstName + " " + middleName;

        input.sendKeys(employeeName);

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }


    public void searchEmployeeByMiddleName(
            String middleName
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeNameSearchInput
                        )
                );

        input.clear();

        input.sendKeys(middleName);

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }


    public void validateEmployeeExists(
            String firstName,
            String middleName
    ) {

        /*
         * El nombre se obtiene directamente
         * desde el CSV.
         */
        String expectedName =
                firstName + " " + middleName;


        /*
         * Busca la fila completa que contenga
         * el nombre esperado.
         */
        By employeeRow =
                By.xpath(
                        "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']"
                                + "[.//div[contains(normalize-space(),'"
                                + expectedName
                                + "')]]"
                );


        WebElement row =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeRow
                        )
                );


        /*
         * Dentro de esa misma fila busca
         * nuevamente el nombre.
         */
        By employeeName =
                By.xpath(
                        ".//div[contains(normalize-space(),'"
                                + expectedName
                                + "')]"
                );


        WebElement nameElement =
                row.findElement(employeeName);


        String actualName =
                nameElement.getText().trim();


        System.out.println(
                "Nombre esperado: "
                        + expectedName
        );

        System.out.println(
                "Nombre encontrado: "
                        + actualName
        );


        /*
         * Validación.
         */
        if (!actualName.equalsIgnoreCase(expectedName)) {

            throw new AssertionError(
                    "El nombre no coincide. "
                            + "Esperado: "
                            + expectedName
                            + " | Encontrado: "
                            + actualName
            );
        }


        System.out.println(
                "Empleado validado correctamente: "
                        + actualName
        );
    }


    public void typeFirstName(
            String firstName
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                firstNameInput
                        )
                );

        input.clear();

        input.sendKeys(firstName);
    }


    public void typeMiddleName(
            String middleName
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                middleNameInput
                        )
                );

        input.clear();

        input.sendKeys(middleName);
    }


    public void typeLastName(
            String lastName
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                lastNameInput
                        )
                );

        input.clear();

        input.sendKeys(lastName);
    }


    public void typeEmployeeId(
            String employeeId
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeIdInput
                        )
                );

        input.clear();

        input.sendKeys(employeeId);
    }


    public void clickSwitch() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        switchInput
                )
        ).click();
    }


    public void typeUsername(
            String username
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                usernameInput
                        )
                );

        input.clear();

        input.sendKeys(username);
    }


    public void typePassword(
            String password
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                passwordInput
                        )
                );

        input.clear();

        input.sendKeys(password);
    }


    public void typeConfirmPassword(
            String confirmPassword
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                confirmPasswordInput
                        )
                );

        input.clear();

        input.sendKeys(confirmPassword);
    }


    public void clickSaveButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        saveButton
                )
        ).click();
    }


    public void addEmploye(
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String username,
            String password,
            String confirmPassword
    ) {

        clickPIM();
        clickAddButton();
        typeFirstName(firstName);
        typeMiddleName(middleName);
        typeLastName(lastName);
        typeEmployeeId(employeeId);
        clickSwitch();
        typeUsername(username);
        typePassword(password);
        typeConfirmPassword(confirmPassword);
        clickSaveButton();
    }
}