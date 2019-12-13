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

        notifier = new Notifier(this);
        notifier.BackgroundColor(Component.COLOR_RED);
        notifier.TextColor(Component.COLOR_WHITE);

        vsaTheScreen = new VerticalScrollArrangement(this);
        vsaTheScreen.WidthPercent(100);
        vsaTheScreen.HeightPercent(100);
        vsaTheScreen.Image("FDS_PossibleLogo_04.png");

        title = new Label(vsaTheScreen);
        title.WidthPercent(100);
        title.HeightPercent(20);
        title.Text("Food Delivery Service");
        title.FontSize(36);
        title.TextAlignment(Component.ALIGNMENT_CENTER);

        Harr1 = new HorizontalArrangement(vsaTheScreen);
        Harr1.WidthPercent(100);
        Harr1.HeightPercent(10);
        UserL = new Label(Harr1);
        UserL.Text("Username");
        UserL.WidthPercent(LENGTH_FILL_PARENT);
        username = new TextBox(Harr1);
        username.WidthPercent(70);
        username.Text("greenshop");

        Harr2 = new HorizontalArrangement(vsaTheScreen);
        Harr2.WidthPercent(100);
        Harr2.HeightPercent(10);
        PasswL = new Label(Harr2);
        PasswL.WidthPercent(LENGTH_FILL_PARENT);
        PasswL.Text("Password");
        password = new PasswordTextBox(Harr2);
        password.WidthPercent(70);
        password.Text("tcfetcfe");

        debugLabel = new Label(vsaTheScreen);
        debugLabel.WidthPercent(100);
        debugLabel.HeightPercent(10);
        debugLabel.Text();

        debugLabel2 = new Label(vsaTheScreen);
        debugLabel2.WidthPercent(100);
        debugLabel2.HeightPercent(10);
        debugLabel2.Text();

        Harr3 = new HorizontalArrangement(vsaTheScreen);
        Harr3.WidthPercent(100);
        Harr3.HeightPercent(10);
        buyer = new CheckBox(Harr3);
        buyer.Text("Buyer");
        buyer.WidthPercent(LENGTH_FILL_PARENT);
        seller = new CheckBox(Harr3);
        seller.Text("Seller");
        seller.WidthPercent(LENGTH_FILL_PARENT);

        login = new Button(vsaTheScreen);
        login.WidthPercent(100);
        login.HeightPercent(20);
        login.Text("Login");
        login.FontSize(25);
        login.TextAlignment(Component.ALIGNMENT_CENTER);
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
        EventDispatcher.registerEventForDelegation(this, "ChangedEvent", "Changed");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params)
    {

        if (eventName.equals("Click")) {
            if (component.equals(login)) {
                loginBtnClick();
                return true;
            }
        }
        if (component.equals(buyer) && eventName.equals("Changed")) {
            checkChange1();
            return true;
        }
        if (component.equals(seller) && eventName.equals("Changed")) {
            checkChange2();
            return true;
        }
        if(eventName.equals("GotText")){
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