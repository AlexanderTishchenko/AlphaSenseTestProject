package elements.complex;

import elements.simple.ReadOnlyElement;
import models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchItem extends ComplexCustomElementBase {
    public SearchItem(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public String getProductName() {
        return _productName.getText();
    }

    @FindBy(className = "product-name")
    private ReadOnlyElement _productName;
}
