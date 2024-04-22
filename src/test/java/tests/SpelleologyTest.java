package tests;

import base.TestBase;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.*;
import static utils.DataUtils.getExpectedSpells;

public class SpelleologyTest extends TestBase {

    @Before
    public void openPage() {
        open( "/spelleology.php");
    }

    @Test
    public void itShouldContainSpells() throws FileNotFoundException {
        $("ul.spells")
                .findAll("li")
                .shouldHave(texts(getExpectedSpells()));
    }

    @Test
    public void itShouldDisplayTortureSpell() {
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .find(text("tortures a person"))
                .click();

        $("div.modal-container")
                .should(appear)
                .shouldHave(text("Crucio"));
    }

    @Test
    public void itShouldExcludeSpells() {
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .exclude(readonly)
                .exclude(hidden)
                .exclude(matchText("^opens.*"))
                .exclude(matchText("^shoots.*"))
                .exclude(matchText("^enlarges.*"))
                .exclude(matchText("^[a|b|c|d].*"))
                .forEach(selenideElement -> System.out.println(selenideElement.getText()));
    }

    @Test
    public void itShouldFilterShotsSpells() {
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .filterBy(matchText("^moves.*"))
                .forEach(selenideElement -> System.out.println(selenideElement.getText()));
    }

    @Test
    public void itShouldFilterSpells() {
        $("input").sendKeys("tortures a person");
        $$("ul.spells li").shouldHave(size(1));
    }
}
