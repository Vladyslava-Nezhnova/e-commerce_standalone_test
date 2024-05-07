package tests;

import TestComponents.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import vladyslavanezhnova.pageobjects.*;


public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test
    public void submitOrder() throws InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication("vldnezhnova@gmail.com", "Testing2024");


        productCatalogue.addProductToCard(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("ukraine");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    @Test (dependsOnMethods = {"submitOrder"})
            public void orderHistoryTest()
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication("vldnezhnova@gmail.com", "Testing2024");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    }

