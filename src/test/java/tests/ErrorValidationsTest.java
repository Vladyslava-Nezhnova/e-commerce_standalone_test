package tests;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import vladyslavanezhnova.pageobjects.CartPage;
import vladyslavanezhnova.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";
        landingPage.loginApplication("vldnezhnova@gmail.com", "apple");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }
    @Test
    public void ProductErrorValidation() throws IOException,InterruptedException{

    String productName = "ZARA COAT 3";
    ProductCatalogue productCatalogue = landingPage.loginApplication("vldnezhnova@gmail.com", "Testing2024");
    List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCard(productName);
    CartPage cartPage = productCatalogue.goToCartPage();
    Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 333");
        Assert.assertFalse(match);

    }
}
