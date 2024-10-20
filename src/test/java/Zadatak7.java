import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Zadatak7 {
    public static void main(String[] args) throws InterruptedException {


// Zadatak 7
// Ulogovati se na wordpress aplikaciju koristeci sopstvene kredencijale
// Fokus treba da bude na cekanjima i lokatorima

        // Sleepers - ceka toliko vremena koliko zadamo dok program ne nastavi sa radom
        // Implicit wait - ceka odredjeno vreme da se pojavi element
        // odnosno, ceka se odredjeno vreme dok se ne dobije greska "NoSuchElementException"
        // Implicit wait sluzi samo za ovu vrstu greske
        // Explicit wait - ceka dok se ne ispuni odredjeni uslov

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Za implicit wait je potrebno samo jednom na pocetku da napisemo ovu liniju koda
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // na kraju dodajemo vreme koliko ce program najvise da ceka da se element pojavi dok nam ne vrati gresku

        // Da bismo koristili Explicit wait moramo prvo da napravimo objekat za cekanje
        // Tom objektu takodje dodeljujemo vreme koliko ce najduze da se ceka da se uslov ispuni
        // Ako se uslov ne ispuni za dato vreme onda ce program da vrati gresku
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.navigate().to("https://wordpress.com/");

        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        loginButton.click();

        // Sleep ce uvek prvi put davati gresku jer nije dodat Exception na metodu
        // Potrebno je samo da kliknete na sleep i zatim na 'Add exception'
        // Klik na 'sleep' -> Alt + Enter -> klik na 'Add exception'
        Thread.sleep(3000);

        String username = "dragoljubqa";

        WebElement usernameField = driver.findElement(By.id("usernameOrEmail"));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Imam 3 elementa za dugme 'Continue'
        //WebElement continueButton = driver.findElement(By.cssSelector(".button.form-button.is-primary"));
        //WebElement continueButton = driver.findElement(By.className("login__form-action"));
        // Odabrao sam preko ovog CSS Selektora jer tu imam vrednost 'submit' koja mi deluje stabilnije od prethodna dva lokatora
        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));
        continueButton.click();

        // Pre nego sto sam ubacio wait za password polje morao sam da imam sleep dok polje ne postane 'interactable'
        //Thread.sleep(3000);

        WebElement passwordField = driver.findElement(By.id("password"));
        // Explicit wait koristimo kad zelimo da sacekamo da se odredjen uslov ispuni
        // Ti uslovi mogu biti da element postane vidljiv, klikabilan ili da se URL promeni na odredjen URL
        // U ovom slucaju cekamo da password polje postane vidljivo
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys("Swordfish123!@#");
        continueButton.click();

        //Thread.sleep(3000);

        // U slucaju kada imamo cekanje da se URL promeni, ovo moze takodje biti deo testa
        // i asertacija linka moze biti suvisna
        // jer ako se URL ne promeni na ovaj zadati, onda ce test pasti na tom koraku
        wait.until(ExpectedConditions.urlToBe("https://wordpress.com/home/dragoljubqa.wordpress.com"));
        // Ali mozemo dodati posebno asertacije kako bismo kasnije lakse proverili gde sve imamo asertacije i sta sve proveravamo
        String expectedURL = "https://wordpress.com/home/dragoljubqa.wordpress.com";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        // S obzirom da nakon logovanja trazim da li je username prisutan na elementu, dobijam string koji ne sadrzi samo username
        WebElement profileName = driver.findElement(By.className("masterbar__item-howdy-howdy"));
        String profileNameText = profileName.getText();
        // Zato mogu da biram da li cu da napravim boolean koji ce proveriti da li to polje sadrzim username koriscen za logovanje
        boolean containsUsername = profileNameText.contains(username);

        Assert.assertTrue(containsUsername);
        // Ili da upisem taj dodatni deo stringa u proveru
        Assert.assertEquals(profileNameText, "Howdy, " + username);


    }
}