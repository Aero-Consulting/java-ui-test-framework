package objects.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.desktop.pages.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPageMobile extends MainPage {
    private final SelenideElement searchButton = $("input[name='btnLol']");

    @Override
    @Step("Нажимаем кнопку 'Поиск в Google'")
    public MainPageMobile clickSearchButton() {
        this.searchButton.shouldBe(visible).click();
        return this;
    }
}
