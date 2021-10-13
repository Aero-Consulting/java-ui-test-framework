package objects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public abstract class BasePage<T extends BasePage> {
    protected final String url;

    protected BasePage(String url) {
        super();
        this.url = url;
    }

    @Step("Открыть страницу по url")
    public T open() {
        Selenide.open(url);
        return shouldBeOpen();
    }

    @Step("Основные элементы страницы должны быть видны пользователю")
    public abstract T shouldBeOpen();

    @Step("Обновить текущую страницу")
    public T refresh() {
        Selenide.refresh();
        return shouldBeOpen();
    }
}
