package net.fachtnaroe.green_bananas;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListView;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;

import java.util.ArrayList;
import java.util.List;

public class customerScreen extends Form implements HandlesEventDispatching {
    private Label FoodLbl;
    private HorizontalArrangement HarrLbl, HarrAddBtn, HarrBuyBtn, HarrListView;
    private VerticalScrollArrangement Screen1;
    private Button AddtoCartBtn, BuyBtn;
    private ListView ListView;
    private Web Web;
    private String BaseURL= "https://fachtnaroe.net/bananas?";
    private EventDispatcher EDP;

    protected void $define() {

        Screen1 = new VerticalScrollArrangement(this);
        Screen1.Width(Component.LENGTH_FILL_PARENT);
        Screen1.HeightPercent(100);

        HarrLbl= new HorizontalArrangement(Screen1);
        HarrLbl.Width(Component.LENGTH_FILL_PARENT);

        FoodLbl= new Label(HarrLbl);
        FoodLbl.Width(Component.LENGTH_FILL_PARENT);
        FoodLbl.Text("List of Food");
        FoodLbl.FontSize(20);
        FoodLbl.TextAlignment(Component.ALIGNMENT_CENTER);

        HarrAddBtn= new HorizontalArrangement(Screen1);
        HarrAddBtn.Width(Component.LENGTH_FILL_PARENT);

        AddtoCartBtn= new Button(HarrAddBtn);
        AddtoCartBtn.Width(Component.LENGTH_FILL_PARENT);
        AddtoCartBtn.Text("Add to Cart");
        AddtoCartBtn.FontSize(14);
        AddtoCartBtn.TextAlignment(Component.ALIGNMENT_CENTER);

        HarrBuyBtn= new HorizontalArrangement(Screen1);
        HarrBuyBtn.Width(Component.LENGTH_FILL_PARENT);

        BuyBtn=new Button(HarrBuyBtn);
        BuyBtn.Width(Component.LENGTH_FILL_PARENT);
        BuyBtn.Text("Buy");
        BuyBtn.FontSize(14);
        BuyBtn.TextAlignment(Component.ALIGNMENT_CENTER);

        HarrListView=new HorizontalArrangement(HarrListView);
        HarrListView.Width(Component.LENGTH_FILL_PARENT);

        ListView=new ListView(HarrListView);
        ListView.WidthPercent(100);
        ListView.HeightPercent(50);
        ListView.BackgroundColor(COLOR_BLUE);

        Web=new Web(this);
        Web.Url(BaseURL+"sessionID=a1b2c3d4&entity=thing&methodequalsget");
        Web.Get();

        EventDispatcher.registerEventForDelegation( this, formName, "Click" );
        EventDispatcher.registerEventForDelegation( this, "GotTextEvent", "GotText" );
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (component.equals(Web)&&eventName.equals("GotText")){
            Json((String)params[3]);
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
    public void Json(String jsonString){
            List<String> jsonIsMySon = new ArrayList<String>();
            String Temp1 = "";
            char start = '{';
            char finish = '}';
            int e = 0;
            for     (int i = 0; i < jsonString.length(); i++) {
                char thisChar = jsonString.charAt(i);
                if (thisChar == start) {
                    e = i+1;
                }
                else if ((thisChar == finish)) {
                    String Temp2 = jsonString.substring(e, i);
                    if (!(Temp2.contains("]"))){
//                        if (Temp2.contains("buyerID\":\"" + pID)) {
//                            jsonIsMySon.add(Temp2);
//                        }
                    }
                }

            }
//        for (int a=0;a<jsonIsMySon.size();a++){
//            Temp1+=jsonIsMySon.get(a)+"*-*";
//        }
//        String Loge="";
////
            String Temp3="";
            for (int a=0;a<jsonIsMySon.size();a++){
                String r1 = jsonIsMySon.get(a).replace("\",\"",",");

                //Rearrange Json data [0]=buyerID,[1]=oID,[2]=seller name ,[3]=tID,[4]=tName-ItemName
                String[] keyValueArray = r1.split(",");
                jsonIsMySon.set(a,"["+keyValueArray[1]+"] "+keyValueArray[4]+" from "+keyValueArray[2]);
                if(a==0){
                    Temp3+=jsonIsMySon.get(a);
                }
                else{
                    Temp3+=","+jsonIsMySon.get(a);
                }
            }

            String r1 = Temp3.replace("\"", "");
            String r2 = r1.replace(":", "");
            String r3 = r2.replace("oID", "");
            String r4 = r3.replace("tName", "");
            String r5 = r4.replace("seller", "");
//            LST_ThingsO.ElementsFromString(r5);
        }
    }
}