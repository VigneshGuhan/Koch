package pages.ixigo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {
    WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="div[class='fltr-hdr']")
    private List<WebElement> filterHeadereader;

    @FindBy(css="div.stops div.stop-info")
    private List<WebElement> stopFilterList;

     @FindBy(css="div.tmng-fltr button")
    private List<WebElement> timeFilterList;
     @FindBy(css="div.arln-nm")
    private List<WebElement> airlineFilterList;

     @FindBy(css="div.result-wrpr div.airline-text")
    private List<WebElement> airlineResultList;

     @FindBy(css="div.result-wrpr div.time:nth-child(1)")
    private List<WebElement> departTimeResultLIst;

     @FindBy(css="div.result-wrpr div.c-price-display span:nth-child(2)")
    private List<WebElement> priceResultList;

    @FindBy(xpath="//div[@class='result-wrpr']//div[@class='summary-section']")
    private List<WebElement> resultList;

/* @FindBy(css="")
    private WebElement ;*/

    public void waitForResultPageToLoadResults(){
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("i.plane-icon")));
    }
public void filterStopSelect(String stopFilterToSelect){
        for(WebElement stopOptions:stopFilterList){
            if(stopOptions.getText().equalsIgnoreCase(stopFilterToSelect)){
                stopOptions.findElement(By.xpath("./../preceding-sibling::span")).click();
                waitForResultPageToLoadResults();
                return;
            }
        }
}
    public List<String> getFilterOptions(){
        return filterHeadereader.stream().map(we -> we.getText()).filter(val -> !val.isEmpty()).collect(Collectors.toList());
    }
    public List<String> getStopFilterList(){
        return stopFilterList.stream().map(we -> we.getText()).filter(val -> !val.isEmpty()).collect(Collectors.toList());
    }
    public List<String> getTImeFilterList(){
        return timeFilterList.stream().map(we -> we.getText()).filter(val -> !val.isEmpty()).collect(Collectors.toList());
    }

    public List<String> getAirlineFilterLists(){
        return airlineFilterList.stream().map(we -> we.getText()).filter(val -> !val.isEmpty()).collect(Collectors.toList());
    }

    public List<FlightResult> getFlightResultDetails(){
        List<FlightResult> flightResults=new ArrayList();
        int size = resultList.size();
        for(int i=0;i<size;i++){
            FlightResult flightResult=new FlightResult();
            flightResult.setAirlinesName(resultList.get(i).findElement(By.xpath(".//div[@class=\"airline-text\"]")).getText());
            flightResult.setDepatureTime(resultList.get(i).findElement(By.xpath(".//div[@class=\"time\"][1]")).getText());
            flightResult.setPrice(resultList.get(i).findElement(By.xpath(".//div[@class=\"price\"]//span[2]")).getText());

            flightResults.add(flightResult);
        }
        return flightResults;
    }


}
