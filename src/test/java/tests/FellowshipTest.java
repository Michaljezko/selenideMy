package tests;

import base.TestBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FellowshipTest extends TestBase {

    @Before
    public void openPage() {
        open( "/fellowship.php");
    }

    @Test
    public void itShouldContainNameForEachFellow() {
        for (SelenideElement fellowElement : getFellowElements()) {
            fellowElement.find("h1").shouldNotBe(empty);
        }
    }

    @Test
    public void itShouldContainSpecifiedFellows() {
        List<String> fellowNames = new ArrayList<String>();

        for (WebElement fellowElement : getFellowElements()) {
            fellowNames.add(fellowElement.findElement(By.cssSelector("h1")).getText());
        }
        Assert.assertTrue(fellowNames.contains("Gandalf"));
        Assert.assertTrue(fellowNames.contains("Aragorn"));
        Assert.assertTrue(fellowNames.contains("Frodo"));
    }

    @Test
    public void itShouldDisplayMessageComplete() {
        List<String> fellowsToSelect = new ArrayList<String>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");

        for (String fellowToSelect : fellowsToSelect) {
            selectFellow(fellowToSelect);
        }
        $("div.points-left h3").shouldHave(text("Complete"));

    }

    @Test
    public void itShouldDisplayPointsForEachFellow() {
        getFellowElements().forEach(selenideElement -> selenideElement.find("div.fellow-points h2").shouldNotBe(empty));
    }

    @Test
    public void itShouldHighlightFellows() {
        List<String> fellowsToSelect = new ArrayList<String>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");

        for (String fellowToSelect : fellowsToSelect) {
            selectFellow(fellowToSelect);
        }
        $("ul.list-of-fellows")
                .findAll("li > div")
                .filterBy(cssClass("active"))
                .shouldHave(textsInAnyOrder(fellowsToSelect));
    }
    private void selectFellow(String fellowName) {
        $(byText(fellowName)).click();
    }
    private ElementsCollection getFellowElements() {
        return $$("ul.list-of-fellows li");
    }
}
