package stepDefinition;

import implementation.Implement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static implementation.Implement.*;

public class StepDefinitions {
    @Given("User opens the General Store App")
    public void userOpensTheGeneralStoreApp() throws IOException {
        capabilities();
    }

    @When("User selects {string} as country")
    public void userSelectsAsCountry(String country) throws IOException {
        Implement.country = country;
        selectCountry();
    }

    @Then("Verifying if the selected country is displayed")
    public void verifyingIfTheSelectedCountryIsDisplayed() {
        verifyCountry();
    }

    @When("User tries to login without entering the name")
    public void userTriesToLoginWithoutEnteringTheName() throws IOException {
        loginWithoutName();
    }

    @Then("Verifying that the user is unable to login")
    public void verifyingThatTheUserIsUnableToLogin() {
        verifyLogin();
    }

    @When("User enters the name {string} to login")
    public void userEntersTheNameToLogin(String name) throws IOException {
        loginWithName(name);
    }

    @Then("Verifying that user has logged in")
    public void verifyingThatUserHasLoggedIn() {
        verifyLogin();
    }

    @When("User adds products to cart")
    public void userAddsProductsToCart() throws IOException {
        addProducts();
    }

    @Then("Verifying that there is one item in cart")
    public void verifyingThatThereIsOneItemInCart() {
        verifyCartCounter();
    }

    @When("User goes to cart")
    public void userGoesToCart() throws IOException {
        goToCart();
    }

    @Then("Verifying if products in cart are correct")
    public void verifyingIfProductsInCartAreCorrect() {
        verifyProducts();
    }

    @When("User searches on Web")
    public void userSearchesOnWeb() {
        webSearch();
    }

    @Then("Verifying if search results are displayed")
    public void verifyingIfSearchResultsAreDisplayed() {
        verifyWeb();
    }
}
