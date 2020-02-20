package pages.main.checkout;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.CheckBox;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;
import utilities.PageOpening;

import java.net.URI;

@PageUri(uri = "index.php?controller=order")
public class ShippingTab extends MainPageBase {
    public ShippingTab(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public void agreeWithTermsOfService() {
        _termsOfServiceCheckBox.select();
    }

    public PaymentTab proceedToCheckout() {
        return PageOpening.open(browser, _proceedToCheckoutButton, PaymentTab.class, false);
    }

    @FindBy(name = "processCarrier")
    private ButtonElement _proceedToCheckoutButton;

    @FindBy(id = "cgv")
    private CheckBox _termsOfServiceCheckBox;
}
