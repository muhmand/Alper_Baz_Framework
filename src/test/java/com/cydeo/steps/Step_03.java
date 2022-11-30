package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import java.util.List;

public class Step_03 {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    List<String> actualBookCategory;

    @Given("I login as a librarian")
    public void i_login_as_a_librarian() {
    loginPage.login(ConfigurationReader.getProperty("librarian_username"),ConfigurationReader.getProperty("password"));

    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String bookPages) {
        bookPage.navigateModule(bookPages);

    }
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        actualBookCategory = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualBookCategory.remove(0);

    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {

        String query = "select * from book_categories";
        DB_Util.runQuery(query);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        List<String> expectedBookCategory = DB_Util.getColumnDataAsList(3);

        Assert.assertEquals(expectedBookCategory,actualBookCategory);

    }








}
