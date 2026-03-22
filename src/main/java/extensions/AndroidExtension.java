package extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.AndroidDriverFactory;
import factory.AndroidDriverModule;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

@NullMarked
public class AndroidExtension implements TestInstancePostProcessor,
        BeforeEachCallback,
        AfterEachCallback {

    private final Injector injector = Guice.createInjector(new AndroidDriverModule());

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        injector.injectMembers(testInstance);

    }

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver driver = injector.getInstance(WebDriver.class);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open();
    }


    @Override
    public void afterEach(ExtensionContext context) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        injector.getInstance(AndroidDriverFactory.class).quit(driver);
    }
}
