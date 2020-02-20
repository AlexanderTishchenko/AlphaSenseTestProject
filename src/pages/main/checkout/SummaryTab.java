package pages.main.checkout;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;
import utilities.PageOpening;

import java.net.URI;

@PageUri(uri = "index.php?controller=order")
public class SummaryTab extends MainPageBase {
    public SummaryTab(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public SignInTab proceedToCheckout() {
        return PageOpening.open(browser, _proceedToCheckoutButton, SignInTab.class, false);
    }

    @FindBy(className = "standard-checkout")
    private ButtonElement _proceedToCheckoutButton;
}
