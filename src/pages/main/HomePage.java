package pages.main;

import core.BrowserDriver;
import elements.complex.AddToCartWindow;
import elements.complex.SearchItem;
import models.annotations.PageUri;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.FindBy;

import java.net.URI;
import java.util.List;

@PageUri(uri = "index.php")
public class HomePage extends MainPageBase {
    public HomePage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public AddToCartWindow addToCart(String productName) {
        for (SearchItem product : _popularProductsList) {
            if (product.getProductName().contains(productName)) {
                product.addToCart();
                return getElement(By.id("layer_cart"), AddToCartWindow.class);
            }
        }
        throw new NotFoundException("There is no product with name " + productName);
    }

    @FindBy(css = "#homefeatured>li")
    private List<SearchItem> _popularProductsList;
}
