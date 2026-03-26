package factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class AndroidDriverModule extends AbstractModule {

    @Provides
    @Singleton
    public WebDriver provideWebDriver(AndroidDriverFactory factory) {
        return factory.create();
    }

    @Provides
    @Singleton
    public Capabilities provideCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setApp("http://wiremock:8080/wishlist.apk");
        options.fullReset();
        return options;
    }
}