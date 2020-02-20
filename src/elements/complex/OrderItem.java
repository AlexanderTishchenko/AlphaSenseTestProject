package elements.complex;

import elements.simple.ButtonElement;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderItem extends ComplexCustomElementBase {
    public OrderItem(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public void downloadInvoice() {
        _downloadInvoiceButton.click();
    }

    @FindBy(css = "[title='Invoice']")
    private ButtonElement _downloadInvoiceButton;
}
