package factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import emulator.Emulator;
import emulator.EmulatorProvider;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.time.Duration;

@Singleton
public class AndroidDriverFactory {

    private final EmulatorProvider emulatorProvider;
    private final Capabilities capabilities;

    @Inject
    public AndroidDriverFactory(EmulatorProvider emulatorProvider, Capabilities capabilities) {
        this.emulatorProvider = emulatorProvider;
        this.capabilities = capabilities;
    }

    @SneakyThrows
    public WebDriver create() {
        Emulator emulator = emulatorProvider.takeAndGet();

        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:%d".formatted(emulator.getPort())),
                capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public void quit(WebDriver driver) {
        emulatorProvider.putBack();
        if (driver != null) {
            driver.quit();
        }
    }
}
