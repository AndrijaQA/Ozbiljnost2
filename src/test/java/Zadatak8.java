import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak8 {
    public static void main(String[] args) {

        //https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
        //Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        // Posto proveravam da li je nesto dodato u korpu, moram prvo da proverim da li je korpa prazna
        // Prvo proveravam da li je broj iznad korpe zaista 0
        WebElement cartAmountBeforeAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountBeforeAdding.getText(), "0");

        // Zatim idem na korpu da se uverim da je zaista prazna, da nije samo upisan broj 0, a da u korpi ima nesto
        WebElement cart = driver.findElement(By.id("nav-cart"));
        cart.click();

        // Ova klasa je prisutna samo kada je korpa prazna, proverio sam rucno da li je element prisutan
        // kada ima nesto u korpi i ne element ne postoji kada korpa nije prazna
        WebElement emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());

        driver.navigate().back();

        // Pre nego sto dodam zelim da izvucem naziv knjige kako bih se posle uverio da je zapravo bas ta knjiga
        // dodata u korpu
        WebElement book = driver.findElement(By.id("productTitle"));
        String bookTitleToBeAdded = book.getText();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        WebElement successfulMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(successfulMessage.isDisplayed());

        // Sada proveravam da li se broj na korpi promenio sa 0 na 1
        // Moram ponovo da definisem isti lokator jer sam odlazio na drugu stranicu i dobicu gresku stale element
        // Ako ne definisem ponovo element (mogu i da inicijalizujem ali zbog naziva biram novi element)
        WebElement cartAmountAfterAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountAfterAdding.getText(), "1");

        cart = driver.findElement(By.id("nav-cart"));
        cart.click();

        //Uzimam naslov knjige u korpi
        WebElement bookInCart = driver.findElement(By.className("a-truncate-cut"));
        String bookTitleInCart = bookInCart.getText();

        //Uporedjujem naslov knjige u korpi sa naslovom knjige pre nego sto sam je dodao u korpu
        Assert.assertEquals(bookTitleToBeAdded, bookTitleInCart);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        WebElement cartAmountAfterRemoving = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountAfterRemoving.getText(), "0");

        emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());


    }
}