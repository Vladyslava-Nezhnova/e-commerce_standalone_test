package tests;

import TestComponents.BaseTest;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import vladyslavanezhnova.pageobjects.*;

import java.util.HashMap;



public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));

       //List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCard(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
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
    @DataProvider
    public Object[][] getData(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "vldnezhnova@gmail.com");
        map.put("password", "Testing2024");
        map.put("product", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "anshika@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("product", "ADIDAS ORIGINAL");
        return new Object[][] {{map }, {map1}};
    }

    }

