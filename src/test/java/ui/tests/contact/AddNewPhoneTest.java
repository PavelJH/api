package ui.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.TestBase;
import ui.page.contact.ContactPage;
import ui.page.contact.PhoneTab;
import ui.page.login.LoginPage;

import java.awt.*;

public class AddNewPhoneTest extends TestBase {
    int id;
    Response response;
    ContactDto contactDto;
    LoginPage loginPage;
    ContactPage contactPage;
    PhoneTab phoneTab;

    @BeforeMethod
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");// создаем через api контакт и далее на него сразу переходим
        loginPage = new LoginPage(driver);
        loginPage.getLogin(EMAIL, PASSWORD);
    }
    @AfterMethod()
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void addNewPhoneTest(){
    contactPage = new ContactPage(driver);
    contactPage.getPage(contactPage.getBASE_PAGE_URL() + id);
    contactPage.click(contactPage.getPhoneTab());
    }
}
