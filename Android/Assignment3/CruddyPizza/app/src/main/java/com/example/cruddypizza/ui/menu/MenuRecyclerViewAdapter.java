package com.example.cruddypizza.ui.menu;

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
import com.example.cruddypizza.data.SizeData;
import com.example.cruddypizza.data.SizeSQLiteHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {
    ArrayList<MenuData> arrayList;
    Context context;
    SizeSQLiteHelper tableSize;
    String[] arrayStringMenu;

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name, price;

        public MenuViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            arrayStringMenu = context.getResources().getStringArray(R.array.menu_values);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public MenuRecyclerViewAdapter(Context context, ArrayList<MenuData> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        this.tableSize = new SizeSQLiteHelper(context);
    }

    @NotNull
    @Override
    public MenuRecyclerViewAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_menu_nopromoted, parent, false);
        return new MenuRecyclerViewAdapter.MenuViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MenuRecyclerViewAdapter.MenuViewHolder holder, int position) {
        final MenuData data = arrayList.get(position);

        holder.image.setImageResource(data.getMenuImage());
        holder.name.setText(arrayStringMenu[data.getMenuName()]);

        SizeData dataSize = tableSize.readSize(data.getId());
        data.setMenuSize(dataSize.getMenuSize());
        data.setMenuPrice(dataSize.getMenuPrice());

        String strPrefix = context.getString(R.string.starting_from);
        holder.price.setText(strPrefix + data.getMenuPrice());
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void setItems(ArrayList<MenuData> arrayList) {
        this.arrayList = arrayList;
    }

    public Object getItem(int position) {
        return arrayList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
