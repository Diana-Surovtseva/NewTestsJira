import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
    }

    @Test
    public void successfulLoginTest() {
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login-form-password")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(driver.findElement(By.id("header-details-user-fullname")).isDisplayed());
    }

    @Test
    public void failedTest(){
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        assertEquals(1,2);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
