
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewTicket {

    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
    }


    @Test
    public void viewJiraTicket() {
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login-form-password")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@data-issue-key='BAK-40']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(driver.findElement(By.id("summary-val")).isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("BAK-40"));

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
