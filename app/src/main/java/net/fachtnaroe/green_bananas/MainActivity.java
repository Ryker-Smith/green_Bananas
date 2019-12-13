package net.fachtnaroe.green_bananas;

import android.content.Intent;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Notifier;

public class MainActivity extends Form implements HandlesEventDispatching {


    private Label title, UserL, PasswL, debugLabel, debugLabel2;
    private TextBox username;
    private PasswordTextBox password;
    private CheckBox buyer, seller;
    private Button login;
    private VerticalScrollArrangement vsaTheScreen;
    private HorizontalArrangement Harr1, Harr2, Harr3;
    private String weblogin = "https://fachtnaroe.net/bananas?cmd=LOGIN&user=",
            webLogin2 = "&pass=";
    private Web webLoginConnection;
    private Notifier notifier;
    private boolean ResponseContent;
    private String weh;
    private static String Suser="",
            SessionId="",
            pID="";

    protected void $define() {

        webLoginConnection = new Web(this);
        this.BackgroundColor(COLOR_ORANGE);
        notifier = new Notifier(this);
        vsaTheScreen = new VerticalScrollArrangement(this);
        vsaTheScreen.Width(Component.LENGTH_FILL_PARENT);
        vsaTheScreen.Height(Component.LENGTH_FILL_PARENT);
        vsaTheScreen.BackgroundColor(Component.COLOR_ORANGE);
        vsaTheScreen.Image("FDS_PossibleLogo_03.png");
        notifier.BackgroundColor(Component.COLOR_RED);
        notifier.TextColor(Component.COLOR_WHITE);
        title = new Label(vsaTheScreen);
        title.Width(Component.LENGTH_FILL_PARENT);
        title.Text("Food Delivery Service");
        title.FontSize(50);
        title.FontBold(true);
        title.TextAlignment(Component.ALIGNMENT_CENTER);

        Harr1 = new HorizontalArrangement(vsaTheScreen);
        Harr1.Width(Component.LENGTH_FILL_PARENT);
        Harr1.HeightPercent(15);
        UserL = new Label(Harr1);
        UserL.Text("Username");
        UserL.WidthPercent(20);
        username = new TextBox(Harr1);
        username.Width(Component.LENGTH_FILL_PARENT);
        username.Text("greenshop");

        Harr2 = new HorizontalArrangement(vsaTheScreen);
        Harr2.Width(Component.LENGTH_FILL_PARENT);
        Harr2.HeightPercent(15);
        PasswL = new Label(Harr2);
        PasswL.WidthPercent(20);
        PasswL.Text("Password");
        password = new PasswordTextBox(Harr2);
        password.Width(Component.LENGTH_FILL_PARENT);
        password.Text("tcfetcfe");

        debugLabel = new Label(vsaTheScreen);
        debugLabel.Width(Component.LENGTH_FILL_PARENT);
        debugLabel.HeightPercent(10);
        debugLabel.Text();

        debugLabel2 = new Label(vsaTheScreen);
        debugLabel2.Width(Component.LENGTH_FILL_PARENT);
        debugLabel2.HeightPercent(10);

        Harr3 = new HorizontalArrangement(vsaTheScreen);
        Harr3.Width(Component.LENGTH_FILL_PARENT);
        Harr3.HeightPercent(10);
        Harr3.AlignHorizontal(Component.ALIGNMENT_CENTER);
        buyer = new CheckBox(Harr3);
        buyer.Text("Buyer");
        buyer.Width(Component.LENGTH_FILL_PARENT);
        buyer.FontBold(true);
        buyer.Checked(true);
        seller = new CheckBox(Harr3);
        seller.Text("Seller");
        seller.FontBold(true);
        seller.Width(Component.LENGTH_FILL_PARENT);
        seller.Checked(false);

        login = new Button(vsaTheScreen);
        login.Width(Component.LENGTH_FILL_PARENT);
        login.HeightPercent(15);
        login.Text("Log In");
        login.FontSize(25);
        login.TextAlignment(Component.ALIGNMENT_CENTER);
        login.BackgroundColor(Component.COLOR_NONE);
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
        EventDispatcher.registerEventForDelegation(this, formName, "Changed");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params)
    {
        if (eventName.equals("Click")) {
            if (component.equals(login)) {
                loginBtnClick();
                return true;
            }
        }
        else if (eventName.equals("Changed")) {
            if (component.equals(buyer)) {
                seller.Checked(!buyer.Checked());
                return true;
            }
            else if (component.equals(seller) ) {
                buyer.Checked(!seller.Checked());
                return true;
            }
        }
        else if(eventName.equals("GotText")){
            sortJsonDeet(params);
            loginResult(params);
            return true;
        }
        return false;
    }

    public void loginBtnClick() {
        webLoginConnection.Url(weblogin + username.Text() + webLogin2 + password.Text());
        webLoginConnection.Get();

        if (buyer.Checked()) {
            notifier.LogInfo("OK");
            openBuyer();
        }

        if (seller.Checked()) {
            notifier.LogInfo("OK");
            openSeller();
        }
    }

    public void openBuyer() {
        //switchFormWithStartValue("MainActivity", null);
        //switchForm("customerScreen");
        Intent i = new Intent(getApplicationContext(),customerScreen.class);
        startActivity(i);
    }

    public void openSeller() {
        //switchFormWithStartValue("MainActivity", null);
        //switchForm("sellerScreen");
        Intent i = new Intent(getApplicationContext(),sellerScreen.class);
        startActivity(i);
    }

    public void loginResult(Object[] params) {
        debugLabel.Text((String) params[3]);
        weh = (String) params[3];
        ResponseContent = weh.contains("OK");
        if (ResponseContent) {
            notifier.ShowAlert("OK");
        } else {
            notifier.ShowAlert("Error");
        }

    }

    public void checkChange1() {
        seller.Checked(false);

    }

    public void checkChange2() {
        buyer.Checked(false);

    }

    public void sortJsonDeet(Object[] params){
        String jsonString = (String)params[3];
        int sessionIDFirstChar =(jsonString.indexOf("sessionID"))+12;
        int pID_FirstChar = (jsonString.indexOf("pID"))+8;
        SessionId=jsonString.substring(sessionIDFirstChar,sessionIDFirstChar+8);
        pID=jsonString.substring(pID_FirstChar,pID_FirstChar+2);
    }
    public static String getUsername(){
        return Suser;
    }
    public static String getSessionID(){
        return SessionId;
    }
    public static String getPID(){
        return pID;
    }
}