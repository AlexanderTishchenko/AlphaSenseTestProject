package utilities;

import core.BrowserDriver;
import models.annotations.PageUri;

import java.net.URI;

public class UriBuilder {

    public static URI getUri(BrowserDriver driver, Class clazz) {
        String baseUrl = driver.getConfig().getBaseUrl();
        PageUri annotation = (PageUri) clazz.getAnnotation(PageUri.class);
        return URI.create(baseUrl + annotation.uri());
    }

    static Boolean isUriMatches(BrowserDriver driver, Class clazz, URI currentUri) {
        return currentUri.toString().contains(getUri(driver, clazz).toString());
    }
}