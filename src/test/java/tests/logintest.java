package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Login;
import utils.csvdatareader;

public class logintest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return csvdatareader.readCsv(
                "src/main/resources/data/login.csv"
        );
    }

    @Test(dataProvider = "loginData")
    public void loginValido(
            String username,
            String password
    ) throws InterruptedException {

        webDriver.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );

        Login loginPage = new Login(webDriver);

        loginPage.loginAs(
                username,
                password
        );

        Thread.sleep(5000);

        System.out.println(
                "URL después del login: "
                        + webDriver.getCurrentUrl()
        );
    }
}