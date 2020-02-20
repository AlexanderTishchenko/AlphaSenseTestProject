package tests;

import logger.CustomLog;
import models.users.User;
import models.users.UserCredentials;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.*;
import pages.main.*;
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
    public void createNewUserAccountTest() {
        CustomLog.step(1, "Open Home page: " + UriBuilder.getUri(getBrowser(), HomePage.class));
        HomePage homePage = PageOpening.open(getBrowser(), HomePage.class);

        CustomLog.step(2, "Create a new account");
        AuthenticationPage authenticationPage = homePage.clickSignIn();
        CreateAnAccountPage createAnAccountPage = authenticationPage.createAnAccount(user.getEmail());
        MyAccountPage myAccountPage = createAnAccountPage.FillUserDataAndRegister(user);

        CustomLog.step(3, "Try to Sign in as a new created user");
        authenticationPage = myAccountPage.signOut();
        myAccountPage = authenticationPage.signIn(user.getCredentials());

        CustomLog.step(4, "Verify that the user logged in successfully");
        MatcherAssert.assertThat("Text of Welcome Message is not equal to expected", myAccountPage.getWelcomeMessage(), is(containsString("Welcome to your account. Here you can manage all of your personal information and orders.")));
        MatcherAssert.assertThat("User name is not equal to expected", myAccountPage.getUserName(), is(containsString(user.getFirstName() + " " + user.getLastName())));
    }

    @DataProvider(name = "searchForClothesTest")
    public Object[][] searchForClothesTestData() {
        return new Object[][]{
                {"Dress", 7},
                {"Printed", 5}
        };
    }


    @Test(dataProvider = "searchForClothesTest")
    public void searchForClothesTest(String searchText, int foundAmountExpected) {
        CustomLog.step(1, "Open Home page: " + UriBuilder.getUri(getBrowser(), HomePage.class));
        HomePage homePage = PageOpening.open(getBrowser(), HomePage.class);

        CustomLog.step(2, "Search for Clothes: " + searchText);
        SearchPage searchPage = homePage.search(searchText);

        CustomLog.step(3, "Assert the search results count and verify the results are according to the searched keyword");
        MatcherAssert.assertThat("Found items count is not equal to expected", searchPage.foundItemsCount(), is(equalTo(foundAmountExpected)));
        MatcherAssert.assertThat("Not all products contain searched text", searchPage.areAllItemsContain(searchText), is(true));
    }

}