package elements.simple;

import elements.ElementBase;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;

public class ButtonElement extends ElementBase {

    public ButtonElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}