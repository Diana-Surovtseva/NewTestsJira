import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class EditTicket {


    WebDriver driver = null;
    WebDriverWait wait;


    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login-form-password")).sendKeys("DianaSurovtseva");
        driver.findElement(By.id("login")).click();

        boolean elementIsPresent = wait.until(elementToBeClickable(By.xpath("//*[contains(text(),'WEBINAR-11962')]"))).isEnabled();

    }

    @Test
    public void addCommentToTicket() {


        driver.findElement(By.xpath("//*[@data-issue-key='WEBINAR-11962']")).click();

        wait.until(elementToBeClickable(By.id("footer-comment-button"))).isEnabled();
        driver.findElement(By.id("footer-comment-button")).click();
        driver.findElement(By.id("comment")).sendKeys("My comment");
        driver.findElement(By.id("issue-comment-add-submit")).click();

        wait.until(presenceOfElementLocated(By.xpath("//*[@id='issue_actions_container']//child::*[@class='issue-data-block activity-comment twixi-block  expanded focused']//child::*[@class='action-body flooded']"))).isEnabled();
        String commentText = driver.findElement(By.xpath("//*[@id='issue_actions_container']//child::*[@class='issue-data-block activity-comment twixi-block  expanded focused']//child::*[@class='action-body flooded']")).getText();
        assertTrue(commentText.contains("My comment"));
    }

    @Test

    public void dellCommentFromTicket() {
        driver.findElement(By.xpath("//*[@data-issue-key='WEBINAR-11962']")).click();

        wait.until(elementToBeClickable(By.id("issue_actions_container"))).isEnabled();
        WebElement elementList = driver.findElement(By.xpath("//*[@class='issue-data-block activity-comment twixi-block  expanded'][last()]//child::*[@title='Delete']"));
        elementList.click();

        String currCommentText = elementList.getText();
        wait.until(elementToBeClickable(By.id("comment-delete-submit"))).isEnabled();
        driver.findElement(By.id("comment-delete-submit")).click();

        String commentText = driver.findElement(By.xpath("//*[@id='issue_actions_container']//child::*[@class='issue-data-block activity-comment twixi-block  expanded']//child::*[@class='action-body flooded']")).getText();
        assertTrue(!commentText.contains(currCommentText));

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
