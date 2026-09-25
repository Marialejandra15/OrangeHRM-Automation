package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Employe;
import utils.csvdatareader;

public class Employe_validate_test extends BaseTest {

    @DataProvider(name = "employe_data")
    public Object[][] employe_data() {

        return csvdatareader.readCsv(
                "src/main/resources/data/employe_data.csv"
        );
    }

    @Test(
            dataProvider = "employe_data",
            dependsOnMethods = "addNewEmployee"
    )
    public void validateEmployee(
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String username,
            String password,
            String confirmPassword
    ) {

        System.out.println(
                "URL después de crear Employee: "
                        + webDriver.getCurrentUrl()
        );

        Employe employePage =
                new Employe(webDriver);

        employePage.clickEmployeeList();

        employePage.searchEmployee(
                firstName,
                middleName
        );

        employePage.validateEmployeeExists(
                firstName,
                middleName
        );
    }
}