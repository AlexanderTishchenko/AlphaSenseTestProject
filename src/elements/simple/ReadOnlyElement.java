package elements.simple;

import elements.ElementBase;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;

public class ReadOnlyElement extends ElementBase {
    public ReadOnlyElement(WebElement wrappedElement, ElementContainer container) {
        super(wrappedElement, container);
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}