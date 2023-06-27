package utils;

import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
    public static WebDriver createWebDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME -> {
                System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32 (4)\\chromedriver.exe");
                return new ChromeDriver();
            }
            case FIREFOX -> {
                System.setProperty("webdriver.gecko.driver", "D:\\Downloads\\geckodriver-v0.33.0-win32\\geckodriver.exe");
                return new FirefoxDriver();
            }
            case EDGE -> {
                System.setProperty("webdriver.edge.driver", "D:\\Downloads\\edgedriver_win64\\msedgedriver.exe");
                return new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

}

