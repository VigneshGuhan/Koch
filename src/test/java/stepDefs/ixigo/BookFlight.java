package stepDefs.ixigo;

import base.Driver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ixigo.LandingPage;

public class BookFlight extends Driver {
    @BeforeClass
    private void initializer(){
        if(driver==null)
            driver = Driver.driverInitializer();
    }

    @Test(priority=1)
    private void verifyLandingPage(){
        appLogin();
        LandingPage landingPage=new LandingPage(driver);
        boolean headerIsDisplayed = landingPage.verifyHeaderIsDisplayed();

        landingPage.clickFlightHeader();
        //landingPage.enterFromCity("PNQ - Pune");
        landingPage.enterToCity("HYD - Hyderabad");
        landingPage.selectDepartureDate("22112020");
        landingPage.selectReturnDate("24112020");
        landingPage.selectTraveller("Adult","2");
        landingPage.selectTraveller("Child","2");
        landingPage.clickSearchBtn();

    }
}
