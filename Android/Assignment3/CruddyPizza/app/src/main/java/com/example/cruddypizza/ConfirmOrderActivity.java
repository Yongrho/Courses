/* reference: https://mkyong.com/android/android-radio-buttons-example/ */

package com.example.cruddypizza;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cruddypizza.data.MenuData;
import com.example.cruddypizza.data.MenuSQLiteHelper;
import com.example.cruddypizza.data.OrderData;
import com.example.cruddypizza.data.OrdersSQLiteHelper;
import com.example.cruddypizza.data.ToppingData;
import com.example.cruddypizza.data.ToppingsSQLiteHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfirmOrderActivity extends AppCompatActivity {
    private static final String TAG = "ConfirmOrderActivity";

    private static final int TOPPING_PEPPERONI = 0;
    private static final int TOPPING_SALAMI = 1;
    private static final int TOPPING_TOMATOES = 2;
    private static final int TOPPING_MUSHROOM = 3;
    private static final int TOPPING_CHICKEN = 4;
    private static final int TOPPING_BEEF = 5;

    private long orderId;
    private boolean orderNew;
    private final OrdersSQLiteHelper tableOrder = new OrdersSQLiteHelper(this);
    private final MenuSQLiteHelper tableMenu = new MenuSQLiteHelper(this);
    private final ToppingsSQLiteHelper tableTopping = new ToppingsSQLiteHelper(this);
    private final HashMap<Integer, Double> mapTopping = new HashMap<>();
    String[] arrayStringSize;
    String[] arrayStringTopping;
    String[] arrayStringMenu;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNew = extras.getBoolean("order_new");
            orderId = extras.getLong("order_id");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.order));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        arrayStringSize = getResources().getStringArray(R.array.size_values);
        arrayStringTopping = getResources().getStringArray(R.array.topping_values);
        arrayStringMenu = getResources().getStringArray(R.array.menu_values);

        mapTopping.put(TOPPING_PEPPERONI, 1.99);
        mapTopping.put(TOPPING_SALAMI, 1.99);
        mapTopping.put(TOPPING_TOMATOES, 0.99);
        mapTopping.put(TOPPING_MUSHROOM, 0.99);
        mapTopping.put(TOPPING_CHICKEN, 2.99);
        mapTopping.put(TOPPING_BEEF, 2.99);

        OrderData dataOrder = tableOrder.readOrder(orderId);
        System.out.println(dataOrder);
        long menuId = dataOrder.getOrderMenuId();
        MenuData dataMenu = tableMenu.readMenu(menuId);
        System.out.println(dataMenu);

        TextView tvPizzaName = findViewById(R.id.pizza_name);
        tvPizzaName.setText(arrayStringMenu[dataMenu.getMenuName()]);

        double total = updateMyPizza(dataMenu);

        TextView tvQuantity = findViewById(R.id.quantity);
        tvQuantity.setText(String.valueOf(dataOrder.getOrderQuantity()));

        TextView tvName = findViewById(R.id.name);
        tvName.setText(dataOrder.getOrderCustomerName());

        TextView tvEmail = findViewById(R.id.email);
        tvEmail.setText(dataOrder.getOrderCustomerEmail());

        TextView tvAddress = findViewById(R.id.address);
        tvAddress.setText(dataOrder.getOrderCustomerAddress());

        TextView tvPhone = findViewById(R.id.phone);
        tvPhone.setText(dataOrder.getOrderCustomerPhone());

        TextView tvTotal = findViewById(R.id.total);
        total += dataMenu.getMenuPrice();
        total *= dataOrder.getOrderQuantity();
        dataOrder.setOrderTotalPrice(total);
        tvTotal.setText(getResources().getString(R.string.total_dollar) + String.format("%.2f", total));
        tableOrder.updateOrder(dataOrder);

        Button btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(ConfirmOrderActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.order_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                ConfirmOrderActivity.this.startActivity(intent);
                            }
                        }).show();
            }
        });

        Button btnModify = findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuildActivity.class);
                intent.putExtra("order_new", false);
                intent.putExtra("order_id", orderId);
                intent.putExtra("menu_id", menuId);
                ConfirmOrderActivity.this.startActivity(intent);
            }
        });

        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataMenu.getMenuPromoted() == 2) {
                    tableMenu.deleteMenu(dataMenu);
                }
                tableOrder.deleteOrder(dataOrder);
                finish();
            }
        });

        TableRow trOrder = findViewById(R.id.tr_order);
        TableRow trModify = findViewById(R.id.tr_modify);
        if (orderNew) {
            trOrder.setVisibility(View.VISIBLE);
            trModify.setVisibility(View.GONE);
        } else {
            trOrder.setVisibility(View.GONE);
            trModify.setVisibility(View.VISIBLE);
        }
    }

    private double updateMyPizza(MenuData data) {
        double total = 0.0;
        StringBuilder myPizzaString = new StringBuilder();
        myPizzaString.append(arrayStringSize[data.getMenuSize()]);

        ArrayList<ToppingData> arrayTopping = tableTopping.getAllToppings(data.getId());

        if (arrayTopping != null && arrayTopping.size() > 0) {
            for (int i = 0; i < arrayTopping.size(); i++) {
                ToppingData toppingData = arrayTopping.get(i);
                String toppingName = arrayStringTopping[toppingData.getToppingName()];
                myPizzaString.append(", ");
                myPizzaString.append(toppingName);
                if (data.getMenuPromoted() != 1) {
                    total += mapTopping.get(toppingData.getToppingName());
                }
            }
        }

        TextView tvMyPizza = findViewById(R.id.my_pizza);
        tvMyPizza.setText(myPizzaString.toString());

        return total;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}