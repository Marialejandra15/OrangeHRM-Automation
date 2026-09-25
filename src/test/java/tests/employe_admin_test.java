package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Employe_admin;
import utils.csvdatareader;

public class employe_admin_test extends BaseTest {

    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {

        return csvdatareader.readCsv(
                "src/main/resources/data/employe_admin.csv"
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

        System.out.println(
                "URL antes de Employee: "
                        + webDriver.getCurrentUrl()
        );

        Employe_admin employeePage =
                new Employe_admin(webDriver);

        employeePage.addUser(
                role,
                employee,
                username,
                statusValue,
                password
        );

        Thread.sleep(5000);
    }
}