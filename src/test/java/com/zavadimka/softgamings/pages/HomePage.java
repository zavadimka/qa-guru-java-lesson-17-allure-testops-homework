package com.zavadimka.softgamings.pages;

import com.codeborne.selenide.SelenideElement;

import com.zavadimka.softgamings.pages.components.PageSection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public final String pageLabel = "Home Page";
    public final String homePageTitle = "SoftGamings | Создать свое онлайн казино — софт и готовые интернет казино";

    private final ArrayList<String> pageSectionLabels = new ArrayList<>();
    private final ArrayList<SelenideElement> pageSectionSelectors = new ArrayList<>();
    private final ArrayList<String> pageSectionHeaders = new ArrayList<>();
    private final ArrayList<PageSection> homePageSections = PageSection.getAllPageSections(pageSectionLabels, pageSectionSelectors, pageSectionHeaders);


    public HomePage() {
        pageSectionLabels.add("Home");
        pageSectionLabels.add("Solutions");
        pageSectionLabels.add("Products");
        pageSectionLabels.add("Providers");
        pageSectionLabels.add("About");
        pageSectionLabels.add("Clients");
        pageSectionLabels.add("Testimonials");

        pageSectionSelectors.add($("#Home h1"));
        pageSectionSelectors.add($("#Solutions h2"));
        pageSectionSelectors.add($("#Products h2"));
        pageSectionSelectors.add($("#Providers h2"));
        pageSectionSelectors.add($("#About + div h2"));
        pageSectionSelectors.add($("#Clients h2"));
        pageSectionSelectors.add($("#Testimonials h2"));

        pageSectionHeaders.add("Вы хотите создать своё онлайн казино?");
        pageSectionHeaders.add("Наше программное обеспечение для казино");
        pageSectionHeaders.add("Наши продукты");
        pageSectionHeaders.add("250" + "+" + " топовых провайдеров игр за одну интеграцию");
        pageSectionHeaders.add("Преимущества SoftGamings");
        pageSectionHeaders.add("Наши клиенты");
        pageSectionHeaders.add("Отзывы клиентов и партнёров");
    }

    @Step("### Open Home Page")
    public HomePage openPage() {
        open(baseUrl);

        return this;
    }

    @Step("### Verify Home Page title: {0}")
    public HomePage checkTitle(String homePageTitle) {
        Assertions.assertEquals(homePageTitle, title());

        return this;
    }

    @Step("### Check Page section headers: " + pageLabel)
    public HomePage checkPageSectionHeaders() {
        PageSection.checkPageSectionHeaders(homePageSections);

        return this;
    }
}
