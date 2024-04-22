package tests;

import base.TestBase;
import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SortingHatTest extends TestBase {


    @Test
    public void itShouldDisplayNameOfHouse() {
        open( "/sortinghat.php");
        $(By.cssSelector("button")).click();
        $("img.loading").should(Condition.appear, Duration.ofMillis(10000)).should(disappear, Duration.ofMillis(10000));
//        $("img.loading").waitUntil(appear, 10000).waitUntil(disappear, 10000); In video he did this, my waitUntil is red
        $("p.result").shouldBe(visible).shouldNotBe(empty);


//        new WebDriverWait(driver, 10)
//            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.loading")));
//        new WebDriverWait(driver, 10)
//            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img.loading")));
//        Assert.assertFalse(driver.findElement(By.cssSelector("p.result")).getText().isEmpty());
    }

    @Test
    public void itShouldDisplayGryffindor() {
        open(  "/sortinghat.php");
        String generateHouse = "";
        while (!generateHouse.equals("Gryffindor")) {
            $("button").shouldBe(enabled).click();
            $("img.loading").should(appear).should(disappear);
//            $("p.result").shouldBe(visible).shouldNotBe(empty);
//            generateHouse = $("p.result").getText();
            // chaining I join two line to one
            generateHouse = $("p.result").shouldBe(visible).shouldNotBe(empty).getText();
        }


    }
}
