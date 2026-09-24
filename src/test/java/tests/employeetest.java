package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Employee;
import pages.Login;
import utils.csvdatareader;

public class employeetest {

    private WebDriver webDriver;


    @BeforeMethod
    public void setUp() {

        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );
    }


    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {

        return csvdatareader.readCsv(
                "src/main/resources/data/employees.csv"
        );
    }


    @Test(dataProvider = "employeeData")
    public void addNewEmployee(
            String role,
            String employee,
            String username,
            String statusValue,
            String password
    ) throws InterruptedException {

        // Iniciar sesión
        Login loginPage = new Login(webDriver);

        loginPage.loginAs(
                "Admin",
                "admin123"
        );

        webDriver.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers"
        );

        Employee addEmployee = new Employee(webDriver);

        addEmployee.addUser(
                role,
                employee,
                username,
                statusValue,
                password
        );


        Thread.sleep(5000);
    }


    @AfterMethod
    public void tearDown() {

        webDriver.quit();
    }
}
