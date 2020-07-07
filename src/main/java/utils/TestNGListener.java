package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.nio.file.StandardCopyOption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        File screenshotsFolder = new File(System.getProperty("user.dir") + "/screenshots");
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdir();
        }
        File screenshot = captureScreenshot();
        Path pathToScreenshot = Paths.get(screenshot.getPath());
        try {
            String currTime = java.time.LocalTime.now().toString().replace(":", ".");
            String screenshotName = screenshotsFolder + "/" + "Screenshot_" + currTime + ".png";
            Files.copy(pathToScreenshot, Paths.get(screenshotName), StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private File captureScreenshot(){
        return ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
    }
}

