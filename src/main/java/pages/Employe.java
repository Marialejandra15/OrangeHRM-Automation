package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // Photo
    private By photoInput =
            By.cssSelector("input[type='file']");

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

    public void uploadPhoto(String photoPath){
        if(photoPath == null || photoPath.isBlank()){
            return;
        }
        Path image =
                Paths.get(photoPath.trim()).toAbsolutePath().normalize();

        if (!Files.isRegularFile(image)) {
            throw new IllegalArgumentException(
                    "No se encontró la foto: " + image
            );
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(photoInput))
                .sendKeys(image.toString());
    }


    public void clickSaveButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        saveButton
                )
        ).click();
    }

    public void saveEmployeeToCsv(
            String firstName,
            String middleName,
            String username
    ) {

        String employeeName =
                firstName + " " + middleName;

        Path csvPath =
                Paths.get(
                        "src/main/resources/data/employe_admin.csv"
                );

        try {

            String content =
                    Files.readString(
                            csvPath,
                            StandardCharsets.UTF_8
                    );

            String[] lines =
                    content.split("\\R");

            if (lines.length < 2) {

                throw new RuntimeException(
                        "El CSV debe contener header y al menos una fila de datos."
                );
            }

            String header =
                    lines[0];

            String[] values =
                    lines[1].split(",", -1);

            if (values.length < 5) {

                throw new RuntimeException(
                        "El CSV no contiene las 5 columnas esperadas."
                );
            }

            // Columna employee
            values[1] =
                    employeeName;


            // Username actual guardado en el CSV
            String currentUsername =
                    values[2];


            // Generar el siguiente ID de 2 dígitos
            int nextId = 1;

            if (currentUsername.matches(".*\\d{2}$")) {

                String lastTwoDigits =
                        currentUsername.substring(
                                currentUsername.length() - 2
                        );

                nextId =
                        Integer.parseInt(lastTwoDigits) + 1;
            }


            // Username nuevo
            String newUsername =
                    username
                            + String.format(
                            "%02d",
                            nextId
                    );


            // Columna username
            values[2] =
                    newUsername;


            String updatedRow =
                    String.join(",", values);

            String updatedContent =
                    header
                            + System.lineSeparator()
                            + updatedRow
                            + System.lineSeparator();


            Files.writeString(
                    csvPath,
                    updatedContent,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING
            );


            System.out.println(
                    "Employee actualizado en CSV: "
                            + employeeName
            );

            System.out.println(
                    "Username actualizado en CSV: "
                            + newUsername
            );


        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al actualizar el CSV: "
                            + csvPath,
                    e
            );
        }
    }


    public void addEmploye(
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String username,
            String password,
            String confirmPassword,
            String photoPath
    ) {

        clickPIM();
        clickAddButton();
        typeFirstName(firstName);
        typeMiddleName(middleName);
        typeLastName(lastName);
        typeEmployeeId(employeeId);
        uploadPhoto(photoPath);
        clickSwitch();
        typeUsername(username);
        typePassword(password);
        typeConfirmPassword(confirmPassword);
        clickSaveButton();
        saveEmployeeToCsv(
                firstName,
                middleName,
                username);
    }
}