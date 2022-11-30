package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Step_04 {

     LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String BookN;
    String bookAuthor;
    String bookYear;


    @When("I open book {string}")
    public void i_open_book(String string) {
       dashBoardPage.books.click();
        bookPage.search.sendKeys(string);
        BrowserUtil.waitFor(2);
        bookPage.editBook(string).click();
        BrowserUtil.waitForVisibility(bookPage.bookName,2);


         BookN = bookPage.bookName.getAttribute("value");
         bookAuthor = bookPage.author.getAttribute("value");
         bookYear = bookPage.year.getAttribute("value");


    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
      List<String> actualAllBookInfo = new ArrayList<>(Arrays.asList(BookN,bookAuthor,bookYear));

        DB_Util.runQuery("select name, author,year from books where name='Chordeiles minor'");

        List<String> expectAllBookInfo = DB_Util.getRowDataAsList(1);
       // System.out.println(DB_Util.getRowDataAsList(1));


        Assert.assertEquals(expectAllBookInfo,actualAllBookInfo);

    }

}
