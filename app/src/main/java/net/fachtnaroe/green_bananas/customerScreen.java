package net.fachtnaroe.green_bananas;

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
    private Label FoodLbl;
    private HorizontalArrangement HarrLbl, HarrAddBtn, HarrBuyBtn, HarrList, HarrCredit,HarrOrdered;
    private VerticalArrangement Screen1;
    private Button AddtoCartBtn, BuyBtn;
    private Web Web, Web_Credit;
    private ListView ListAvailable, ListOrdered;
    private String BaseURL = "https://fachtnaroe.net/bananas?",  SessionID = MainActivity.getSessionID(),   pID = MainActivity.getPID();
//    pID=LoginScreen.getpID(), Username=LoginScreen.getUsername();


    protected void $define() {

        Screen1 = new VerticalArrangement(this);
        Screen1.Width(Component.LENGTH_FILL_PARENT);
        Screen1.Height(Component.LENGTH_FILL_PARENT);

        HarrLbl = new HorizontalArrangement(Screen1);
        HarrLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl = new Label(HarrLbl);
        FoodLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl.Text("List of Food");
        FoodLbl.FontSize(20);
        FoodLbl.TextAlignment(Component.ALIGNMENT_CENTER);

        HarrList = new HorizontalArrangement(Screen1);
        HarrList.WidthPercent(100);
        HarrList.HeightPercent(70);
        ListAvailable = new ListView(HarrList);
        ListAvailable.HeightPercent(100);

        Web = new Web(this);
        Web.Url(BaseURL + "sessionID=a1b2c3d4&entity=thing&method=GET");
        Web.Get();

        HarrAddBtn = new HorizontalArrangement(Screen1);
        HarrAddBtn.Width(Component.LENGTH_FILL_PARENT);
        AddtoCartBtn = new Button(HarrAddBtn);
        AddtoCartBtn.Width(Component.LENGTH_FILL_PARENT);
        AddtoCartBtn.Text("Add to Cart");
        AddtoCartBtn.FontSize(14);
        AddtoCartBtn.TextAlignment(Component.ALIGNMENT_CENTER);

        HarrBuyBtn = new HorizontalArrangement(Screen1);
        HarrBuyBtn.Width(Component.LENGTH_FILL_PARENT);
        BuyBtn = new Button(HarrBuyBtn);
        BuyBtn.Width(Component.LENGTH_FILL_PARENT);
        BuyBtn.Text("Buy");
        BuyBtn.FontSize(14);
        BuyBtn.TextAlignment(Component.ALIGNMENT_CENTER);

        HarrCredit= new HorizontalArrangement(Screen1);
        HarrCredit.HeightPercent(20);
        HarrCredit.WidthPercent(100);
        Web_Credit= new Web(this);
        Web_Credit.Url(BaseURL+"sessionID="+ SessionID + "&entity=person&method=GET&pID="+ pID);
        Web_Credit.Get();




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
            if (component.equals(AddtoCartBtn)) {
                AddtoCartBtn();
                return true;

            }

        }
        return false;

    }

    private void AddtoCartBtn() {
        AddtoCartBtn.Text("Pressed");

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

        ListAvailable.ElementsFromString(r8);
        // String y=jsonIsMySon.get(0);


    }
}

