package com.example.cruddypizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cruddypizza.data.OrderData;
import com.example.cruddypizza.data.OrdersSQLiteHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactActivity extends AppCompatActivity {
    private static final String TAG = "ContactActivity";

    private int quantity;
    private long menuId;
    private long orderId;
    boolean orderNew;

    private final OrdersSQLiteHelper tableOrder = new OrdersSQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNew = extras.getBoolean("order_new");
            orderId = extras.getLong("order_id");
            menuId = extras.getLong("menu_id");
            quantity = Integer.parseInt(extras.getString("quantity"));
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.customer));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextInputEditText editFirstname = findViewById(R.id.firstname);
        TextInputEditText editLastname = findViewById(R.id.lastname);
        TextInputEditText editEmail = findViewById(R.id.email);
        TextInputEditText editAddress = findViewById(R.id.address);
        TextInputEditText editPhone = findViewById(R.id.phone);
        Button btnContinue = (Button) findViewById(R.id.btnContinue);

        if (!orderNew) {
            OrderData dataOldOrder = tableOrder.readOrder(orderId);
            String[] name = dataOldOrder.getOrderCustomerName().split(" ");
            try {
                if (!name[0].isEmpty()) {
                    editFirstname.setText(name[0]);
                }
                if (!name[1].isEmpty()) {
                    editLastname.setText(name[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String email = dataOldOrder.getOrderCustomerEmail();
            if (!email.isEmpty()) {
                editEmail.setText(email);
            }
            String address = dataOldOrder.getOrderCustomerAddress();
            if (!address.isEmpty()) {
                editAddress.setText(address);
            }
            String phone = dataOldOrder.getOrderCustomerPhone();
            if (!phone.isEmpty()) {
                editPhone.setText(phone);
            }
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderData data = new OrderData();
                data.setOrderMenuId(menuId);
                data.setOrderQuantity(quantity);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = sdf.format(new Date());
                data.setOrderDate(date);

                if (editFirstname.getText().toString().isEmpty()) {
                    editFirstname.setError(getString(R.string.empty_message));
                    return;
                }
                if (editLastname.getText().toString().isEmpty()) {
                    editLastname.setError(getString(R.string.empty_message));
                    return;
                }
                if (editEmail.getText().toString().isEmpty()) {
                    editEmail.setError(getString(R.string.empty_message));
                    return;
                }
                if (editAddress.getText().toString().isEmpty()) {
                    editAddress.setError(getString(R.string.empty_message));
                    return;
                }
                if (editPhone.getText().toString().isEmpty()) {
                    editPhone.setError(getString(R.string.empty_message));
                    return;
                }

                data.setOrderCustomerName(editFirstname.getText().toString()
                                            + " "
                                            + editLastname.getText().toString());
                data.setOrderCustomerEmail(editEmail.getText().toString());
                data.setOrderCustomerAddress(editAddress.getText().toString());
                data.setOrderCustomerPhone(editPhone.getText().toString());
                if (orderNew) {
                    orderId = tableOrder.createOrder(data);
                    data.setId(orderId);
                } else {
                    data.setId(orderId);
                    tableOrder.updateOrder(data);
                }

                Intent intent = new Intent(getApplicationContext(), ConfirmOrderActivity.class);
                intent.putExtra("order_new", true);
                intent.putExtra("order_id", orderId);
                ContactActivity.this.startActivity(intent);
            }
        });
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