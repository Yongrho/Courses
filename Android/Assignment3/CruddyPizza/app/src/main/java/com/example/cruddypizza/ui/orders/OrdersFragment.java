package com.example.cruddypizza.ui.orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruddypizza.ConfirmOrderActivity;
import com.example.cruddypizza.R;
import com.example.cruddypizza.data.OrderData;
import com.example.cruddypizza.data.OrdersSQLiteHelper;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {
    private static final String TAG = "OrdersFragment";

    OrdersSQLiteHelper tableOrder;
    RecyclerView recyclerView;
    OrdersRecyclerViewAdapter orderAdapter;
    ArrayList<OrderData> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        // open the database of the application context
        tableOrder = new OrdersSQLiteHelper(getContext());
        data = tableOrder.getAllOrders();

        recyclerView = root.findViewById(R.id.sectioned_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        orderAdapter = new OrdersRecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(orderAdapter);

        orderAdapter.setOnItemClickListener(new OrdersRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v,  int position) {
                OrderData data = (OrderData) orderAdapter.getItem(position);
                Intent intent = new Intent(getContext(), ConfirmOrderActivity.class);
                intent.putExtra("order_new", false);
                intent.putExtra("order_id", data.getId());
                OrdersFragment.this.startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();

        data = tableOrder.getAllOrders();
        if (data.size() > 0) {
            orderAdapter.setItems(data);
            recyclerView.setAdapter(orderAdapter);
        } else {
            recyclerView.setAdapter(null);
        }
    }
}