package pages.ixigo;

import org.openqa.selenium.WebDriver;

public class FlightResult {
    String airlinesName;
    String depatureTime;
    String price;

    public String getAirlinesName() {
        return airlinesName;
    }

    public void setAirlinesName(String airlinesName) {
        this.airlinesName = airlinesName;
    }

    public String getDepatureTime() {
        return depatureTime;
    }

    public void setDepatureTime(String depatureTime) {
        this.depatureTime = depatureTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FlightResult{" +
                "airlinesName='" + airlinesName + '\'' +
                ", depatureTime='" + depatureTime + '\'' +
                ", price='" + price + '\'' +
                '}'+"\n";
    }
}
