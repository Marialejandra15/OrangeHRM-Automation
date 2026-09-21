package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Login;
import utils.csvdatareader;

public class logintest {

    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return csvdatareader.readCsv(
                "src/main/resources/data/login.csv"
        );
    }

    @Test(dataProvider = "loginData")
    public void loginValido(String username, String password) throws InterruptedException {

        Login loginPage = new Login(webDriver);

        loginPage.loginAs(username, password);

        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}