package pages.main.account;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.ReadOnlyElement;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;
import utilities.PageOpening;

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

    public OrderHistoryPage openOrderHistory() {
        return PageOpening.open(browser, _orderHistoryButton, OrderHistoryPage.class, false);
    }

    @FindBy(className = "info-account")
    private ReadOnlyElement _welcomeMessage;

    @FindBy(css = ".account span")
    private ReadOnlyElement _userLabel;

    @FindBy(css = "[title='Orders']")
    private ButtonElement _orderHistoryButton;
}
