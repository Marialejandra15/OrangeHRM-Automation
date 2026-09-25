package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static WebDriver webDriver;

    @BeforeSuite
    public void setUpDriver() {

        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        System.out.println("=================================");
        System.out.println("WebDriver iniciado.");
        System.out.println("=================================");
    }

    @AfterSuite
    public void tearDownDriver() {

        if (webDriver != null) {

            webDriver.quit();

            System.out.println("=================================");
            System.out.println("WebDriver cerrado.");
            System.out.println("=================================");
        }
    }
}