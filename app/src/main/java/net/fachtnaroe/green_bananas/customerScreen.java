package net.fachtnaroe.green_bananas;

import android.graphics.Color;
import android.util.Log;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListView;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.util.YailList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class customerScreen extends Form implements HandlesEventDispatching {
    private Label FoodLbl, CreditLbl, AvaFood, UserLbl, pIDLbl, ThingOrder;
    private HorizontalArrangement HarrLbl, HarrBuyBtn, HarrList, HarrUser;
    private VerticalArrangement Screen1;
    private Button BuyBtn;
    private Web Web, Web2;
    private ListView ListView, ListView2;
    private String BaseURL = "https://fachtnaroe.net/bananas?",
            pID=MainActivity.getPID(),
            username=MainActivity.getUsername();
    private Notifier notifier;


    protected void $define() {
        Screen1 = new VerticalArrangement(this);
        this.BackgroundColor(COLOR_ORANGE);
        Screen1.Width(Component.LENGTH_FILL_PARENT);
        Screen1.Height(Component.LENGTH_FILL_PARENT);
        Screen1.BackgroundColor(Component.COLOR_ORANGE);
        Screen1.Image("FDS_PossibleLogo_03.png");

        HarrLbl = new HorizontalArrangement(Screen1);
        HarrLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl = new Label(HarrLbl);
        FoodLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl.Text("Food Delivery Service");
        FoodLbl.FontSize(20);
        FoodLbl.TextAlignment(Component.ALIGNMENT_CENTER);
        FoodLbl.BackgroundColor(Color.parseColor("#005200"));
        FoodLbl.TextColor(Color.WHITE);

        HarrUser = new HorizontalArrangement(Screen1);
        HarrUser.WidthPercent(100);
        HarrUser.HeightPercent(5);
        HarrUser.BackgroundColor(00000000);
        UserLbl = new Label(HarrUser);
        UserLbl.WidthPercent(50);
        UserLbl.TextColor(COLOR_BLACK);
        UserLbl.Text("Username "+ username);
        UserLbl.FontSize(14);
        UserLbl.BackgroundColor(00000000);

        pIDLbl = new Label(HarrUser);
        pIDLbl.Text("pID "+pID);
        pIDLbl.FontSize(14);
        pIDLbl.TextColor(COLOR_BLACK);
        pIDLbl.TextAlignment(ALIGNMENT_OPPOSITE);
        pIDLbl.WidthPercent(50);
        pIDLbl.BackgroundColor(00000000);

        AvaFood = new Label(Screen1);
        AvaFood.WidthPercent(100);
        AvaFood.HeightPercent(3);
        AvaFood.Text("Things Available to order");
        AvaFood.TextAlignment(ALIGNMENT_NORMAL);
        AvaFood.BackgroundColor((Color.parseColor("#005200")));
        AvaFood.TextColor(Color.WHITE);
        AvaFood.FontSize(12);

        HarrList = new HorizontalArrangement(Screen1);
        HarrList.WidthPercent(100);
        HarrList.HeightPercent(50);
        ListView = new ListView(HarrList);
        ListView.HeightPercent(100);
        ListView.Width(Component.LENGTH_FILL_PARENT);
        ListView.BackgroundColor(00000000);
        ListView.TextColor(COLOR_BLACK);
        ListView.SelectionColor(Color.parseColor("#009F00"));

        Web = new Web(this);
        Web.Url(BaseURL + "sessionID=a1b2c3d4&entity=thing&method=GET");
        Web.Get();
        Web2 = new Web(this);
        Web2.Url(BaseURL + "sessionID=a1b2c3d4&entity=prettyorders&method=GET");
        Web2.Get();

        HarrBuyBtn = new HorizontalArrangement(Screen1);
        HarrBuyBtn.Width(LENGTH_FILL_PARENT);
        HarrBuyBtn.HeightPercent(10);
        CreditLbl = new Label(HarrBuyBtn);
        CreditLbl.WidthPercent(50);
        CreditLbl.Text("Credit €");
        CreditLbl.FontSize(20);
        CreditLbl.TextAlignment(ALIGNMENT_NORMAL);
        CreditLbl.BackgroundColor(00000000);

        BuyBtn = new Button(HarrBuyBtn);
        BuyBtn.WidthPercent(50);
        BuyBtn.Text("Buy");
        BuyBtn.FontSize(14);
        BuyBtn.TextAlignment(ALIGNMENT_CENTER);
        BuyBtn.BackgroundColor(00000000);

        ThingOrder = new Label(Screen1);
        ThingOrder.WidthPercent(100);
        ThingOrder.HeightPercent(3);
        ThingOrder.Text("Things I've ordered");
        ThingOrder.FontSize(12);
        ThingOrder.TextAlignment(ALIGNMENT_NORMAL);
        ThingOrder.BackgroundColor((Color.parseColor("#005200")));
        ThingOrder.TextColor(Color.WHITE);

        ListView2 = new ListView(Screen1);
        ListView2.WidthPercent(100);
        ListView2.HeightPercent(100);
        ListView2.BackgroundColor(COLOR_WHITE);
        ListView2.TextColor(COLOR_BLACK);
        ListView2.SelectionColor(Color.parseColor("#009F00"));

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
//        EventDispatcher.registerEventForDelegation(this, formName,eventName "Initilize");
        EventDispatcher.registerEventForDelegation(this, "GotTextEvent", "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        if (eventName.equals("GotText")){
            if (component.equals(Web)) {
                Log.w("Check L8r",(String)params[3]);
                //calling the procedure For the ListView containing the Items that are available to buy
                jsonSortAndListViewForBuyerScreen(params[1].toString(), (String) params[3],"thing", "null");
                //calling the procedure For the ListView containing the Items that the buyer has ordered
                return true;
           }
//            if (component.equals(Web2)) {
//                //calling the procedure For the ListView containing the Items that the buyer has ordered
//                jsonSortAndListViewForBuyerScreen(params[1].toString(), (String) params[3],"prettyorders", "buyerID");
//                //JsonSortThingsListView((String) params[3]);
//                //ListView.ElementsFromString((String)params[3]);
//                return true;
//                }
        }
        if (eventName.equals("Click")) {
            if (component.equals(BuyBtn)) {
                BuyBtn();
                return true;

            }
        }
        return false;

    }
    public void BuyBtn() {
        BuyBtn.Text("Pressed");
    }
    public void jsonSortAndListViewForBuyerScreen(String status, String textOfResponse, String tableName, String fieldName) {
        List<String> ListViewItemArray;
        if (status.equals("200")) try {
            ListViewItemArray = new ArrayList<String>();
            // See:  https://stackoverflow.com/questions/5015844/parsing-json-object-in-java
            JSONObject parser = new JSONObject(textOfResponse);
            if (!parser.getString(tableName).equals("")) {
                JSONArray jsonIsMySon = parser.getJSONArray(tableName);
                for (int i = 0; i < jsonIsMySon.length(); i++) {
                    String oneEntryInTheListView = "";
                    //add data from table to the sting above by getting the field name you want from the brief ( example where field name is "sellerID": oneEntryInTheListView = jsonIsMySon.getJSONObject(i).getString("sellerID"); )
                    //formats entries the ListView containing the items in thing table
                    if (tableName.equals("thing") && fieldName.equals("null")){
                        oneEntryInTheListView = "[" + jsonIsMySon.getJSONObject(i).getString("tID")
                                + " : " + jsonIsMySon.getJSONObject(i).getString("tSoldBy")
                                + "] " + jsonIsMySon.getJSONObject(i).getString("tName")
                                + " (" + jsonIsMySon.getJSONObject(i).getString("tDescription")
                                + ") €" + jsonIsMySon.getJSONObject(i).getString("tPrice");
                        ListViewItemArray.add(oneEntryInTheListView);
                    }
                    //formats entries the ListView containing the orders buyer has placed
                    else if ((tableName.equals("prettyorders") && fieldName.equals("buyerID")) && (Integer.valueOf(jsonIsMySon.getJSONObject(i).getString(fieldName)).equals( Integer.valueOf(pID)))) {
                        oneEntryInTheListView = "[" + jsonIsMySon.getJSONObject(i).getString("oID")
                                + "] " + jsonIsMySon.getJSONObject(i).getString("tName")
                                + " from " + jsonIsMySon.getJSONObject(i).getString("seller")
                                + " [ tID: " + jsonIsMySon.getJSONObject(i).getString("tID") + " ]";
                        ListViewItemArray.add(oneEntryInTheListView);
                    }
                }
                YailList tempData = YailList.makeList(ListViewItemArray);
                if (tableName.equals("prettyorders") && fieldName.equals("buyerID")) {
                    ListView2.Elements(tempData);
                }
                if (tableName.equals("thing") && fieldName.equals("null")) {
                    ListView.Elements(tempData);
                }
            }
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
            notifier.ShowMessageDialog("Error 3.353; JSON Exception (check password) ", "Information", "OK");
        }
        else {
            notifier.ShowMessageDialog("Error 3.356; Problem connecting with server", "Information", "OK");
        }
    }
}