package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import vladyslavanezhnova.pageobjects.CartPage;
import vladyslavanezhnova.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation(){

        landingPage.loginApplication("vldnezhnova@gmail.com", "apple");
        Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());

    }
    @Test
    public void ProductErrorValidation() throws InterruptedException{

    String productName = "ZARA COAT 3";
    ProductCatalogue productCatalogue = landingPage.loginApplication("vldnezhnova@gmail.com", "Testing2024");
        productCatalogue.addProductToCard(productName);
    CartPage cartPage = productCatalogue.goToCartPage();
    Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 333");
        Assert.assertFalse(match);

    }
}
