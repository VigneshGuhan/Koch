package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElementUtils {
    public static boolean isPresent(WebDriver driver, WebElement element){
        try{
            element.isDisplayed();
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    public void getScreenshot(WebDriver driver,String resourceName) {
        File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenShotDirectory=(System.getProperty("user.dir"))+"/screenshots";

        // creating screenshot directory if not exist
        File dir=new File(screenShotDirectory);
        if(!dir.exists()){
            if(dir.mkdir()) {

                System.out.println("Screenshot diectory created at :"+screenShotDirectory);
            }
            else {
                System.out.println("Screenshot diectory ccreation failed !!!!");
            }
        }

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destinationPath=screenShotDirectory+"/"+resourceName+"_"+dateName+".png";
        System.out.println("Screenshot copied to  :"+destinationPath);
        try {
            FileUtils.copyFile(src, new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
