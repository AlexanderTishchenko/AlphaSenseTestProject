package tests;

import elements.complex.AddToCartWindow;
import elements.complex.OrderItem;
import logger.CustomLog;
import models.users.User;
import models.users.UserCredentials;
import models.users.Users;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.*;
import pages.main.*;
import pages.main.account.MyAccountPage;
import pages.main.account.OrderHistoryPage;
import pages.main.checkout.*;
import utilities.FilesUtilities;
import utilities.PageOpening;
import utilities.Randomizer;
import utilities.UriBuilder;

import static org.hamcrest.CoreMatchers.*;

public class BasicTests extends SeleniumTestBase {

    private User user;

    public BasicTests() {
        user = new User(new UserCredentials(Randomizer.getRandomEmail(), "123456"),
                "test", "test", "test", "test", "Alabama", "00000",
                "United States", "+799999999", "My address");
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
        MatcherAssert.assertThat("Text of Welcome Message is not equal to expected", myAccountPage.getWelcomeMessage(),
                is(containsString("Welcome to your account. Here you can manage all of your personal information and orders.")));
        MatcherAssert.assertThat("User name is not equal to expected",
                myAccountPage.getUserName(), is(containsString(user.getFirstName() + " " + user.getLastName())));
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

        CustomLog.step(2, "Add first product to cart");
        SearchPage searchPage = homePage.search(searchText);

        CustomLog.step(3, "Assert the search results count and verify the results are according to the searched keyword");
        MatcherAssert.assertThat("Found items count is not equal to expected",
                searchPage.foundItemsCount(), is(equalTo(foundAmountExpected)));
        MatcherAssert.assertThat("Not all products contain searched text", searchPage.areAllItemsContain(searchText), is(true));
    }

    @Test()
    public void makeAnOrderTest() {
        String productName = "Blouse";
        UserCredentials testUserCredentials = Users.TestUser.getCredentials();
        CustomLog.step(1, "Open Home page: " + UriBuilder.getUri(getBrowser(), HomePage.class));
        HomePage homePage = PageOpening.open(getBrowser(), HomePage.class);

        CustomLog.step(2, "Add to cart: " + productName);
        AddToCartWindow addToCartWindow = homePage.addToCart(productName);

        CustomLog.step(3, "Proceed to cart");
        SummaryTab summaryTab = addToCartWindow.proceedToCheckout();

        CustomLog.step(4, "Proceed to Sign in");
        SignInTab signInTab = summaryTab.proceedToCheckout();

        CustomLog.step(5, "Proceed to Address Tab");
        AddressTab addressTab = signInTab.signIn(testUserCredentials);

        CustomLog.step(6, "Proceed to Shipping Tab");
        ShippingTab shippingTab = addressTab.proceedToCheckout();

        CustomLog.step(7, "Proceed to Payment Tab");
        shippingTab.agreeWithTermsOfService();
        PaymentTab paymentTab = shippingTab.proceedToCheckout();

        CustomLog.step(8, "Choose payment type");
        paymentTab.payByCheck();

        CustomLog.step(9, "Confirm order");
        paymentTab.confirmOrder();

        CustomLog.step(10, "Verify success message");
        MatcherAssert.assertThat("Success message in not equal to expected",
                paymentTab.getSuccessAlertText(), is(equalTo("Your order on My Store is complete.")));
    }

    @Test()
    public void downloadTheInvoiceTest() {
        FilesUtilities.removeAllDownloadedFiles();
        UserCredentials testUserCredentials = Users.TestUser.getCredentials();

        CustomLog.step(1, "Open Home page: " + UriBuilder.getUri(getBrowser(), HomePage.class));
        HomePage homePage = PageOpening.open(getBrowser(), HomePage.class);

        CustomLog.step(2, "Sign in as a test user");
        AuthenticationPage authenticationPage = homePage.clickSignIn();
        MyAccountPage myAccountPage = authenticationPage.signIn(testUserCredentials);

        CustomLog.step(3, "Open Order History page");
        OrderHistoryPage orderHistoryPage = myAccountPage.openOrderHistory();

        CustomLog.step(4, "Download the first invoice");
        OrderItem orderItem = orderHistoryPage.getOrderList().iterator().next();
        orderItem.downloadInvoice();

        CustomLog.step(5, "Verify that file is downloaded");
        boolean exists = FilesUtilities.isFileExist(getBrowser(),"IN.*\\.pdf");
        MatcherAssert.assertThat("Downloaded document is not found", exists, is(true));
    }
}