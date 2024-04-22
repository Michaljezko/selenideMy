package tests;

import base.TestBase;
import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.Screenshot;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GosslingatorTest extends TestBase {

//    @Rule
//    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests(). succeededTests();
//    @Rule
//    public SoftAsserts = new SoftAsserts();

    @Before
    public void openPage() {
        open( "/gosslingator.php");
    }

    @Test
    public void itShouldDisplayTitle() {
        Assert.assertEquals("GOSLINGATE ME", $(".ryan-title").getText());
    }

    @Test
    public void itShouldDisplayTitleSelenide() {
        Assert.assertEquals("GOSLINGATE ME", $(".ryan-title").getText());
    }

    @Test
    public void itShouldDisplayRyanImage() {
        $("img");
    }
    @Test
    public void itShouldAddOneRyan() {
//        Configuration.assertionMode = AssertionMode.SOFT;
       addRyan();
       screenshot("ryan");
        $("div.ryan-counter h2").shouldHave(text("1"));
        $("div.ryan-counter h3").shouldHave(text("ryan"));
    }

    @Test
    public void itShouldAddOneRyanSelenide() {
        addRyan();

        String actualNumberOfRyans = $(By.id("ryanCounter")).getText();
        Assert.assertEquals("1", actualNumberOfRyans);

        System.out.println("Number of ryans: " + $("div.ryan-counter h2").getText());
        Assert.assertEquals("ryan", $("div.ryan-counter h3").getText());
    }

    @Test
    public void itShouldTwoRyans() {
        addRyan( 2);

        String actualNumberOfRyans = $(By.id("ryanCounter")).getText();
        String actualRyanDescription = $("div.ryan-counter h3").getText();

        Assert.assertEquals("2", actualNumberOfRyans);
        Assert.assertEquals("ryans", actualRyanDescription);
    }


    @Test
    public void itShouldDisplayWarningMessage() {
        addRyan(50);
            $(By.cssSelector("h1.tooManyRyans")).shouldHave(Condition.exactText(
                    "NUMBER OF\n" +
                    "RYANS\n" +
                    "IS TOO DAMN\n" +
                    "HIGH"));
    }

    @Test
    public void itShouldDisplayNoRyanOnPageOpen() {
        $$("img").shouldHave(size(0));
    }

    @Test
    public void itShouldRemoveRyanHeadByClickingOnImage() {
        addRyan(30);
        $$("img").forEach(SelenideElement::click);
        $$("img").shouldHave(CollectionCondition.size(0));
    }

    private void addRyan() {
        $(By.id("addRyan")).click();
    }

    private void addRyan(int numberOfRyans){
        for (int i = 0; i < numberOfRyans; i++) {
            $(By.id("addRyan")).click();
        }
    }

}
