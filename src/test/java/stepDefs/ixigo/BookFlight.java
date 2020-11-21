package stepDefs.ixigo;

import base.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ixigo.FlightResult;
import pages.ixigo.LandingPage;
import pages.ixigo.ResultPage;

import java.util.List;
import java.util.stream.Collectors;

public class BookFlight extends Driver {
    @BeforeClass
    private void initializer(){
        if(driver==null)
            driver = Driver.driverInitializer();
    }

    @Test(priority=1)
    @Parameters({"fromCity","toCity","fromDate","toDate","Passenger","passengerCount"})
    private void verifyLandingPage(String fromCity,String toCity,String fromDate,String toDate,String passenger,String passengerCount){
        appLogin();
        LandingPage landingPage=new LandingPage(driver);

        Assert.assertTrue(landingPage.verifyHeaderIsDisplayed(),"Verify Ixigo Header is Displayed");


        landingPage.clickFlightHeader();
        landingPage.enterFromCity(fromCity);
        landingPage.enterToCity(toCity);
        landingPage.selectDepartureDate(fromDate);
        landingPage.selectReturnDate(toDate);
        landingPage.selectTraveller(passenger,passengerCount);
        landingPage.clickSearchBtn();
    }

    @Test(priority = 2,dependsOnMethods = "verifyLandingPage")
    public void verifyResultPage(){
        ResultPage resultPage=new ResultPage(driver);
        resultPage.waitForResultPageToLoadResults();


        resultPage.filterStopSelect("Non stop");
        List<String> filterOptions = resultPage.getFilterOptions();
        System.out.println("filterOptions = " + filterOptions.toString());
        List<String> tImeFilterList = resultPage.getTImeFilterList();
        System.out.println("tImeFilterList = " + tImeFilterList.toString());
        List<String> airlineFilterLists = resultPage.getAirlineFilterLists();
        System.out.println("airlineFilterLists= " + airlineFilterLists.toString());

        List<FlightResult> flightResultDetails = resultPage.getFlightResultDetails();
        flightResultDetails.stream().forEach(fr -> System.out.print(fr.toString()));

        int priceValue=5000;
        List<FlightResult> filteredFlightResult = flightResultDetails.stream().filter(flightRes -> Integer.parseInt(flightRes.getPrice()) < priceValue).collect(Collectors.toList());
        System.out.println("Filtered flight list : ");
        filteredFlightResult.forEach(System.out::println);
    }
}
