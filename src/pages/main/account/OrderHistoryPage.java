package pages.main.account;

import core.BrowserDriver;
import elements.complex.OrderItem;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;

import java.net.URI;
import java.util.List;

@PageUri(uri = "index.php?controller=history")
public class OrderHistoryPage extends MainPageBase {
    public OrderHistoryPage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public List<OrderItem> getOrderList() {
        return _orders;
    }

    @FindBy(css = "#order-list tbody>tr")
    private List<OrderItem> _orders;
}
