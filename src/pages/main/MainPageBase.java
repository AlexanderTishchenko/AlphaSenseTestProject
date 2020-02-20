package pages.main;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.TextFieldElement;
import org.openqa.selenium.support.FindBy;
import pages.PageBase;
import utilities.PageOpening;

import java.net.URI;

public abstract class MainPageBase extends PageBase {
    protected MainPageBase(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public AuthenticationPage clickSignIn() {
        return PageOpening.open(browser, _signInButton, AuthenticationPage.class, false);
    }

    public AuthenticationPage signOut() {
        return PageOpening.open(browser, _signOutButton, AuthenticationPage.class, false);
    }

    public SearchPage search(String searchText) {
        _searchField.fill(searchText);
        return PageOpening.open(browser, _searchButton, SearchPage.class, false);

    }

    @FindBy(className = "login")
    private ButtonElement _signInButton;

    @FindBy(className = "logout")
    private ButtonElement _signOutButton;

    @FindBy(id = "search_query_top")
    private TextFieldElement _searchField;

    @FindBy(name = "submit_search")
    private ButtonElement _searchButton;
}
