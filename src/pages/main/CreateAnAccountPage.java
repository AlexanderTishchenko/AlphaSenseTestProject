package pages.main;

import core.BrowserDriver;
import elements.simple.ButtonElement;
import elements.simple.DropDownElement;
import elements.simple.TextFieldElement;
import models.annotations.PageUri;
import models.users.User;
import org.openqa.selenium.support.FindBy;
import utilities.PageOpening;

import java.net.URI;

@PageUri(uri = "index.php?controller=authentication&back=my-account#account-creation")
public class CreateAnAccountPage extends MainPageBase {
    public CreateAnAccountPage(BrowserDriver browser, URI uri) {
        super(browser, uri);
    }

    public MyAccountPage FillUserDataAndRegister(User user) {
        _customerFirstNameField.replace(user.getFirstName());
        _customerLasNameField.replace(user.getLastName());
        _emailField.replace(user.getEmail());
        _passwordField.replace(user.getPassword());
        _firstNameField.replace(user.getFirstName());
        _lastNameField.replace(user.getLastName());
        _addressField.replace(user.getAddress());
        _cityField.replace(user.getCity());
        _stateDropDown.select(user.getState());
        _zipPostalCodeField.replace(user.getZipOrPostalCode());
        _countryDropDown.select(user.getCountry());
        _mobilePhoneField.replace(user.getMobilePhone());
        _addressAliasField.replace(user.getAddressAlias());
        return PageOpening.open(browser, _registerButton, MyAccountPage.class, false);
    }

    @FindBy(id = "customer_firstname")
    private TextFieldElement _customerFirstNameField;

    @FindBy(id = "customer_lastname")
    private TextFieldElement _customerLasNameField;

    @FindBy(id = "email")
    private TextFieldElement _emailField;

    @FindBy(id = "passwd")
    private TextFieldElement _passwordField;

    @FindBy(id = "firstname")
    private TextFieldElement _firstNameField;

    @FindBy(id = "lastname")
    private TextFieldElement _lastNameField;

    @FindBy(id = "address1")
    private TextFieldElement _addressField;

    @FindBy(id = "city")
    private TextFieldElement _cityField;

    @FindBy(id = "id_state")
    private DropDownElement _stateDropDown;

    @FindBy(id = "postcode")
    private TextFieldElement _zipPostalCodeField;

    @FindBy(id = "id_country")
    private DropDownElement _countryDropDown;

    @FindBy(id = "phone_mobile")
    private TextFieldElement _mobilePhoneField;

    @FindBy(id = "alias")
    private TextFieldElement _addressAliasField;

    @FindBy(id = "submitAccount")
    private ButtonElement _registerButton;
}
