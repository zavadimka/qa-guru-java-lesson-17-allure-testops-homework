package com.zavadimka.softgamings.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

public class HeaderMenuBarItem {
    SelenideElement itemSelector;
    String itemText;
    URL itemUrl;

    public HeaderMenuBarItem() {

    }

    public HeaderMenuBarItem(SelenideElement itemSelector, String itemText, URL itemUrl) {
        this.itemSelector = itemSelector;
        this.itemText = itemText;
        this.itemUrl = itemUrl;
    }


    private HeaderMenuBarItem getItem(String itemSelector, String itemText, String itemUrl) {
        URL url;
        try {
            url = new URL(itemUrl);
        } catch (MalformedURLException e) {
            url = null;
        }

        SelenideElement selector = $(itemSelector);
        return new HeaderMenuBarItem(selector, itemText, url);
    }

    public ArrayList<HeaderMenuBarItem>[] getHeaderMenuBarFromCsv() throws CsvValidationException, IOException {
        ArrayList<HeaderMenuBarItem>[] headerMenuBar = new ArrayList[]{
                readCsv("src/test/resources/menubar/header_menu_home.csv"),
                readCsv("src/test/resources/menubar/header_menu_solutions.csv"),
                readCsv("src/test/resources/menubar/header_menu_products.csv"),
                readCsv("src/test/resources/menubar/header_menu_games.csv"),
                readCsv("src/test/resources/menubar/header_menu_blog.csv"),
                readCsv("src/test/resources/menubar/header_menu_faq.csv"),
                readCsv("src/test/resources/menubar/header_menu_about.csv"),
                readCsv("src/test/resources/menubar/header_menu_contacts.csv"),
        };

        return headerMenuBar;
    }

    private ArrayList<HeaderMenuBarItem> readCsv(String csvPath) throws IOException, CsvValidationException {

        CSVReader reader = new CSVReader(new FileReader(csvPath));
        ArrayList<HeaderMenuBarItem> menuItemsList = new ArrayList<HeaderMenuBarItem>();

        String[] lines;
        while ((lines = reader.readNext()) != null) {
            String itemSelector = lines[0],
                    itemText = lines[1],
                    itemUrl = lines[2];

            HeaderMenuBarItem headerMenuBarItem = getItem(itemSelector, itemText, itemUrl);
            menuItemsList.add(headerMenuBarItem);
        }

        return menuItemsList;
    }

    @Step("Header MenuBar links opening")
    public void headerMenuBarLinksOpeningTest(ArrayList<HeaderMenuBarItem>[] headerMenuBar) {
        for (int i = 7; i >= 0; i--) {
            HeaderMenuBarItem tempItem = headerMenuBar[i].get(0);
            tempItem.itemSelector.hover();

            for (HeaderMenuBarItem item : headerMenuBar[i]) {
                tempItem.itemSelector.hover();
                item.itemSelector.click();

                String currentUrl = webdriver().driver().url();

                String expectedUrl;
                try {
                    expectedUrl = item.itemUrl.toString();
                } catch (Exception e) {
                    System.out.println("No link");
                    continue;
                }

                assertEquals(expectedUrl, currentUrl);
            }
        }
    }

    @Step("Header MenuBar headers and links verification")
    public void headerMenuBarVerification(ArrayList<HeaderMenuBarItem>[] headerMenuBar) {
        for (int i = 0; i < 8; i++) {
            HeaderMenuBarItem tempItem = headerMenuBar[i].get(0);
            tempItem.itemSelector.hover();

            for (HeaderMenuBarItem item : headerMenuBar[i]) {
                String url;
                try {
                    url = item.itemUrl.toString();
                } catch (Exception e) {
                    url = "";
                }

                item.itemSelector.shouldBe(exist).shouldHave(text(item.itemText)).shouldHave(Condition.href(url));
            }
        }
    }

    public void printMenuBar(ArrayList<HeaderMenuBarItem>[] menuBar) {
        for (int i = 0; i < 8; i++) {
            for (HeaderMenuBarItem item : menuBar[i]) {
                System.out.println(item.itemSelector + " "
                        + item.itemText + " "
                        + item.itemUrl);
            }
        }
    }
}
