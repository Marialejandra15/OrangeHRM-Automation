package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Employe;
import utils.csvdatareader;

public class Employe_test extends BaseTest {

    @DataProvider(name = "employe_data")
    public Object[][] employe_data() {

        return csvdatareader.readCsv(
                "src/main/resources/data/employe_data.csv"
        );
    }

    @Test(dataProvider = "employe_data")
    public void addNewEmployee(
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String username,
            String password,
            String confirmPassword
    ) throws InterruptedException {

        System.out.println(
                "URL antes de Employee: "
                        + webDriver.getCurrentUrl()
        );

        Employe employePage =
                new Employe(webDriver);

        employePage.addEmploye(
                firstName,
                middleName,
                lastName,
                employeeId,
                username,
                password,
                confirmPassword
        );

        Thread.sleep(9000);
    }
}