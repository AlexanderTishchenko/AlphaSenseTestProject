package tests;

import logger.CustomLog;
import models.users.User;
import models.users.UserCredentials;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.*;
import pages.main.AuthenticationPage;
import pages.main.CreateAnAccountPage;
import pages.main.HomePage;
import pages.main.MyAccountPage;
import utilities.PageOpening;
import utilities.Randomizer;
import utilities.UriBuilder;

import static org.hamcrest.CoreMatchers.*;

public class BasicTests extends SeleniumTestBase {

    private User user;

    public BasicTests() {
        user = new User(new UserCredentials(Randomizer.getRandomEmail(), "123456"), "test", "test", "test", "test", "Alabama", "00000", "United States", "+799999999", "My address");
    }

    @Test
    public void createNewUserAccount() {
        CustomLog.step(1, "Open Home page: " + UriBuilder.getUri(getBrowser(), HomePage.class));
        HomePage homePage = PageOpening.open(getBrowser(), HomePage.class);

        CustomLog.step(2, "Create a new account");
        AuthenticationPage authenticationPage = homePage.clickSignIn();
        CreateAnAccountPage createAnAccountPage = authenticationPage.createAnAccount(user.getEmail());
        MyAccountPage myAccountPage = createAnAccountPage.FillUserDataAndRegister(user);

        CustomLog.step(3, "Try to Sign in as a new created user");
        authenticationPage = myAccountPage.signOut();
        myAccountPage = authenticationPage.signIn(user.getCredentials());
        MatcherAssert.assertThat("Text of Welcome Message is not equal to expected", myAccountPage.getWelcomeMessage(), is(containsString("Welcome to your account. Here you can manage all of your personal information and orders.")));
        MatcherAssert.assertThat("User name is not equal to expected", myAccountPage.getUserName(), is(containsString(user.getFirstName() + " " + user.getLastName())));
    }

}