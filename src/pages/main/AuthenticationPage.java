package pages.main;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.TextFieldElement;
import models.annotations.PageUri;
import models.users.UserCredentials;
import org.openqa.selenium.support.FindBy;
import pages.main.account.MyAccountPage;
import utilities.PageOpening;

import java.net.URI;

@PageUri(uri = "index.php?controller=authentication&back=my-account")
public class AuthenticationPage extends MainPageBase {
    public AuthenticationPage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public CreateAnAccountPage createAnAccount(String email) {
        _emailCreateField.replace(email);
        return PageOpening.open(browser, _createAnAccountButton, CreateAnAccountPage.class, false);
    }

    public MyAccountPage signIn(UserCredentials credentials) {
        _emailSignInField.replace(credentials.getEmail());
        _passwordField.replace(credentials.getPassword());
        return PageOpening.open(browser, _signInButton, MyAccountPage.class, false);
    }

    @FindBy(id = "email_create")
    private TextFieldElement _emailCreateField;

    @FindBy(id = "SubmitCreate")
    private ButtonElement _createAnAccountButton;


    @FindBy(id = "email")
    private TextFieldElement _emailSignInField;

    @FindBy(id = "passwd")
    private TextFieldElement _passwordField;

    @FindBy(id = "SubmitLogin")
    private ButtonElement _signInButton;
}
