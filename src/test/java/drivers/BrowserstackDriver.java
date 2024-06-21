package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthConfig;
import config.PlatformsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    protected static PlatformsConfig platformsConfig = ConfigFactory.create(PlatformsConfig.class, System.getProperties());
    protected static AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities w3cCaps = new MutableCapabilities();

        w3cCaps.setCapability("appium:deviceName", platformsConfig.getDevice());
        w3cCaps.setCapability("appium:osVersion", platformsConfig.getOS());

        w3cCaps.setCapability("appium:app", platformsConfig.getApp());

        MutableCapabilities browserstackOptions = new MutableCapabilities();
        browserstackOptions.setCapability("userName", authConfig.getUser());
        browserstackOptions.setCapability("accessKey", authConfig.getKey());
        browserstackOptions.setCapability("projectName", platformsConfig.getProject());
        browserstackOptions.setCapability("buildName", platformsConfig.getBuild());
        browserstackOptions.setCapability("sessionName", platformsConfig.getName());

        w3cCaps.setCapability("bstack:options", browserstackOptions);

        try {
            return new RemoteWebDriver(
                    new URL(authConfig.getRemoteUrl()), w3cCaps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}