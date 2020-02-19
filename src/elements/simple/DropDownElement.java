package elements.simple;

import elements.ElementBase;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownElement extends ElementBase {
    public DropDownElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public void select(String value) {
        Select dropdown = new Select(getWrappedElement());
        dropdown.selectByVisibleText(value);
    }
}