package pages.main.checkout;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.TextFieldElement;
import models.annotations.PageUri;
import models.users.UserCredentials;
import org.openqa.selenium.support.FindBy;
import pages.main.MainPageBase;
import utilities.PageOpening;

import java.net.URI;

@PageUri(uri = "index.php?controller=authentication")
public class SignInTab extends MainPageBase {
    public SignInTab(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public AddressTab signIn(UserCredentials credentials) {
        _emailSignInField.fill(credentials.getEmail());
        _passwordField.fill(credentials.getPassword());
        return PageOpening.open(browser, _signInButton, AddressTab.class, false);
    }

    @FindBy(id = "email")
    private TextFieldElement _emailSignInField;

    @FindBy(id = "passwd")
    private TextFieldElement _passwordField;

    @FindBy(id = "SubmitLogin")
    private ButtonElement _signInButton;
}
