package pages;


import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class LoginPage extends AbsBasePage {

    private final SelenideElement userNameInputField =
            $(id("wishlist:id/username_text_input"))
                    .as("Поле ввода имени пользователя");

    private final SelenideElement passwordInputField =
            $(id("wishlist:id/password_text_input"))
                    .as("Поле ввода пароля");

    private final SelenideElement logInButton =
            $(id("wishlist:id/Log_in_button"))
                    .as("Кнопка входа");

    public void login(String username, String password) {
        userNameInputField
                .shouldBe(visible.because("Поле ввода имени пользователя не видно на экране"))
                .sendKeys(username);
        passwordInputField
                .shouldBe(visible.because("Поле ввода пароля не видно на экране"))
                .sendKeys(password);
        logInButton
                .shouldBe(visible.because("Кнопка входа не видна на экране"))
                .click();
    }
}
