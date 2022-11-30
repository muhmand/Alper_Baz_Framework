package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Step_01 {

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        System.out.println("Database Connection is establish through hooks class");
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DB_Util.runQuery("select id from users");
    }

    @Then("verify all users has a unique ID")
    public void verify_all_users_has_a_unique_id() {
        List<String> id  = DB_Util.getColumnDataAsList(1);
        Set<String> uniqueId = new LinkedHashSet<>(id);

        Assert.assertEquals(id.size(), uniqueId.size());
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");


    }
    @Then("verify the below columns are listed in the result")
    public void verify_the_below_columns_are_listed_in_the_result(List<String> expectedAllColumnName) {
        List<String> actualAllColumnName = DB_Util.getAllColumnNamesAsList();

        //Assert.assertEquals(expectedAllColumnName,actualAllColumnName);
        for (int i = 0; i < expectedAllColumnName.size(); i++) {
            Assert.assertEquals(expectedAllColumnName.get(i),actualAllColumnName.get(i));
        }

    }

}
