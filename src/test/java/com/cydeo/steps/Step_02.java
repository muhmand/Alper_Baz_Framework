package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Step_02 {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBook;
    @Given("user login as a librarian")
    public void user_login_as_a_librarian() {
       loginPage.login("librarian43@library","9Wa02cAu");

    }

    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
         actualBorrowedBook = dashBoardPage.borrowedBooksNumber.getText();

    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        String expectedBorrowedBook = DB_Util.getCellValue(1, 1);
        Assert.assertEquals((expectedBorrowedBook),actualBorrowedBook);


    }



}
