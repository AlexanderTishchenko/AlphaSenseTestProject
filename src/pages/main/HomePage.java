package pages.main;

import core.BrowserDriver;
import models.annotations.PageUri;

import java.net.URI;

@PageUri(uri = "index.php")
public class HomePage extends MainPageBase {
    public HomePage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }
}
