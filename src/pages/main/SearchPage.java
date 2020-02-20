package pages.main;

import core.BrowserDriver;
import elements.complex.SearchItem;
import logger.CustomLog;
import models.annotations.PageUri;
import org.openqa.selenium.support.FindBy;

import java.net.URI;
import java.util.List;

@PageUri(uri = "index.php?controller=search")
public class SearchPage extends MainPageBase {
    public SearchPage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public int foundItemsCount() {
        return _searchItems.size();
    }

    public boolean areAllItemsContain(String searchText) {
        boolean areAllItemsContain = true;
        for (SearchItem product: _searchItems) {
            String productName = product.getProductName();
            boolean isEqual = productName.contains(searchText);
            areAllItemsContain &= isEqual;
            if(!isEqual){
                CustomLog.error(String.format("Product '%s' doesn't contain search text: '%s'", productName, searchText));
            }
        }
        return areAllItemsContain;
    }

    @FindBy(css = "ul.product_list>li")
    private List<SearchItem> _searchItems;
}
