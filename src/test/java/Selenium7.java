import org.testng.annotations.*;

public class Selenium7 {

    @BeforeClass
    public void method1() {
        System.out.println("**********************");
        System.out.println("ISPIS IZ BEFORE CLASS");
        System.out.println("**********************");
    }

    @BeforeMethod
    public void method2() {
        System.out.println("______________________");
        System.out.println("ISPIS IZ BEFORE METHOD");
        System.out.println("______________________");
    }

    @Test(priority = 10)
    public void method3() {
        System.out.println("======================");
        System.out.println("TEST 1");
        System.out.println("======================");
    }

    @Test(priority = 20)
    public void method3a() {
        System.out.println("======================");
        System.out.println("TEST 2");
        System.out.println("======================");
    }

    @Test(priority = 30)
    public void method3b() {
        System.out.println("======================");
        System.out.println("TEST 3");
        System.out.println("======================");
    }

    @Test(priority = 15)
    public void method3c() {
        System.out.println("======================");
        System.out.println("TEST 4");
        System.out.println("======================");
    }

    @AfterMethod
    public void method4() {
        System.out.println("/////////////////////");
        System.out.println("ISPIS IZ AFTER METHOD");
        System.out.println("/////////////////////");
    }

    @AfterClass
    public void method5() {
        System.out.println("---------------------");
        System.out.println("ISPIS IZ AFTER CLASS");
        System.out.println("---------------------");
    }


}