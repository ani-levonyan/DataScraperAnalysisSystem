
package com.ds_intelligence_arm.storage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


@Entity
public class auto_am_DataRecord {
    String url;
    String html;

    auto_am_DataRecord(String url, String html){
        this.url = url;
        this.html = html;
    }

    //ToDo: Getter for url, price_in_usd, price_in_amd, title, description using any of the getElements method

    public String getUrl() {
        return this.url;
    }

    public String getTitle() {
        return getFirstElementText("title");
    }

    public String getDescription() {
        return getMetaContent("description");
    }

    public String getPriceInUSD() {
        String[] prices = getPrices();
        String priceAMD = prices[1];
        String priceUSD = prices[0];
        return validatePrice(priceUSD, priceAMD, "$");  // Assuming .fnum within price section
    }

    public String getPriceInAMD() {
        String[] prices = getPrices();
        String priceAMD = prices[1];
        String priceUSD = prices[0];
        return validatePrice(priceAMD, priceUSD, "֏");  // Second item in dropdown for AMD
    }



        // Method to retrieve the first matching element's text
        private String getFirstElementText(String selector) {
            Document document = Jsoup.parse(this.html);
            Element element = document.selectFirst(selector);
            return element != null ? element.text() : "";
        }
    
        // Method to retrieve content from a meta tag
        private String getMetaContent(String metaName) {
            Document document = Jsoup.parse(this.html);
            Element metaElement = document.selectFirst("meta[name=" + metaName + "]");
            return metaElement != null ? metaElement.attr("content") : "";
        }
    
        // Generic method for fetching elements by selector
        public List<Element> getElementsBySelector(String selector) {
            Document document = Jsoup.parse(this.html);
            Elements elements = document.select(selector);
            return new ArrayList<>(elements);
        }

        private String[] getPrices() {
            String priceUSD = getFirstElementText("#pricedown li:nth-child(1) span");
            String priceAMD = getFirstElementText("#pricedown li:nth-child(3) span");
            return new String[] {priceUSD, priceAMD};
        }

        private String validatePrice(String primaryField, String secondaryField, String expectedSymbol) {
            if (primaryField != null && primaryField.contains(expectedSymbol)) {
                return primaryField;
            }
            if (secondaryField != null && secondaryField.contains(expectedSymbol)) {
                return secondaryField;
            } else {
                return "";
            }
        }

        public String toString() {
            return "URL: " + url +
                    ", Title: " + this.getTitle() +
                    ", Description: " + this.getDescription() +
                    ", Price in USD: " + this.getPriceInUSD() +
                    ", Price in AMD: " + this.getPriceInAMD();
        }


}
