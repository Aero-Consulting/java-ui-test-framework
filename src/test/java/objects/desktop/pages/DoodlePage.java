package objects.desktop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.BasePage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DoodlePage extends BasePage<DoodlePage> {
    protected final SelenideElement card = $("#title-card");
    private final SelenideElement title = card.$("h2");

    public DoodlePage(String url) {
        super(String.format("/doodles/%s", url));
    }

    @Override
    public DoodlePage shouldBeOpen() {
        card.shouldBe(visible);
        return this;
    }

    @Step("Заголовок дудла '{text}' отображается")
    public void titleShouldBeVisible(String text) {
        title.shouldBe(visible).shouldHave(exactText(text));
    }
}
