package net.fachtnaroe.green_bananas;

import android.graphics.Color;
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


public class Order extends Form implements HandlesEventDispatching {
    private Button BTN_BuyItem;
    private VerticalArrangement VArr;
    private Label LblFoodDeliveryService, LblUsername, LblTextbox, LblpID, LbleTextboxpID, LblListofFood, LblofCredit, CreditCurrency, LblofOrderedItems;
    private HorizontalArrangement HorizonUserInformation, HorizonCreditBuyBtn, HorizonCredit, HorizonBuyBtn;
    private ListView ListofFood, OrderedList;
    private String BaseURL = "https://fachtnaroe.net/bananas?", CreditURL ="&entity=person&method=GET&pID=", sID="sessionID=";
    private String[] startValue;
    private Web Web1, Web2, Web3, Web4, Web5;
    private Notifier Nofitier;

    protected void $define() {
        this.BackgroundColor(Component.COLOR_ORANGE);
        //[0]=extra quotation mark(not to be used) [1]=pId [2]=Username [3]=SessionID [4]=extra quotation mark(not to be used)
        startValue = this.startupValue.split("<SPLIT>");
        VArr = new VerticalArrangement(this);
        VArr.Height(LENGTH_FILL_PARENT);
        VArr.Width(LENGTH_FILL_PARENT);
        VArr.BackgroundColor(Component.COLOR_ORANGE);
        VArr.Image("FDS_PossibleLogo_04.png");

        LblFoodDeliveryService = new Label(VArr);
        LblFoodDeliveryService.Width(LENGTH_FILL_PARENT);
        LblFoodDeliveryService.FontSize(20);
        LblFoodDeliveryService.FontBold(true);
        LblFoodDeliveryService.Text("Food Delivery Service");
        LblFoodDeliveryService.TextColor(Component.COLOR_WHITE);
        LblFoodDeliveryService.TextAlignment(Component.ALIGNMENT_CENTER);
        LblFoodDeliveryService.BackgroundColor(Color.parseColor("#005200"));

        HorizonUserInformation = new HorizontalArrangement(VArr);
        HorizonUserInformation.Width(LENGTH_FILL_PARENT);
        HorizonUserInformation.HeightPercent(5);
        HorizonUserInformation.BackgroundColor(Component.COLOR_NONE);

        LblUsername = new Label(HorizonUserInformation);
        LblUsername.FontSize(15);
        LblUsername.Text("Username:");
        LblUsername.TextColor(Component.COLOR_BLACK);

        LblTextbox = new Label(HorizonUserInformation);
        LblTextbox.Width(LENGTH_FILL_PARENT);
        LblTextbox.FontSize(15);
        LblTextbox.Text(startValue[2]);
        LblTextbox.TextColor(COLOR_BLUE);
        LblTextbox.FontBold(true);

        LblpID = new Label(HorizonUserInformation);
        LblpID.FontSize(15);
        LblpID.Text("pID:");
        LblpID.TextColor(Component.COLOR_BLACK);

        LbleTextboxpID = new Label(HorizonUserInformation);
        LbleTextboxpID.FontSize(15);
        LbleTextboxpID.Text(startValue[1]);
        LbleTextboxpID.TextColor(Component.COLOR_BLUE);
        LbleTextboxpID.FontBold(true);

        LblListofFood = new Label(VArr);
        LblListofFood.Width(LENGTH_FILL_PARENT);
        LblListofFood.FontSize(12);
        LblListofFood.Text("Things Available For Purchase:");
        LblListofFood.TextColor(Component.COLOR_WHITE);
        LblListofFood.FontItalic(true);
        LblListofFood.BackgroundColor(Color.parseColor("#005200"));

        ListofFood = new ListView(VArr);
        ListofFood.Width(LENGTH_FILL_PARENT);
        ListofFood.Height(LENGTH_FILL_PARENT);
        ListofFood.TextSize(14);
        ListofFood.TextColor(Component.COLOR_BLACK);
        ListofFood.SelectionColor(Color.parseColor("#009F00"));
        ListofFood.BackgroundColor(Component.COLOR_NONE);

        HorizonCreditBuyBtn = new HorizontalArrangement(VArr);
        HorizonCreditBuyBtn.Width(LENGTH_FILL_PARENT);
        HorizonCreditBuyBtn.HeightPercent(7);
        HorizonCreditBuyBtn.BackgroundColor(Component.COLOR_NONE);

        HorizonCredit = new HorizontalArrangement(HorizonCreditBuyBtn);
        HorizonCredit.Width(LENGTH_FILL_PARENT);
        HorizonCredit.HeightPercent(7);
        HorizonCredit.BackgroundColor(Component.COLOR_NONE);

        LblofCredit = new Label(HorizonCredit);
        LblofCredit.FontSize(20);
        LblofCredit.Width(LENGTH_FILL_PARENT);
        LblofCredit.TextColor(Component.COLOR_BLACK);
        LblofCredit.Text("Credit ");
        LblofCredit.BackgroundColor(Component.COLOR_NONE);

        CreditCurrency = new Label(HorizonCredit);
        CreditCurrency.FontSize(20);
        CreditCurrency.TextColor(Component.COLOR_BLACK);
        CreditCurrency.Width(LENGTH_FILL_PARENT);
        CreditCurrency.BackgroundColor(Component.COLOR_NONE);

        HorizonBuyBtn = new HorizontalArrangement(HorizonCreditBuyBtn);
        HorizonBuyBtn.Width(LENGTH_FILL_PARENT);
        HorizonBuyBtn.HeightPercent(7);
        HorizonBuyBtn.BackgroundColor(Component.COLOR_NONE);

        BTN_BuyItem = new Button(HorizonBuyBtn);
        BTN_BuyItem.Text("Buy");
        BTN_BuyItem.Width(LENGTH_FILL_PARENT);
        BTN_BuyItem.TextColor(COLOR_BLACK);

        LblofOrderedItems = new Label(VArr);
        LblofOrderedItems.Width(LENGTH_FILL_PARENT);
        LblofOrderedItems.FontSize(12);
        LblofOrderedItems.Text("Things I've Ordered:");
        LblofOrderedItems.TextColor(Component.COLOR_WHITE);
        LblofOrderedItems.FontItalic(true);
        LblofOrderedItems.BackgroundColor(Color.parseColor("#005200"));

        OrderedList = new ListView(VArr);
        OrderedList.Width(LENGTH_FILL_PARENT);
        OrderedList.HeightPercent(30);
        OrderedList.TextColor(Component.COLOR_BLACK);
        OrderedList.TextSize(14);
        OrderedList.SelectionColor(Color.parseColor("#009F00"));
        OrderedList.BackgroundColor(Component.COLOR_NONE);

        Nofitier = new Notifier(this);
        Nofitier.BackgroundColor(Component.COLOR_GREEN);
        Nofitier.TextColor(Component.COLOR_WHITE);

        Web1 = new Web(this);
        Web1.Url(BaseURL + sID + startValue[3] + "&entity=thing&method=GET");
        Web1.Get();

        Web2 = new Web(this);
        Web2.Url(BaseURL + sID + startValue[3] + "&entity=prettyorders&method=GET");
        Web2.Get();

        Web3 = new Web(this);
        Web3.Url(BaseURL + sID + startValue[3] + CreditURL + startValue[1]);
        Web3.Get();

        Web4 = new Web(this);

        Web5 = new Web(this);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "Initialize");
        EventDispatcher.registerEventForDelegation(this, "GotTextEvent", "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (eventName.equals("Click")) {
            if (component.equals(BTN_BuyItem)) {
                buyItem(ListofFood.Selection());
                return true;
            } else {
                return false;
            }
        }
        else if (component.equals(Web1) && eventName.equals("GotText")) {
            //calling the procedure For the ListView containing the Items that are available to buy
            jsonSortAndListViewForBuyerScreen(params[1].toString(), (String) params[3],"thing", "null");
            return true;
        }
        else if (component.equals(Web2) && eventName.equals("GotText")) {
            //calling the procedure For the ListView containing the Items that the buyer has ordered
            jsonSortAndListViewForBuyerScreen(params[1].toString(), (String) params[3],"prettyorders", "buyerID");
            return true;
        }
        else if (component.equals(Web3) && eventName.equals("GotText")) {
            //calling procedure to show credit amount
            jsonSortAndListViewForBuyerScreen(params[1].toString(), (String) params[3],"person", "Credit");
            return true;
        }
        else if (component.equals(Web5) && eventName.equals("GotText")) {
            //procedure to check if order was placed
            jsonConfirmOrderPlaced(params[1].toString(), (String) params[3]);
        }
        return false;
    }
    public void jsonConfirmOrderPlaced(String status, String textOfResponse) {
        if (status.equals("200")) try {
            // See:  https://stackoverflow.com/questions/5015844/parsing-json-object-in-java
            JSONObject parser = new JSONObject(textOfResponse);
            String status_json = parser.getString("Status");
            if(status_json.contains("OK")){
                String oID = parser.getString("oID");
                Nofitier.ShowAlert("Your Order Has Been Placed : " + oID);
                Web2.Get();
                Web3.Get();
            }
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
            Nofitier.ShowMessageDialog("Error 3.353; JSON Exception (check password) ", "Information", "OK");
        }
        else {
            Nofitier.ShowMessageDialog("Error 3.356; Problem connecting with server", "Information", "OK");
        }
    }
    public void buyItem(String listSelection) {
        if ((ListofFood.Selection().isEmpty())) {
            Nofitier.ShowAlert("No Item Selected");
        } else {
            int i = listSelection.indexOf("]");
            int euro = listSelection.indexOf("€") + 1;
            String price = listSelection.substring(euro);
            String oldCredit = CreditCurrency.Text().replace("€", "");
            String y = listSelection.substring(1, i);
            String[] url_IDs = y.split(":");
            Web5.Url(BaseURL + sID + startValue[3] + "&entity=orders&method=POST&tID=" + url_IDs[0] + "&sellerID=" + url_IDs[1] + "&slotNum=1&buyerID=" + startValue[1]);
            Double diffCredit = Double.parseDouble(oldCredit) - Double.parseDouble(price);
            String newCredit = Double.toString(diffCredit);
            Web4.Url("https://fachtnaroe.net/bananas?sessionID=a1b2c3d4&entity=person&method=PUT&pID=" + startValue[1] + "&Credit=" + newCredit);
            Web4.Get();
            Web5.Get();
        }
    }
    //this procedure can be called for both listViews, (uses the kawa-1.7 library)
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
                    //gets Credit amount
                    if(tableName.equals("person") && fieldName.equals("Credit")){
                        oneEntryInTheListView = jsonIsMySon.getJSONObject(i).getString("Credit");
                        ListViewItemArray.add(oneEntryInTheListView);
                    }
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
                    else if ((tableName.equals("prettyorders") && fieldName.equals("buyerID")) && (Integer.valueOf(jsonIsMySon.getJSONObject(i).getString(fieldName)).equals( Integer.valueOf(startValue[1])))) {
                        oneEntryInTheListView = "[" + jsonIsMySon.getJSONObject(i).getString("oID")
                                + "] " + jsonIsMySon.getJSONObject(i).getString("tName")
                                + " from " + jsonIsMySon.getJSONObject(i).getString("seller")
                                + " [ tID: " + jsonIsMySon.getJSONObject(i).getString("tID") + " ]";
                        ListViewItemArray.add(oneEntryInTheListView);
                    }
                }
                YailList tempData = YailList.makeList(ListViewItemArray);
                if(tableName.equals("person") && fieldName.equals("Credit")){
                    CreditCurrency.Text("€" + tempData.get(1));
                }
                if (tableName.equals("prettyorders") && fieldName.equals("buyerID")) {
                    OrderedList.Elements(tempData);
                }
                if (tableName.equals("thing") && fieldName.equals("null")) {
                    ListofFood.Elements(tempData);
                }
            }
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
            Nofitier.ShowMessageDialog("Error 3.353; JSON Exception (check password) ", "Information", "OK");
        }
        else {
            Nofitier.ShowMessageDialog("Error 3.356; Problem connecting with server", "Information", "OK");
        }
    }
}