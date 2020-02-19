package pages.main;

import core.BrowserDriver;
import elements.simple.ReadOnlyElement;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "index.php?controller=my-account")
public class MyAccountPage extends MainPageBase {
    public MyAccountPage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public String getWelcomeMessage() {
        return _welcomeMessage.getText();
    }

    public String getUserName() {
        return _userLabel.getText();
    }

    @FindBy(className = "info-account")
    private ReadOnlyElement _welcomeMessage;

    @FindBy(css = ".account span")
    private ReadOnlyElement _userLabel;
}
