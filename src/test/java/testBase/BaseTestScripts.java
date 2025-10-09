package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTestScripts {

	public WebDriver driver;
	public Logger logger;

	public Properties p;

	@BeforeMethod(groups = { "Sanity", "Master", "Regression", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void initialize(@Optional("Windows")String os, @Optional("chrome")String br) throws IOException {

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capability = new DesiredCapabilities();
			// Oprating system
			if (os.equalsIgnoreCase("windows")) {
				capability.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capability.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching operationg system");
				return;
			}
			// Browser
			switch (br.toLowerCase()) {
			case "chrome":
				capability.setBrowserName("chrome");
				break;
			case "edge":
				capability.setBrowserName("edge");
				break;
			case "firefox":
				capability.setBrowserName("firefox");
				break;

			default:
				System.out.println("Invalid browser name");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://172.30.3.175:4444/wd/hub"), capability);
		}
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("Invalid browser name");
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().refresh();
		driver.get(p.getProperty("appurl"));
		Assert.assertEquals(driver.getTitle(), "Your Store");
	}

	@AfterMethod(groups = { "Sanity", "Master", "Regression", "DataDriven" })
	public void cleanup() {
		driver.quit();
	}

	public static String randomStringForName() {
		return RandomStringUtils.randomAlphabetic(7);
	}

	public static String randomStringForLastName() {
		return RandomStringUtils.randomAlphabetic(7);
	}

	public static String randomStringForEmail() {
		return RandomStringUtils.randomAlphabetic(7);
	}

	public static String randomPassword() {
		String chars = RandomStringUtils.randomAlphabetic(5);
		String numbs = RandomStringUtils.randomAlphanumeric(2);

		return (chars + "@" + numbs);
	}

	public String captureScreen(String tname) {
		// Create timestamp for unique filename
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		// Take screenshot
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		// Create destination path
		String targetDir = System.getProperty("user.dir") + File.separator + "screenshots";
		File dir = new File(targetDir);
		if (!dir.exists()) {
			dir.mkdirs(); // Create directory if not exists
		}

		String targetPath = targetDir + File.separator + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetPath);

		try {
			FileUtils.copyFile(sourceFile, targetFile);
			System.out.println("Screenshot saved at: " + targetPath);
		} catch (IOException e) {
			System.out.println("Failed to save screenshot: " + e.getMessage());
			return null;
		}

		return targetPath;
	}
}
