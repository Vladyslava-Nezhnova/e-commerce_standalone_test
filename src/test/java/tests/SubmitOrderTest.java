package tests;

import TestComponents.BaseTest;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import vladyslavanezhnova.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


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
    public Object[][] getData() throws IOException {

        List<HashMap<String, String >> data = getJsonDataToMap(System.getProperty("user.dir")+
                "//src//test//java//vladyslavanezhnova//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};

        /* HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "vldnezhnova@gmail.com");
        map.put("password", "Testing2024");
        map.put("product", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "anshika@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("product", "ADIDAS ORIGINAL"); */
    }

    }

