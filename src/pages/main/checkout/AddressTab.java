package pages.main.checkout;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;
import utilities.PageOpening;

import java.net.URI;

@PageUri(uri = "index.php?controller=order")
public class AddressTab extends MainPageBase {
    public AddressTab(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public ShippingTab proceedToCheckout() {
        return PageOpening.open(browser, _proceedToCheckoutButton, ShippingTab.class, false);
    }

    @FindBy(name = "processAddress")
    private ButtonElement _proceedToCheckoutButton;
}
