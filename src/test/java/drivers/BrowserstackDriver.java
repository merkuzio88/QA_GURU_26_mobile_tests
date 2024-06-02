package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        // Основные возможности драйвера для W3C
        MutableCapabilities w3cCaps = new MutableCapabilities();

        // Параметры платформы (устройство и ОС)
        w3cCaps.setCapability("platformName", "Android");
        w3cCaps.setCapability("appium:deviceName", "Google Pixel 7 Pro");
        w3cCaps.setCapability("appium:osVersion", "13.0");

        // Параметры приложения
        w3cCaps.setCapability("appium:app", "bs://sample.app");

        // Учетные данные и параметры для BrowserStack
        MutableCapabilities browserstackOptions = new MutableCapabilities();
        browserstackOptions.setCapability("userName", "bsuser_mpduwc");
        browserstackOptions.setCapability("accessKey", "8o2TCk438GnhCfmDBTPF");
        browserstackOptions.setCapability("projectName", "BrowserStack Sample");
        browserstackOptions.setCapability("buildName", "browserstack-build-1");
        browserstackOptions.setCapability("sessionName", "first_test");

        // Включаем BrowserStack опции в W3C возможности
        w3cCaps.setCapability("bstack:options", browserstackOptions);

        // Подключаем WebDriver с заданными возможностями
        try {
            return new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), w3cCaps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}