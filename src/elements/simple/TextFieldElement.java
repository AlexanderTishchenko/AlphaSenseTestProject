package elements.simple;

import elements.ElementBase;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;

public class TextFieldElement extends ElementBase {
    public TextFieldElement(WebElement wrappedElement, ElementContainer container) {
        super(wrappedElement, container);
    }

    public void fill(String text) {
        getWrappedElement().sendKeys(text);
    }

    public void replace(String text) {
        getWrappedElement().clear();
        fill(text);
    }
}