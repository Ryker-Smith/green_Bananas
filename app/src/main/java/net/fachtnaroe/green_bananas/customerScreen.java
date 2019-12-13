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
    private Label FoodLbl, CreditLbl;
    private HorizontalArrangement HarrLbl,HarrBuyBtn, HarrList;
    private VerticalArrangement Screen1;
    private Button BuyBtn;
    private Web Web;
    private ListView ListView;
    private String BaseURL = "https://fachtnaroe.net/bananas?";


    protected void $define() {
        Screen1 = new VerticalArrangement(this);
        Screen1.Width(Component.LENGTH_FILL_PARENT);
        Screen1.Height(Component.LENGTH_FILL_PARENT);
        Screen1.BackgroundColor(Component.COLOR_ORANGE);
        Screen1.Image("FDS_PossibleLogo_04.png");

        HarrLbl = new HorizontalArrangement(Screen1);
        HarrLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl = new Label(HarrLbl);
        FoodLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl.Text("Food Delivery Service");
        FoodLbl.FontSize(20);
        FoodLbl.TextAlignment(Component.ALIGNMENT_CENTER);
        FoodLbl.BackgroundColor(Color.parseColor("#005200"));
        FoodLbl.TextColor(Color.WHITE);

//This is where the Username Label and pID go.

//This is where the "Things Available" Label goes.

        HarrList = new HorizontalArrangement(Screen1);
        HarrList.WidthPercent(100);
        HarrList.HeightPercent(70);
        ListView = new ListView(HarrList);
        ListView.HeightPercent(90);
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
        CreditLbl.TextAlignment(ALIGNMENT_NORMAL);
        CreditLbl.BackgroundColor(00000000);

        BuyBtn = new Button(HarrBuyBtn);
        BuyBtn.WidthPercent(50);
        BuyBtn.Text("Buy");
        BuyBtn.FontSize(14);
        BuyBtn.TextAlignment(ALIGNMENT_CENTER);
        BuyBtn.BackgroundColor(00000000);

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
        ListView.SelectionColor((Color.parseColor("#009F00")));
        // String y=jsonIsMySon.get(0);
    }

}