package tests;

import annotations.Page;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import objects.desktop.pages.DoodlePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Google")
@Feature("Google doodles")
public class GoogleDoodlesTest extends BaseTest {
    @Page(args = {"nigeria-independence-day-2021"})
    private DoodlePage doodlePage;

    @BeforeMethod(description = "Открыть страницу дудла")
    public void setUp() {
        doodlePage.open();
    }

    @Test(groups = "C2 ", description = "Дудл Google")
    public void googleDoodle() {
        doodlePage.titleShouldBeVisible("Nigeria Independence Day 2021");
    }
}
