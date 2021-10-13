package objects.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.desktop.pages.DoodlePage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class DoodlePageMobile extends DoodlePage {
    private final SelenideElement title = card.$("h3");

    public DoodlePageMobile(String url) {
        super(url);
    }

    @Step("Заголовок дудла '{text}' отображается")
    public void titleShouldBeVisible(String text) {
        title.shouldBe(visible).shouldHave(exactText(text));
    }
}
