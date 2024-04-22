package tests;

import base.TestBase;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.selector.ByAttribute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SavingsCalculatorPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class SavingsCalculatorTest extends TestBase {
    private SavingsCalculatorPage savingsCalculatorPage;

//    @Rule
//    public TextReport textReport = new TextReport().onSucceededTest(true).onFailTest(true);

//    @Rule
//    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);




    @Before
    public void openPage() {
        open("/savingscalculator.php");
        savingsCalculatorPage = new SavingsCalculatorPage();
    }

    @Test
    public void itShouldEnterOneTimeInvestment() {
        //driver.findElement(By.xpath("//input[@placeholder='One time investment']")).sendKeys("25");
        $(byAttribute("placeholder","One time investment")).sendKeys("26");
        System.out.println("elwef");
    }
    @Test
    public void itShouldEnableApplyButton() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        savingsCalculatorPage.getApplyButton().shouldBe(enabled);
    }

    @Test
    public void itShouldDisplayCalculatedAmounts() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        savingsCalculatorPage.getCalculatedTotalIncomeElement().shouldNotBe(empty).shouldHave(text("kr"));
        savingsCalculatorPage.getCalculatedInterestIncomeElement().shouldNotBe(empty).shouldHave(text("kr"));
    }

    @Test
    public void itShouldDisplayCalculatedRisk() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        savingsCalculatorPage.getCalculatedRiskElement().shouldNotBe(empty);
    }


    @Test
    public void itShouldContainFundNameInNewRequest() {
        String fundToSelect = "Hoggwart's Fund";

        savingsCalculatorPage.selectFund(fundToSelect);
        savingsCalculatorPage.enterOneTimeInvestment("25000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        savingsCalculatorPage.applyForSaving();

        assertEquals(
                fundToSelect,
                savingsCalculatorPage.getRecentRequestDetail().findElement(cssSelector("p.fund-description")).getText()
        );
    }

    @Test
    public void itShouldDisplayErrorMessageWhenEmailIsInvalid() {
        savingsCalculatorPage.enterEmail("invalid");
        savingsCalculatorPage.getEmailInputWrapper().shouldHave(cssClass("error"));
    }

    @Test
    public void itShouldHighlightNewRequestOnHover() throws InterruptedException {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.applyForSaving();

        $("div.saving-detail")
                .hover()
                .shouldHave(cssValue("background-color", "rgba(4, 102, 156, 1)"));

    }
}


//p.fund-description
//div.saving-detail