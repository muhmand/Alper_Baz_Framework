package come.cydeo.practice.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String email;
    String actualUsername;

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String email, String password) {

        loginPage.login(email,password);
        BrowserUtil.waitFor(2);
        this.email = email;
    }
    @When("user gets username  from user fields")
    public void user_gets_username_from_user_fields() {
         actualUsername = dashBoardPage.accountHolderName.getText();
        System.out.println("actualUsername = " + actualUsername);


    }
    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {
        DB_Util.runQuery("select full_name from users where email='"+email+"'");
        String expectedUserName = DB_Util.getFirstRowFirstColumn();

        // Get data from Database

        //compare
        Assert.assertEquals(expectedUserName,actualUsername);


    }
}
