package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class LoginPage extends AbsBasePage {

    private final SelenideElement userNameInputField = $(id("ru.otus.wishlist:id/username_text_input"));
    private final SelenideElement passwordInputField = $(id("ru.otus.wishlist:id/password_text_input"));
    private final SelenideElement logInButton = $(id("ru.otus.wishlist:id/Log_in_button"));

    public void login(String username, String password) {
        userNameInputField.shouldBe(visible).sendKeys(username);
        passwordInputField.shouldBe(visible).sendKeys(password);
        logInButton.shouldBe(visible).click();
    }
}