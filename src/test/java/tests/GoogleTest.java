package tests;

import helpers.factories.objects.ObjectFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import objects.desktop.pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Google")
@Feature("Google search")
public class GoogleTest extends BaseTest {
    public MainPage mainPage;

    @BeforeClass(description = "Инициализация PageObjects")
    public void initPages() {
        mainPage = ObjectFactory.init(MainPage.class);
    }

    @BeforeMethod(description = "Открыть страницу")
    public void setUp() {
        mainPage.open();
    }

    @Test(groups = "C1", description = "Поиск на главной страницы Google")
    public void googleSearch() {
        mainPage
                .fillQueryField("Test text")
                .clickSearchButton()
                .searchResultsShouldBeVisible();
    }
}
