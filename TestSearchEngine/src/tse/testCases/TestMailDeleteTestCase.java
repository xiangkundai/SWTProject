package tse.testCases;

import java.util.HashMap;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

import tse.tasks.TestMailTasks;
import tse.utilities.SeleniumUtils;
import tse.utilities.XMLParser;

public class TestMailDeleteTestCase extends SeleneseTestCase {

    private HashMap<String, Object> paraMap;

    private TestMailTasks           tgTasks;

    private SeleniumUtils           utils;

    RemoteControlConfiguration      rcc = new RemoteControlConfiguration();

    SeleniumServer                  SELENIUM_SERVER;

    @BeforeClass
    public void setup() {
        selenium = new DefaultSelenium("localhost", 4444, "*firefox",
        		"https://mail.google.com/");
        	//                "https://accounts.google.com/");
        System.out.println("Starting selenium.");
        selenium.start();
        utils = new SeleniumUtils(selenium);
        tgTasks = new TestMailTasks(utils);
    }

    @Parameters( { "mail_test_para_1" }) //still using login para
    @Test
    public void testMailDelete(String paraFile) throws InterruptedException {
        paraMap = (HashMap<String, Object>) XMLParser.getInstance().parserXml(paraFile);
        System.out.println("the paraMap is" + paraMap);

        tgTasks.openSite();
        tgTasks.typeLoginTxtField(paraMap);
        tgTasks.clickLoginBtn();
        //tgTasks.verifyResult(paraMap);
        Thread.sleep(3000);
        //
        tgTasks.clickCheckBox();
        tgTasks.clickDelete();
        //
        // String totalEmailAfterDelete = selenium.getText("//span[@class='Dj']/b[3]");
        // System.out.println(totalEmailBeforeDelete + " Emails at the beginning, now only " + 
        //                    totalEmailAfterDelete + " Emails !");
        // assertEquals(1, Integer.parseInt(totalEmailBeforeDelete) - Integer.parseInt(totalEmailAfterDelete));
    
        Thread.sleep(3000);
    }
    
    @AfterClass
    public void stop(){
        if(selenium != null){
            selenium.stop();
        }
    }
}
