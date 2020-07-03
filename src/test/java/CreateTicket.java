
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateTicket {

    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
    }


    @Test

    public void createTicket() {
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login-form-password")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds());
        //boolean elementIsPresent = wait.until(presenceOfElementLocated(By.id("create_link"))).isEnabled();
        //assertEquals(elementIsPresent, true);

        // wait.until(presenceOfElementLocated(By.id("create_link"))).isEnabled();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("create_link")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //wait.until(presenceOfElementLocated(By.id("project-field"))).isEnabled();
        driver.findElement(By.id("project-field")).clear();
        driver.findElement(By.id("project-field")).sendKeys("Webinar (WEBINAR)");
        driver.findElement(By.id("project-field")).sendKeys(Keys.TAB);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //wait.until(presenceOfElementLocated(By.id("issuetype-field"))).isEnabled();

        driver.findElement(By.id("issuetype-field")).clear();
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.TAB);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("summary")).sendKeys("Test bug");
        driver.findElement(By.id("reporter-field")).clear();
        driver.findElement(By.id("reporter-field")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("reporter-field")).sendKeys(Keys.TAB);
        driver.findElement(By.id("create-issue-submit")).click();

        boolean popUpIsPresent = wait.until(presenceOfElementLocated(By.className("aui-message-success"))).isDisplayed();
        assertEquals(popUpIsPresent, true);


        String popUpText = driver.findElement(By.className("aui-message-success")).getText();
        assertTrue(popUpText.contains("WEBINAR"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
