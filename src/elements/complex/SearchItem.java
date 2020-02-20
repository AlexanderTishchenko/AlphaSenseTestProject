package elements.complex;

import elements.simple.ButtonElement;
import elements.simple.ReadOnlyElement;
import models.interfaces.ElementContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Wait;

public class SearchItem extends ComplexCustomElementBase {
    public SearchItem(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public String getProductName() {
        return _productName.getText();
    }

    public void addToCart() {
        _productName.hover();
        _addToCartButton.click();
        Wait.waitForAjax(browser, 4);
    }

    @FindBy(className = "product-name")
    private ReadOnlyElement _productName;

    @FindBy(css = "[title='Add to cart']")
    private ButtonElement _addToCartButton;
}
