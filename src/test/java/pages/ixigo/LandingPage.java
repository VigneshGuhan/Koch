package pages.ixigo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="ixiLogoImg")
    private WebElement headerLogoImg;

    @FindBy(css="a[href='/flights']")
    private WebElement flightNavHeader;


    @FindBy(xpath="//div[text()='From']/following-sibling::input[@placeholder]")
    private WebElement fromCityInput;
    @FindBy(xpath="//div[text()='From']/../following-sibling::div[contains(@class,'clear-input')]")
    private WebElement fromCityInputClearBtn;

    @FindBy(xpath="//div[text()='To']/following-sibling::input[@placeholder]")
    private WebElement toCityInput;
    @FindBy(xpath="//div[text()='To']/../following-sibling::div[contains(@class,'clear-input')]")
    private WebElement toInputClearBtn;

    @FindBy(css="input[placeholder='Depart']")
    private WebElement departDateInput;

    @FindBy(css="input[placeholder='Return']")
    private WebElement returnDateInput;

    @FindBy(xpath="//div[text()='Travellers | Class']/following-sibling::input[@placeholder]")
    private WebElement travellerOrClassInput;

    @FindBy(css="div.search button")
    private WebElement searchBtn;

   /* @FindBy(xpath="//div[text()='To']/following-sibling::input[@placeholder]")
    private WebElement toCityInput;*/

    public boolean verifyHeaderIsDisplayed(){
        return ElementUtils.isPresent(driver, headerLogoImg);
    }

    public void clickFlightHeader(){
        WebDriverWait wait=new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(flightNavHeader)).click();
    }
//PNQ - Pune
    public void enterFromCity(String fromCity){
    /*WebDriverWait wait=new WebDriverWait(driver, 5);
        WebElement fromCityElement = wait.until(ExpectedConditions.visibilityOf(fromCityInput));*/

        if(ElementUtils.isPresent(driver,fromCityInputClearBtn))
            fromCityInputClearBtn.click();

        fromCityInput.click();
       // fromCityInput.clear();
        fromCityInput.sendKeys(fromCity);
        String fromXpath=String.format("//div[./div[text()='From']]/following-sibling::div//div[contains(text(),'%s')]",fromCity);
        WebDriverWait wait=new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fromXpath))).click();

    }

    public void enterToCity(String toCity){
      //  WebDriverWait wait=new WebDriverWait(driver, 5);
       // wait.until(ExpectedConditions.visibilityOf(toCityInput));
        toCityInput.click();
        toCityInput.sendKeys(toCity);

        String toXpath=String.format("//div[./div[text()='To']]/following-sibling::div//div[contains(text(),'%s')]",toCity);
        WebDriverWait wait=new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toXpath))).click();
    }

    /*public void selectFromSuggestion(String city){
         WebDriverWait wait=new WebDriverWait(driver, 5);
         String xpath="";
       wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath()));
    }*/

    public boolean selectDepartureDate(String dateInDDMMYYYY) {
        departDateInput.click();
        String xpathDepDate = String.format("//div[contains(@class,'flight-dep-cal')]//td[@data-date='%s']", dateInDDMMYYYY);
        WebDriverWait wait=new WebDriverWait(driver, 5);
        boolean enabled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDepDate))).isEnabled();
        if(enabled) {
            driver.findElement(By.xpath(xpathDepDate)).click();
            return true;
        }
        else return false;

    }

    public boolean selectReturnDate(String dateInDDMMYYYY) {
        returnDateInput.click();
        String xpathRetDate = String.format("//div[contains(@class,'flight-ret-cal')]//td[@data-date='%s']", dateInDDMMYYYY);
        WebDriverWait wait=new WebDriverWait(driver, 5);
        boolean enabled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRetDate))).isEnabled();
        if(enabled) {
            driver.findElement(By.xpath(xpathRetDate)).click();
            return true;
        }
        else return false;

    }

    public boolean selectTraveller(String category, String number){
        travellerOrClassInput.click();
        WebDriverWait wait=new WebDriverWait(driver, 10);
        String xpathRetDate = String.format("//div[@class='number-counter'][.//div[text()='%s']]//span[@data-val='%s']", category,number);
        boolean enabled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRetDate))).isEnabled();
        if(enabled) {
            driver.findElement(By.xpath(xpathRetDate)).click();
            travellerOrClassInput.click();
            return true;

        }
        else return false;
    }

    public void clickSearchBtn(){
        searchBtn.click();
    }

}
