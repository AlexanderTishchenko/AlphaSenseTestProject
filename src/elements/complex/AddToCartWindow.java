package elements.complex;

import elements.simple.ButtonElement;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.main.checkout.SummaryTab;
import utilities.PageOpening;

public class AddToCartWindow extends ComplexCustomElementBase {
    public AddToCartWindow(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public SummaryTab proceedToCheckout() {
        return PageOpening.open(browser, _proceedToCheckoutButton, SummaryTab.class, false);
    }

    @FindBy(css = "[title='Proceed to checkout']")
    private ButtonElement _proceedToCheckoutButton;
}
