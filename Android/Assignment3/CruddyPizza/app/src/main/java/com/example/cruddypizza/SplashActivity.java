package com.example.cruddypizza;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.cruddypizza.data.MenuData;
import com.example.cruddypizza.data.MenuSQLiteHelper;
import com.example.cruddypizza.data.SizeData;
import com.example.cruddypizza.data.SizeSQLiteHelper;
import com.example.cruddypizza.data.ToppingData;
import com.example.cruddypizza.data.ToppingsSQLiteHelper;

import java.util.ArrayList;
import java.util.Locale;

public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";

    private static final int SPLASH_DISPLAY_LENGTH = 100;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("cruddypizza", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getBaseContext().getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        createMenu();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    // This function is only for demo, because there is no screen that can edit menu
    private void createMenu() {
        MenuSQLiteHelper tableMenu = new MenuSQLiteHelper(this);
        ArrayList<MenuData> menuPromoted = tableMenu.getMenuAll(1);
        if (menuPromoted.size() > 0) {
            return;
        }

        SizeSQLiteHelper tableSize = new SizeSQLiteHelper(this);
        ToppingsSQLiteHelper tableTopping = new ToppingsSQLiteHelper(this);

        MenuData menuLarge4Topping = new MenuData(1, R.drawable.kisspng_california_style_pizza, 1);
        menuLarge4Topping.setMenuSize(2);
        menuLarge4Topping.setMenuPrice(12.99);
        long id = tableMenu.createMenu(menuLarge4Topping);
        tableTopping.createTopping(new ToppingData(id, 0, 0));
        tableTopping.createTopping(new ToppingData(id, 1, 0));
        tableTopping.createTopping(new ToppingData(id, 2, 0));
        tableTopping.createTopping(new ToppingData(id, 3, 0));

        MenuData menuMedium2Topping = new MenuData(2, R.drawable.kisspng_chicago_style_pizza, 1);
        menuMedium2Topping.setMenuSize(1);
        menuMedium2Topping.setMenuPrice(10.99);
        id = tableMenu.createMenu(menuMedium2Topping);
        tableTopping.createTopping(new ToppingData(id, 0, 0));
        tableTopping.createTopping(new ToppingData(id, 1, 0));

        MenuData menuSmall3Topping = new MenuData(3, R.drawable.kisspng_european_cuisine_pizza, 1);
        menuSmall3Topping.setMenuSize(0);
        menuSmall3Topping.setMenuPrice(8.99);
        id = tableMenu.createMenu(menuSmall3Topping);
        tableTopping.createTopping(new ToppingData(id, 0, 0));
        tableTopping.createTopping(new ToppingData(id, 1, 0));
        tableTopping.createTopping(new ToppingData(id, 2, 0));

        MenuData menuOriginal = new MenuData(4, R.drawable.kisspng_european_cuisine_pizza, 0);
        id = tableMenu.createMenu(menuOriginal);
        tableSize.createSize(new SizeData(id, 0, 7.99));
        tableSize.createSize(new SizeData(id, 1, 9.99));
        tableSize.createSize(new SizeData(id, 2, 11.99));

        MenuData menuPepperoni = new MenuData(5, R.drawable.kisspng_sicilian_pizza_pepperoni_salami_chicago_style_pizz, 0);
        id = tableMenu.createMenu(menuPepperoni);
        tableSize.createSize(new SizeData(id, 0, 9.99));
        tableSize.createSize(new SizeData(id, 1, 11.99));
        tableSize.createSize(new SizeData(id, 2, 13.99));
        tableTopping.createTopping(new ToppingData(id, 0, 0));

        MenuData menuCheese = new MenuData(6, R.drawable.transparent_cheese_cuisine_pizza, 0);
        id = tableMenu.createMenu(menuCheese);
        tableSize.createSize(new SizeData(id, 0, 10.99));
        tableSize.createSize(new SizeData(id, 1, 13.99));
        tableSize.createSize(new SizeData(id, 2, 16.99));
        tableTopping.createTopping(new ToppingData(id, 3, 0));

        MenuData menuHawaiian = new MenuData(7, R.drawable.transparent_hawaiian_pizza, 0);
        id = tableMenu.createMenu(menuHawaiian);
        tableSize.createSize(new SizeData(id, 0, 12.99));
        tableSize.createSize(new SizeData(id, 1, 13.99));
        tableSize.createSize(new SizeData(id, 2, 15.99));
        tableTopping.createTopping(new ToppingData(id, 3, 0));
        tableTopping.createTopping(new ToppingData(id, 4, 0));

        MenuData menuVegetarian = new MenuData(8, R.drawable.transparent_vegetarian_cuisine_pizza, 0);
        id = tableMenu.createMenu(menuVegetarian);
        tableSize.createSize(new SizeData(id, 0, 10.99));
        tableSize.createSize(new SizeData(id, 1, 12.99));
        tableSize.createSize(new SizeData(id, 2, 14.99));
        tableTopping.createTopping(new ToppingData(id, 2, 0));
        tableTopping.createTopping(new ToppingData(id, 3, 0));
    }
}