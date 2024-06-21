package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("ios_tests")
@Feature("IOS test")
public class IosTest extends TestBase {

    @Test
    void successfulSearchTest() {

        step("Press Text button", () -> {
            $(accessibilityId("Text Button")).click();
        });
        step("Click on the text field and enter text", () -> {
            $(accessibilityId("Text Input")).click();
            $(accessibilityId("Text Input")).sendKeys("Appium");
            $(accessibilityId("Text Input")).pressEnter();
        });
        step("Check that displayed text is the same as input", () -> {
            assertThat($(id("Text Output")).getText()).isEqualTo("Appium");
        });
    }
}
