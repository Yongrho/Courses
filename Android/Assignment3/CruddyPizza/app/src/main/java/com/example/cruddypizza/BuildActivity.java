// reference: https://mkyong.com/android/android-radio-buttons-example/

package com.example.cruddypizza;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cruddypizza.data.MenuData;
import com.example.cruddypizza.data.MenuSQLiteHelper;
import com.example.cruddypizza.data.SizeData;
import com.example.cruddypizza.data.SizeSQLiteHelper;
import com.example.cruddypizza.data.ToppingData;
import com.example.cruddypizza.data.ToppingsSQLiteHelper;

import java.util.ArrayList;

public class BuildActivity extends AppCompatActivity {
    private static final String TAG = "BuildActivity";

    private static final int TOPPING_PEPPERONI = 0;
    private static final int TOPPING_SALAMI = 1;
    private static final int TOPPING_TOMATOES = 2;
    private static final int TOPPING_MUSHROOM = 3;
    private static final int TOPPING_CHICKEN = 4;
    private static final int TOPPING_BEEF = 5;

    private static final int SIZE_NAME_SMALL = 0;
    private static final int SIZE_NAME_MEDIUM = 1;
    private static final int SIZE_NAME_LARGE = 2;
    private static final int SIZE_NAME_EXTRA_LARGE = 3;

    private static final double SIZE_PRICE_SMALL = 10.99;
    private static final double SIZE_PRICE_MEDIUM = 12.99;
    private static final double SIZE_PRICE_LARGE = 15.99;
    private static final double SIZE_PRICE_EXTRA_LARGE = 10.99;

    private final String[] arrayQuantity = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String quantity = arrayQuantity[0];
    private MenuData data;
    private long menuId;
    private long orderId;
    boolean orderNew;

    private final MenuSQLiteHelper tableMenu = new MenuSQLiteHelper(this);
    private final SizeSQLiteHelper tableSize = new SizeSQLiteHelper(this);
    private final ToppingsSQLiteHelper tableTopping = new ToppingsSQLiteHelper(this);
    private ArrayList<SizeData> arraySize;
    private ArrayList<ToppingData> arrayTopping;

