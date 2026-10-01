package base;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = BaseTest.webDriver;

        if (driver != null) {

            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Screenshot del fallo",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        }
    }
}