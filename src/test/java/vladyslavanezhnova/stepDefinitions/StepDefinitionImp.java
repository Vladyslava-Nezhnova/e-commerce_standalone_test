package vladyslavanezhnova.stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import vladyslavanezhnova.pageobjects.*;
import java.io.IOException;

public class StepDefinitionImp extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
    landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password)
    {
        productCatalogue = landingPage.loginApplication(username, password);
    }
    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) throws InterruptedException {
        productCatalogue.addProductToCard(productName);
    }
    @When("^Checkout product (.+) and submit the order$")
    public void checkout_submit_order(String productName){
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("ukraine");
        confirmationPage = checkoutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_ConfirmationPage(String string){
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }
    @Then("{string} message is displayed")
    public void something_message_is_displayed(String mes){
        Assert.assertEquals(mes,landingPage.getErrorMessage());
        driver.close();
    }



}
