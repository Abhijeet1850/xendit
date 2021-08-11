package com.test.xendit.PageActions;

import com.test.xendit.TestBase.TestBase;
import com.test.xendit.helper.configReader.PropertyReader;
import com.test.xendit.helper.logger.LoggerHelper;
import com.test.xendit.helper.resource.ResourceHelper;
import com.test.xendit.helper.util.CalculatorMatrix;
import com.test.xendit.helper.util.TextFromImageOcrLib;
import com.test.xendit.helper.wait.WaitHelper;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CalculatorMainPage {

    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(CalculatorMainPage.class);
    TestBase tb = new TestBase();
    WaitHelper wait;
    PropertyReader reader = new PropertyReader();
    Actions action;
    TextFromImageOcrLib ocrLib;

    private String actualResult;

    public static int xmid;
    public static int ymid;

    public static final List<String> CALC_KEYS = Arrays.asList("MC", "MR", "M+", "M-", "CE", "%", "*", "-", "+", "+/-", ".", "/");

    @FindBy(id = "fullframe")
    WebElement calcFrame;

    WebElement calculator;


    public CalculatorMainPage(WebDriver driver) {
        this.driver = driver;
        init();
        PageFactory.initElements(driver, this);
        wait = new WaitHelper(driver);
        action = new Actions(driver);
        ocrLib = new TextFromImageOcrLib();
    }

    public void init() {
        driver.get("https://www.online-calculator.com/full-screen-calculator/");
        driver.manage().window().maximize();
    }

    public String getActualResult() {
        return actualResult;
    }

    public void fetchCalculatorDetails() {
        log.info("Fetch Calculator size details");
        System.out.println("Height of Chrome Window (y) : " + driver.manage().window().getSize().getHeight() + "   width of Chrome Window (x) : " + driver.manage().window().getSize().getWidth());
        wait.waitForFrameBeforeSwitching(calcFrame, reader.getExplicitWait(), reader.getPollingTime());
        calculator = wait.waitForElementToBeClickable(By.id("canvas"), reader.getExplicitWait(), reader.getPollingTime());
        xmid = (int) calculator.getRect().getWidth() / 2;
        ymid = (int) calculator.getRect().getHeight() / 2;
    }


    public void enterNumberOrOperator(String key) {
        if (CALC_KEYS.contains(key))
            performCalculatorKeyclick(key);
        else if (key.equals("=") || key.equals("sqrt") || key.equals("1/x")) {
            performCalculatorKeyclick(key);
            wait.waitForMillis(2000);
            String resultImage = tb.captureElement(calculator, "Result");
            extractActualResult(resultImage);
        } else {
            char[] arr = key.toCharArray();
            int i = 0;
            while (i < arr.length) {
                if (arr[i] == '-')
                    performCalculatorKeyclick("+/-");
                else
                    performCalculatorKeyclick(String.valueOf(arr[i]));
                i++;
            }
        }
        wait.waitForMillis(500);
    }

    public void extractActualResult(String resultImage) {
        String output = ocrLib.getTextFromImage(resultImage);
        System.out.println(output);
        String[] result = output.split("\\R");
        actualResult = result[0].trim();
        System.out.println(actualResult + "length of string is : --> " + actualResult.length());
    }

    public void performCalculatorKeyclick(String calKey) {
        Point offset = getOffset(calKey);
        action.moveToElement(calculator, -xmid, -ymid).moveByOffset(offset.getX(), offset.getY()).click().build().perform();
    }


    public Point getOffset(String key) {
        Point position = CalculatorMatrix.getKeyPosition(key);
        System.out.println(position.getX() + " , " + position.getY());
        System.out.println(key);
        return getOffsetPosition(position);
    }

    public Point getOffsetPosition(Point position) {
        int yrecord = calculator.getRect().getHeight() / 7;
        int xrecord = calculator.getRect().getWidth() / 5;
        int xOffset = xrecord * position.getX() + (int) (xrecord / 2);
        int yOffset = yrecord * position.getY() + (int) (yrecord * 0.4);
        System.out.println("Key --> : " + xOffset + " , " + yOffset);
        return new Point(xOffset, yOffset);
    }

}
