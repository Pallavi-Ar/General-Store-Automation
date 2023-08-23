package locator;

import org.openqa.selenium.By;

public class Locators {
    public static final By countryBtn = By.id("com.androidsample.generalstore:id/spinnerCountry");
    public static final By radioFemale = By.id("com.androidsample.generalstore:id/radioFemale");
    public static final By login_btn = By.id("com.androidsample.generalstore:id/btnLetsShop");
    public static final By name = By.id("com.androidsample.generalstore:id/nameField");
    public static final By products= By.xpath("//*[@text='Products']");
    public static final By selectedCountry = By.id("android:id/text1");
    public static final By add_to_cart_1 = By.xpath("(//*[@text='ADD TO CART'])[2]");
    public static final By jordan = By.xpath("//*[@text='LeBron Soldier 12 ']");
    public static final By counter = By.id("com.androidsample.generalstore:id/counterText");
    public static final By cart_btn = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    public static final By jordan_cart = By.xpath("//*[@text='LeBron Soldier 12 ']");
    public static By webSearch = By.id("com.androidsample.generalstore:id/btnProceed");
    public static By input = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.widget.EditText");
}