    private TextView myPizza;
    String[] arrayStringSize;
    String[] arrayStringTopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
        arrayStringSize = getResources().getStringArray(R.array.size_values);
        arrayStringTopping = getResources().getStringArray(R.array.topping_values);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNew = extras.getBoolean("order_new");
            orderId = extras.getLong("order_id");
            menuId = extras.getLong("menu_id");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.title_activity_build));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        myPizza = findViewById(R.id.textView4);

        RadioGroup radioSizeGroup = findViewById(R.id.radioGroup);
        RadioButton sizeSmall = findViewById(R.id.radioButton);
        sizeSmall.setText(arrayStringSize[0]);
        RadioButton sizeMedium = findViewById(R.id.radioButton2);
        sizeMedium.setText(arrayStringSize[1]);
        RadioButton sizeLarge = findViewById(R.id.radioButton3);
        sizeLarge.setText(arrayStringSize[2]);
        RadioButton sizeExtraLarge = findViewById(R.id.radioButton4);
        sizeExtraLarge.setText(arrayStringSize[3]);

        radioSizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            // Check which radio button has been clicked
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton2:
                        data.setMenuSize(1);
                        break;
                    case R.id.radioButton3:
                        data.setMenuSize(2);
                        break;
                    case R.id.radioButton4:
                        data.setMenuSize(3);
                        break;
                    default:
                        data.setMenuSize(0);
                        break;
                }

                arraySize = tableSize.getAllSize(menuId);
                if (arraySize == null) {
                    switch (data.getMenuSize()) {
                        case SIZE_NAME_MEDIUM:
                            data.setMenuPrice(SIZE_PRICE_MEDIUM);
                            break;
                        case SIZE_NAME_LARGE:
                            data.setMenuPrice(SIZE_PRICE_LARGE);
                            break;
                        case SIZE_NAME_EXTRA_LARGE:
                            data.setMenuPrice(SIZE_PRICE_EXTRA_LARGE);
                            break;
                        default:
                            data.setMenuPrice(SIZE_PRICE_SMALL);
                            break;
                    }
                } else {
                    for (int i = 0; i < arraySize.size(); i++) {
                        SizeData sizeData = arraySize.get(i);
                        if (data.getMenuSize() == sizeData.getMenuSize()) {
                            data.setMenuPrice(sizeData.getMenuPrice());
                            break;
                        }
                    }
                }
                updateMyPizza();
            }
        });

        CheckBox pepperoni = findViewById(R.id.checkBox);
        pepperoni.setText(arrayStringTopping[0]);
        pepperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopping(pepperoni, TOPPING_PEPPERONI);
            }
        });

        CheckBox salami = findViewById(R.id.checkBox2);
        salami.setText(arrayStringTopping[1]);
        salami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopping(salami, TOPPING_SALAMI);
            }
        });

        CheckBox tomatoes = findViewById(R.id.checkBox3);
        tomatoes.setText(arrayStringTopping[2]);
        tomatoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopping(tomatoes, TOPPING_TOMATOES);
            }
        });

        CheckBox mushroom = findViewById(R.id.checkBox4);
        mushroom.setText(arrayStringTopping[3]);
        mushroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopping(mushroom, TOPPING_MUSHROOM);
            }
        });

        CheckBox chicken = findViewById(R.id.checkBox5);
        chicken.setText(arrayStringTopping[4]);
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopping(chicken, TOPPING_CHICKEN);
            }
        });

        CheckBox beef = findViewById(R.id.checkBox6);
        beef.setText(arrayStringTopping[5]);
        beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopping(beef, TOPPING_BEEF);
            }
        });

        Button buttonContinue = findViewById(R.id.btnContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayTopping = tableTopping.getAllToppings(menuId);
                data.setMenuTopping(arrayTopping);
                tableMenu.updateMenu(data);

                Intent intent;
                intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra("order_new", orderNew);
                intent.putExtra("order_id", orderId);
                intent.putExtra("menu_id", data.getId());
                intent.putExtra("quantity", quantity);
                BuildActivity.this.startActivity(intent);
            }
        });

        if (menuId == 0) {
            data = new MenuData(0, R.drawable.logo_pizza, 0);
            radioSizeGroup.check(R.id.radioButton);
            data.setMenuSize(0);
            data.setMenuPrice(SIZE_PRICE_SMALL);
            data.setMenuPromoted(2);
            menuId = tableMenu.createMenu(data);
            data.setId(menuId);
        } else {
            data = tableMenu.readMenu(menuId);

            arrayTopping = tableTopping.getAllToppings(menuId);
            data.setMenuTopping(arrayTopping);

            for (int i = 0; i < arrayTopping.size(); i++) {
                ToppingData toppingData = arrayTopping.get(i);
                switch (toppingData.getToppingName()) {
                    case TOPPING_SALAMI:
                        salami.setChecked(true);
                        break;
                    case TOPPING_TOMATOES:
                        tomatoes.setChecked(true);
                        break;
                    case TOPPING_MUSHROOM:
                        mushroom.setChecked(true);
                        break;
                    case TOPPING_CHICKEN:
                        chicken.setChecked(true);
                        break;
                    case TOPPING_BEEF:
                        beef.setChecked(true);
                        break;
                    default:
                        pepperoni.setChecked(true);
                        break;
                }
            }

            if (data.getMenuPromoted() == 1) {
                switch (data.getMenuSize()) {
                    case 1:
                        radioSizeGroup.check(R.id.radioButton2);
                        break;
                    case 2:
                        radioSizeGroup.check(R.id.radioButton3);
                        break;
                    case 3:
                        radioSizeGroup.check(R.id.radioButton4);
                        break;
                    default:
                        radioSizeGroup.check(R.id.radioButton);
                        break;
                }

                radioSizeGroup.setEnabled(false);
                sizeSmall.setEnabled(false);
                sizeMedium.setEnabled(false);
                sizeLarge.setEnabled(false);
                sizeExtraLarge.setEnabled(false);

                pepperoni.setEnabled(false);
                salami.setEnabled(false);
                tomatoes.setEnabled(false);
                mushroom.setEnabled(false);
                chicken.setEnabled(false);
                beef.setEnabled(false);
            } else {
                radioSizeGroup.check(R.id.radioButton);
            }
        }
        updateMyPizza();
        setQuantity();
    }

    private void updateMyPizza() {
        StringBuilder myPizzaString = new StringBuilder();
        myPizzaString.append(arrayStringSize[data.getMenuSize()]);

        arrayTopping = tableTopping.getAllToppings(menuId);
        if (arrayTopping != null && arrayTopping.size() > 0) {
            for (int i = 0; i < arrayTopping.size(); i++) {
                ToppingData toppingData = arrayTopping.get(i);
                String toppingName = arrayStringTopping[toppingData.getToppingName()];
                myPizzaString.append(", ");
                myPizzaString.append(toppingName);
            }
        }
        myPizza.setText(myPizzaString.toString());
    }

    private void setQuantity() {
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                quantity = arrayQuantity[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        // Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayQuantity);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    private long hasTopping(int toppingName) {
        arrayTopping = tableTopping.getAllToppings(menuId);

        for (int i = 0; i < arrayTopping.size(); i++) {
            ToppingData data = arrayTopping.get(i);
            if (toppingName == data.getToppingName()) {
                return data.getId();
            }
        }
        return -1;
    }
    private void setTopping(CheckBox checkBox, int toppingName) {
        long id = hasTopping(toppingName);
        if (checkBox.isChecked()) {
            if (id > 0) {
                return;
            }
            ToppingData data = new ToppingData();
            data.setMenuId(menuId);
            data.setToppingName(toppingName);
            tableTopping.createTopping(data);
        } else {
            if (id > 0) {
                tableTopping.deleteTopping(id);
            }
        }
        updateMyPizza();
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