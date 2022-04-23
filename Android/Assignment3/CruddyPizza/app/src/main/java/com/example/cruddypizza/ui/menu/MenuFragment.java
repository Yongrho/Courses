package com.example.cruddypizza.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruddypizza.BuildActivity;
import com.example.cruddypizza.ContactActivity;
import com.example.cruddypizza.R;
import com.example.cruddypizza.data.MenuData;
import com.example.cruddypizza.data.MenuSQLiteHelper;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    private static final String TAG = "MenuFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // open the database of the application context
        MenuSQLiteHelper tableMenu = new MenuSQLiteHelper(getContext());
        ArrayList<MenuData> menuPromoted = tableMenu.getMenuAll(0);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.sectioned_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MenuRecyclerViewAdapter menuAdapter = new MenuRecyclerViewAdapter(getContext(), menuPromoted);
        recyclerView.setAdapter(menuAdapter);

        menuAdapter.setOnItemClickListener(new MenuRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v,  int position) {
                MenuData data = (MenuData) menuAdapter.getItem(position);
                createBuildMenu(data.getId());
            }
        });

        Button buttonBuild = (Button) root.findViewById(R.id.btnBuild);
        buttonBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBuildMenu(0);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void createBuildMenu(long menuId) {
        Intent intent = new Intent(getContext(), BuildActivity.class);
        intent.putExtra("order_new", true);
        intent.putExtra("order_id", 0L);
        intent.putExtra("menu_id", menuId);
        MenuFragment.this.startActivity(intent);
    }
}