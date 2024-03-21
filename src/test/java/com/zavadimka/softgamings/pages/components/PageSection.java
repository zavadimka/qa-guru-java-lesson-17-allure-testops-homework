package com.zavadimka.softgamings.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class PageSection {

    public String headerLabel;
    public SelenideElement headerSelector;
    public String headerText;

    private PageSection(String headerLabel, SelenideElement headerSelector, String headerText) {
        this.headerLabel = headerLabel;
        this.headerSelector = headerSelector;
        this.headerText = headerText;
    }

    private static PageSection getPageSection(String headerLabel, SelenideElement headerSelector, String headerText){
        return new PageSection(headerLabel, headerSelector, headerText);
    }

    public static ArrayList<PageSection> getAllPageSections(ArrayList<String> headerLabels, ArrayList<SelenideElement> headerSelectors, ArrayList<String> headers){
        final ArrayList<PageSection> PageSections = new ArrayList<>();
        for (int i = 0; i < headerLabels.size(); i++) {
            PageSections.add(getPageSection(headerLabels.get(i), headerSelectors.get(i), headers.get(i)));
        }
        return PageSections;
    }

    @Step("Check the Page section headers")
    public static void checkPageSectionHeaders(ArrayList<PageSection> pageSections) {
        for (PageSection section : pageSections){
            section.headerSelector.shouldBe(visible).shouldHave(text(section.headerText));
        }
    }
}