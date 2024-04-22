package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SavingsCalculatorPage {
    private SelenideElement emailInput = $(byId("emailInput"));
    private SelenideElement yearsInput = $(byId("yearsInput"));
    private SelenideElement oneTimeInvestmentInput = $(byId("oneTimeInvestmentInput"));
    private SelenideElement fundSelect = $(byId("fundSelect"));;

    //    private SelenideElement applyButton = $(byId("button.btn"));
    private SelenideElement applyButton = $("button.btn-success");
    private SelenideElement resultElement = $("div.result");
    private SelenideElement mostRecentSavingsDetail = $("ul.saving-list").find("div.saving-detail");

    public SavingsCalculatorPage() {
        page(this);
    }

    public void enterEmail(String email) {
        $(emailInput).val(email).pressTab();
    }

    public void enterYears(int years) {
        $(yearsInput).val(String.valueOf(years));
    }

    public void enterOneTimeInvestment(String amount) {
        $(oneTimeInvestmentInput).val(amount);
    }

    public void selectFund(String fundToSelect) {
        new Select(fundSelect).selectByVisibleText(fundToSelect);
//        fundSelect.selectOption(fundToSelect);
    }

    public void applyForSaving() {

        $("button.btn-success").click();
    }


    public SelenideElement getCalculatedTotalIncomeElement() {

//        return $(resultElement).find(By.xpath("./div[1]/p"));
        return resultElement.find("div").find("p");
    }

    public SelenideElement getCalculatedInterestIncomeElement() {
//        return $(resultElement).find(By.xpath("./div[2]/p"));
        return resultElement.find("div", 1).find("p");
    }

    public SelenideElement getCalculatedRiskElement() {

//        return $(resultElement).find(By.xpath("./div[3]/p"));
        return resultElement.find("div", 2).find("p");
    }

    public SelenideElement getRecentRequestDetail() {
        return mostRecentSavingsDetail;
    }

    public SelenideElement getApplyButton() {
        return $(applyButton);
    }

    public SelenideElement getEmailInputWrapper(){
        return $(emailInput).parent();
        //or I can use closest, instead of parents
//        return $(emailInput).closest("div");
    }
}