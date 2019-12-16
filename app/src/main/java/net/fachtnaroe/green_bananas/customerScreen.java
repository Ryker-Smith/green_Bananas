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
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Web;

import java.util.ArrayList;
import java.util.List;

public class customerScreen extends Form implements HandlesEventDispatching {
    private Label FoodLbl, CreditLbl, AvaFood, UserLbl, pIDLbl, ThingOrder;
    private HorizontalArrangement HarrLbl,HarrBuyBtn, HarrList, HarrUser;
    private VerticalArrangement Screen1;
    private Button BuyBtn;
    private Web Web;
    private ListView ListView, ListView2;
    private String BaseURL = "https://fachtnaroe.net/bananas?";


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

        HarrUser=new HorizontalArrangement(Screen1);
        HarrUser.WidthPercent(100);
        HarrUser.HeightPercent(5);
        HarrUser.BackgroundColor(00000000);
        UserLbl=new Label(HarrUser);
        UserLbl.WidthPercent(50);
        UserLbl.TextColor(COLOR_BLACK);
        UserLbl.Text("Username");
        UserLbl.FontSize(14);
        UserLbl.BackgroundColor(00000000);

        pIDLbl=new Label(HarrUser);
        pIDLbl.Text("pID 15  "); //The reason there is 2 spaces after the "pID 15" is for it to fit on the screen.
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
        ListView.BackgroundColor(00000000);

        Web = new Web(this);
        Web.Url(BaseURL + "sessionID=a1b2c3d4&entity=thing&method=GET");
        Web.Get();

        HarrBuyBtn = new HorizontalArrangement(Screen1);
        HarrBuyBtn.Width(LENGTH_FILL_PARENT);
        HarrBuyBtn.HeightPercent(10);
        CreditLbl= new Label(HarrBuyBtn);
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

        ThingOrder= new Label (Screen1);
        ThingOrder.WidthPercent(100);
        ThingOrder. HeightPercent(3);
        ThingOrder.Text("Things I've ordered");
        ThingOrder.FontSize(12);
        ThingOrder.TextAlignment(ALIGNMENT_NORMAL);
        ThingOrder.BackgroundColor((Color.parseColor("#005200")));
        ThingOrder.TextColor(Color.WHITE);

        ListView2= new ListView(Screen1);
        ListView2.WidthPercent(100);
        ListView2.HeightPercent(100);
        ListView2.BackgroundColor(00000000);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, "GotTextEvent", "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(Web) && eventName.equals("GotText")) {
            JsonSortThingsListView((String) params[3]);
            // ListView.ElementsFromString((String)params[3]);
            return true;
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

    public void JsonSortThingsListView(String jsonString) {

// for loop to sort by pID
        String Temp1 = "";
        //Used https://stackoverflow.com/questions/48449004/java-storing-the-output-of-a-for-loop-into-an-array/48449039 and https://www.w3schools.com/java/java_ref_string.asp
        List<String> jsonIsMySon = new ArrayList<String>();
        char start = '{';
        char finish = '}';
        int e = 0;
        for (int i = 0; i < jsonString.length(); i++) {
            char thisChar = jsonString.charAt(i);
            if (thisChar == start) {

                e = i + 1;
            } else if ((thisChar == finish)) {
                String Temp2 = jsonString.substring(e, i);

                if (!(Temp2.contains("]"))) {
                    if (Temp2.contains("tSoldBy\":\"")) {
                        jsonIsMySon.add(Temp2);
                    }
                }
            }

        }

//        LBL_AvToOrdr.Text(jsonIsMySon.get(1));

//        String loge = "";
        //For Loop to Rearrange Data To How I want
        String Temp3 = "";
        for (int a = 0; a < jsonIsMySon.size(); a++) {
//            String tempi="";

            String r1 = jsonIsMySon.get(a).replace("\",\"", "<SPLIT>");
            String r2 = r1.replace(",", "-");
//                tempi = r2.replace("*@*", ",");

            String[] keyValueArray = r2.split("<SPLIT>");
            jsonIsMySon.set(a, "[" + keyValueArray[1] + ":" + keyValueArray[5] + "]" + keyValueArray[2] + "(" + keyValueArray[0] + ")€" + keyValueArray[4]);


            if (a == 0) {
                //Rearrange Json data [0]=tDescription,[1]=tID,[2]=tName,[3]=tPicture,[4]=tPrice,[5]=tSoldBy  logetiddy
//                String[] keyValueArray = r1.split(",");
//                jsonIsMySon.set(a,"["+keyValueArray[2]+":"+keyValueArray[6]+"]"+keyValueArray[3]+"("+keyValueArray[1]+")€"+keyValueArray[5]);
                Temp3 += jsonIsMySon.get(a);
            } else {
                //Rearrange Json data [0]=tDescription,[1]=tID,[2]=tName,[3]=tPicture,[4]=tPrice,[5]=tSoldBy  logetiddy
//                String[] keyValueArray = r1.split(",");
//                jsonIsMySon.set(a,"["+keyValueArray[1]+":"+keyValueArray[5]+"]"+keyValueArray[2]+"("+keyValueArray[0]+")€"+keyValueArray[4]);
                Temp3 += "," + jsonIsMySon.get(a);
            }
        }

        //Format for use in listView-Remove KeyNames
        String r2 = Temp3.replace("\":\"", "");
        String r3 = r2.replace("\"tDescription", "");
        String r4 = r3.replace("tID", "");
        String r5 = r4.replace("tName", "");
        String r6 = r5.replace("tPrice", "");
        String r7 = r6.replace("tSoldBy", "");
        String r8 = r7.replace("\"", "");

        ListView.ElementsFromString(r8);
        ListView.TextColor(COLOR_BLACK);
        ListView.TextSize(20);
        ListView.SelectionColor((Color.parseColor("#009F00")));
        // String y=jsonIsMySon.get(0);
    }

}