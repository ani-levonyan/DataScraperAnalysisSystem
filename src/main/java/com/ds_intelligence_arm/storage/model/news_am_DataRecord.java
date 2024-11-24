
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
public class news_am_DataRecord {
    String url;
    String html;

    news_am_DataRecord(String url, String html){
        this.url = url;
        this.html = html;
    }

    //ToDo: Getter for url, date, title, text using any of the getElements method

    public String getUrl() {
        return this.url;
    }

    public String getDate() {
        return getFirstElementText("span.news-time");
    }

    public String getTitle() {
        String title = getMetaContent("og:title");
        if (title.isEmpty()) {
            title = getFirstElementText("title");
        }
        return title;
    }

    public String getText() {
        List<Element> paragraphs = getElementsBySelector("div.article-text p");
        StringBuilder text = new StringBuilder();
        for (Element paragraph : paragraphs) {
            text.append(paragraph.text()).append("\n");
        }
        return text.toString().trim();
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

    public String toString() {
        return "URL: " + getUrl() +
                ", Date: " + getDate() +
                ", Title: " + getTitle() +
                ", Text: " + getText();
    }
}
