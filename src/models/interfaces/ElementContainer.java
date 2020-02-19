package models.interfaces;

import org.openqa.selenium.SearchContext;
import pages.PageBase;

public interface ElementContainer extends SearchContext {
    PageBase getPage();

    SearchContext getSearchContext();
}
