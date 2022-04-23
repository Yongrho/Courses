package com.example.cruddypizza.ui.orders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cruddypizza.R;
import com.example.cruddypizza.data.MenuData;
import com.example.cruddypizza.data.MenuSQLiteHelper;
import com.example.cruddypizza.data.OrderData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.OrderViewHolder> {
    ArrayList<OrderData> arrayList;
    Context context;
    MenuSQLiteHelper tableMenu;
    String[] arrayStringMenu;

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name, price, date;

        public OrderViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
            arrayStringMenu = context.getResources().getStringArray(R.array.menu_values);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public OrdersRecyclerViewAdapter(Context context, ArrayList<OrderData> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        this.tableMenu = new MenuSQLiteHelper(context);
    }

    @NotNull
    @Override
    public OrdersRecyclerViewAdapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_menu_order, parent, false);
        return new OrdersRecyclerViewAdapter.OrderViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(OrdersRecyclerViewAdapter.OrderViewHolder holder, int position) {
        final OrderData data = arrayList.get(position);
        MenuData dataMenu = tableMenu.readMenu(data.getOrderMenuId());

        holder.image.setImageResource(dataMenu.getMenuImage());
        holder.name.setText(arrayStringMenu[dataMenu.getMenuName()]);
        holder.date.setText(data.getOrderDate());

        String strPrefix = context.getString(R.string.dollar_sign);
        holder.price.setText(strPrefix + data.getOrderTotalPrice());
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void setItems(ArrayList<OrderData> arrayList) {
        this.arrayList = arrayList;
    }

    public Object getItem(int position) {
        return arrayList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
