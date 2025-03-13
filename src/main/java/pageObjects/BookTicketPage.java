package pageObjects;

import common.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class BookTicketPage {

    // Các locator cho các phần tử trên trang "Book ticket"
    private final By selectDepartDate = By.xpath("//select[@name='DepartDate']");
    private final By selectDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By selectArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By selectSeatType = By.xpath("//select[@name='SeatType']");
    private final By selectTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
    private final By _lblSuccessMsg = By.xpath("//div[@id='content']/h1");
    private final By _ddlDepartStation = By.xpath("//select[@name='DepartStation']");
    private final By _btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    private final By _ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");
    // Các phương thức để tương tác với phần tử
    public WebElement getLblSuccessMsg() {
        return Constant.WEBDRIVER.findElement(_lblSuccessMsg);
    }

    public WebElement getDdlDepartStation() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ZERO.ofSeconds(30));
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(_ddlDepartStation));
        } catch (TimeoutException e) {
            System.out.println("Đã hết thời gian chờ khi tìm tab '_ddlDepartStation': " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy tab '_ddlDepartStation': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Một lỗi khác đã xảy ra: " + e.getMessage());
        }
        return null;
    }

    public String getSelectedArriveStation() {
        WebElement arriveStationDropdown = getDdlDepartStation();
        Select select = new Select(arriveStationDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void selectDepartDate(String date) {
        Constant.WEBDRIVER.findElement(selectDepartDate).sendKeys(date);
    }

    public void selectDepartFrom(String station) {
        Constant.WEBDRIVER.findElement(selectDepartFrom).sendKeys(station);
    }

    public void selectArriveAt(String station) {
        Constant.WEBDRIVER.findElement(selectArriveAt).sendKeys(station);
    }

    public void selectSeatType(String seatType) {
        Constant.WEBDRIVER.findElement(selectSeatType).sendKeys(seatType);
    }

    public void selectTicketAmount(String amount) {
        Constant.WEBDRIVER.findElement(selectTicketAmount).sendKeys(amount);
    }

    public void clickBookTicketButton() {

        Constant.WEBDRIVER.findElement(btnBookTicket).click();
    }

    public WebElement getDdlTicketAmount() {

        return Constant.WEBDRIVER.findElement(_ddlTicketAmount);

    }

    public String getSuccessMessage() {
        return getLblSuccessMsg().getText();
    }

    public TicketInfor getTicketInfo() {
        WebElement tableRow = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='DivTable']//tr[@class='OddRow']"));
        String departStation = tableRow.findElement(By.xpath("./td[1]")).getText();
        String arriveStation = tableRow.findElement(By.xpath("./td[2]")).getText();
        String seatType = tableRow.findElement(By.xpath("./td[3]")).getText();
        String departDate = tableRow.findElement(By.xpath("./td[4]")).getText();
        int amount = Integer.parseInt(tableRow.findElement(By.xpath("./td[7]")).getText());
        return new TicketInfor(departStation, arriveStation, seatType, departDate, amount);
    }

    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }

    public String getSelectedDepartStation() {
        WebElement departStationDropdown = getDdlDepartStation();
        Select select = new Select(departStationDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public MyTicketPage gotoMyTicketsPage() {
        WebElement myTicketsLink = Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/Page/ManageTicket.cshtml']"));
        myTicketsLink.click();
        return new MyTicketPage();
    }

    public void selectTicketAmount(int ticketAmount) {
        WebElement amountDropdown = getDdlTicketAmount();
        amountDropdown.click();
        for (WebElement option : amountDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(String.valueOf(ticketAmount))) {
                option.click();
                break;
            }
        }
    }

    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
        selectDepartDate(departDate);
        selectDepartFrom(departFrom);
        selectArriveAt(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        WebElement bookTicketLink = getBtnBookTicket();
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketLink);
        bookTicketLink.click();
    }
}