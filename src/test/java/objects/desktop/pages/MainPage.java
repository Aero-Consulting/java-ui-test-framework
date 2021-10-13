package objects.desktop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.BasePage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage<MainPage> {
    private final SelenideElement searchButton = $("input[name='btnK']");
    private final SelenideElement searchContent = $("#search");
    private final SelenideElement queryField = $("input[name='q']");

    public MainPage() {
        super("/");
    }

    private ElementsCollection searchResults() {
        return searchContent.$$("div.g");
    }

    @Step("Главная страница Google должна быть открыта")
    public MainPage shouldBeOpen() {
        queryField.shouldBe(visible);
        return this;
    }

    @Step("Вводим значение '{queryString}' в поле поиска")
    public MainPage fillQueryField(String queryString) {
        queryField.setValue(queryString);
        return this;
    }

    @Step("Нажимаем кнопку 'Поиск в Google'")
    public MainPage clickSearchButton() {
        searchButton.shouldBe(visible).click();
        return this;
    }

    @Step("Результаты запроса отображаются")
    public void searchResultsShouldBeVisible() {
        searchContent.shouldBe(visible);
        searchResults().shouldHave(sizeGreaterThan(0));
    }
}
