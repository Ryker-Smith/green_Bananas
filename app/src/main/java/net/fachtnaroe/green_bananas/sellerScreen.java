package net.fachtnaroe.green_bananas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.os.Bundle;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListPicker;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.ListView;

public class sellerScreen extends Form implements HandlesEventDispatching {
    private Label ProdNLbl, ProdDesLbl, PriceLbl, SellerNLbl;
    private TextBox ProdNTbx, ProdDesTbx, PriceTbx, SellerNTbx;
    private Button SellBtn;
    private VerticalArrangement vertA1;
    private HorizontalArrangement horiA1, horiA2, horiA3, horiA4, horiA5;
    private Notifier Notifier;
    private String pID=MainActivity.getPID(), Username = MainActivity.getUsername();

    protected void $define() {
        vertA1 = new VerticalArrangement(this);
        vertA1.WidthPercent(100);
        vertA1.HeightPercent(100);

        horiA1 = new HorizontalArrangement(vertA1);
        horiA1.WidthPercent(100);
        horiA1.HeightPercent(10);
        ProdNLbl = new Label(horiA1);
        ProdNLbl.Text("Product Name:");
        ProdNLbl.WidthPercent(LENGTH_FILL_PARENT);
        ProdNTbx = new TextBox(horiA1);
        ProdNTbx.WidthPercent(70);

        horiA2 = new HorizontalArrangement(vertA1);
        horiA2.WidthPercent(100);
        horiA2.HeightPercent(10);
        ProdDesLbl = new Label(horiA2);
        ProdDesLbl.Text("Product Description:");
        ProdDesLbl.WidthPercent(LENGTH_FILL_PARENT);
        ProdDesTbx = new TextBox(horiA2);
        ProdDesTbx.WidthPercent(100);

        horiA3 = new HorizontalArrangement(vertA1);
        horiA3.WidthPercent(100);
        horiA3.HeightPercent(10);
        PriceLbl = new Label(horiA3);
        PriceLbl.Text("Product Price:");
        PriceLbl.WidthPercent(LENGTH_FILL_PARENT);
        PriceTbx = new TextBox(horiA3);
        PriceTbx.WidthPercent(100);

        horiA4 = new HorizontalArrangement(vertA1);
        horiA4.WidthPercent(100);
        horiA4.HeightPercent(10);
        SellerNLbl = new Label(horiA4);
        SellerNLbl.Text("Seller Name");
        SellerNTbx = new TextBox(horiA4);
        SellerNTbx.WidthPercent(100);

        horiA5 = new HorizontalArrangement(vertA1);
        horiA5.WidthPercent(100);
        horiA5.HeightPercent(10);
        SellBtn = new Button(horiA5);
        SellBtn.Text("Sell");
        SellBtn.WidthPercent(100);
        SellBtn.HeightPercent(10);
    }
// Test for GIT
    /*public static class MyClass {
        public static void main(String[] args) {
            ArrayList<String> food = new ArrayList<String>();
            food.add("Apples");
            food.add("Pickel");
            food.add("Cornflakes");
            food.add("Pineapple");
            System.out.println(food);
        }
    }*/
}