package elements.simple;

import elements.ElementBase;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;

public class CheckBox extends ElementBase {
    public CheckBox(WebElement wrappedElement, ElementContainer elementContainer) {
        super(wrappedElement, elementContainer);
    }

    public void toggle() {
        click();
    }

    public void select() {
        if (!isSelected()) {
            toggle();
        }
    }

    public boolean isSelected() {
        return getWrappedElement().isSelected();
    }
}
