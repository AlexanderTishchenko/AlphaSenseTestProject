package pages.main.checkout;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.ReadOnlyElement;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;

import java.net.URI;

@PageUri(uri = "index.php?")
public class PaymentTab extends MainPageBase {
    public PaymentTab(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public PaymentTab payByCheck() {
        _payByCheckButton.click();
        reinit();
        return this;
    }

    public PaymentTab confirmOrder() {
        _confirmOrderButton.click();
        reinit();
        return this;
    }

    public String getSuccessAlertText() {
        return _successAlert.getText();
    }

    @FindBy(className = "alert-success")
    private ReadOnlyElement _successAlert;

    @FindBy(xpath = ".//*[.='I confirm my order']")
    private ButtonElement _confirmOrderButton;

    @FindBy(className = "cheque")
    private ButtonElement _payByCheckButton;
}
