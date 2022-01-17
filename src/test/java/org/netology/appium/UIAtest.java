package org.netology.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UIAtest {

    private AndroidDriver driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
//        desiredCapabilities.setCapability("deviceName", "emulator-5556" );
//        desiredCapabilities.setCapability("appium:app", "C:\\Users\\Admin\\IdeaProjects\\netology-appium-UIautomator\\app\\build\\outputs\\apk\\androidTest\\debug\\app-debug-androidTest.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testInputSpaces() {

        MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("UiAutomator");
        el2.click();
        MobileElement textBefore = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el3.sendKeys("    ");
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el4.click();
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void testInputNone() {

        MobileElement textBefore = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void testChangeTextAndNewActivity() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys("Hallo");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        assertEquals("Hallo", textAfter.getText());
    }


    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}


