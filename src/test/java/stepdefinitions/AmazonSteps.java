package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;


public class AmazonSteps {
    private WebDriver driver;

/*    @Before
    public void setUp() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\Downloads\\geckodriver-v0.33.0-win32\\geckodriver.exe");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions);
    }*/

    @Before
    public void setUp() throws IOException {
        // Load the configuration file
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config.properties");
        properties.load(inputStream);

        // Get the browser type from the configuration
        String browserType = properties.getProperty("browser");

        // Set the appropriate driver executable based on the browser type
        switch (browserType.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32 (4)\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                System.setProperty("webdriver.gecko.driver", "D:\\Downloads\\geckodriver-v0.33.0-win32\\geckodriver.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "D:\\Downloads\\edgedriver_win64\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            // Add more cases for other browsers if needed

            default:
                System.out.println("Invalid browser type specified in the configuration.");
                break;
        }
    }

    @Given("I launch chrome browser")
    public void iLaunchChromeBrowser() {
        /*System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32 (4)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();*/
        driver.get("https://www.amazon.ca/");

    }

    @When("I navigate to amazon.ca")
    public void iNavigateToAmazonCa() {
        driver.get("https://www.amazon.ca/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("verify hamburger menu exists")
    public void verifyHamburgerMenuExists() {
        boolean isPresent = driver.findElements(By.xpath("//*[@id=\"nav-hamburger-menu\"]/i")).size() > 0;
        if (!isPresent){
            driver.navigate().refresh();
        }
    }

    @Then("click on hamburger menu")
    public void clickOnHamburgerMenu() {
        driver.findElement(By.xpath("//*[@id=\"nav-hamburger-menu\"]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("verify hamburger menu opens")
    public void verifyHamburgerMenuOpens() {
        if(driver.findElements(By.xpath("//div[@id='nav-main']")).size() == 0){

            driver.navigate().refresh();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Then("click on Kindle")
    public void clickOnKindle() {
        List<WebElement> kindleOptions = driver.findElements(By.className("hmenu-item"));
        WebElement kindle = driver.findElement(By.xpath("//div[contains(text(),'Kindle')]"));
        kindle.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("click on Kindle under Kindle E-Readers")
    public void clickOnKindleUnderKindleEReaders() {
        driver.findElement(By.xpath("//a[@class='hmenu-item' and text()='Kindle']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("click Buy Now")
    public void clickBuyNow() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//input[@id='buy-now-button']")).click();
    }

    @And("verify user is asked for email or phone number")
    public void verifyUserIsAskedForEmailOrPhoneNumber() {
        Boolean emailorphonefieldisPresent = driver.findElements(By.xpath("//*[@id=\"ap_email\"]")).size() > 0;
        if(emailorphonefieldisPresent = true){
            System.out.println("User is successfully asked for email or phone number");
        } else {
            System.out.println("User not asked for email or phone number");
        }
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
