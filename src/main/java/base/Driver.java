package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver driver=null;
    protected static Properties prop=new Properties();
    static  FileInputStream fis=null;
    static final String filePath=".//src//main//resources//datadriven.properties";

    public static WebDriver driverInitializer() {
        initiailizeProperties();
        //Defining browser from property file and setting up
        String browserName=prop.getProperty("browser");
        System.out.println(browserName);

        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();

        }else if(browserName.equalsIgnoreCase("FireFox")||browserName.equalsIgnoreCase("FF")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            System.out.println("-----Test Runs with default browser in latest version : Chrome---");
        }

        String implicitWait=prop.getProperty("implicitWait");
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitWait), TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
    private static void initiailizeProperties(){
    File file=new File(filePath);
    if(file.exists()&& file.canRead()) {
        try {
            fis=new FileInputStream(filePath);
            prop.load(fis);
            System.out.println("File loaded...");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    else {
        System.out.println("Cannot find the file");
        System.exit(0);
    }
}

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

    protected static void appLogin() {
        if(prop==null){
            initiailizeProperties();

        }
        String appUrl=prop.getProperty("appUrl");
        System.out.println("Launching application Url = " + appUrl);
        driver.get(appUrl);

    }

    protected static void appLogin(String Url) {
        String appUrl=Url;
        System.out.println("Launching application Url = " + appUrl);
        driver.get(appUrl);
    }
}
