package com.example.cruddypizza.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MenuSQLiteHelper extends SQLiteOpenHelper {
	// database version
	private static final int databaseVersion = 1;
	// database name
	private static final String menuDBName = "PizzaMenu";

	private static final String menuTable = "menu";
	private static final String menuId = "id";
	private static final String menuName = "name";
	private static final String menuImage = "image";
	private static final String menuSize = "size";
	private static final String menuPrice = "price";
	private static final String menuPromoted = "promoted";

	private static final String[] COLUMNS = { menuId, menuName, menuImage,
											menuSize, menuPrice, menuPromoted};

	public MenuSQLiteHelper(Context context) {
		super(context, menuDBName, null, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create menu table
		String create_menu_table = "CREATE TABLE menu ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
															+ "name TEXT, "
															+ "image TEXT, "
															+ "size TEXT, "
															+ "price TEXT, "
															+ "promoted TEXT)";
		try {
			db.execSQL(create_menu_table);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop menu table if already exists
		db.execSQL("DROP TABLE IF EXISTS menu");
		this.onCreate(db);
	}

	public long createMenu(MenuData data) {
		// get reference of the order
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be inserted
		ContentValues values = new ContentValues();
		values.put(menuName, data.getMenuName());
		values.put(menuImage, data.getMenuImage());
		values.put(menuSize, data.getMenuSize());
		values.put(menuPrice, data.getMenuPrice());
		values.put(menuPromoted, data.getMenuPromoted());

		// insert order
		long id = db.insert(menuTable, null, values);
//		data.setId(id);

		// close database transaction
		db.close();
		return id;
	}

	public MenuData readMenu(long id) {
		// get reference of the database
		SQLiteDatabase db = this.getReadableDatabase();

		// get b query
		Cursor cursor = db.query(menuTable,
				COLUMNS, " id = ?", new String[] { String.valueOf(id) },
				null, null, null, null);

		// if results !=null, parse the first one
		if (cursor != null) {
			cursor.moveToFirst();
		}

		MenuData data = new MenuData();

		data.setId(Long.parseLong(cursor.getString(0)));
		data.setMenuName(Integer.parseInt(cursor.getString(1)));
		data.setMenuImage(Integer.parseInt(cursor.getString(2)));
		data.setMenuSize(Integer.parseInt(cursor.getString(3)));
		data.setMenuPrice(Double.parseDouble(cursor.getString(4)));
		data.setMenuPromoted(Integer.parseInt(cursor.getString(5)));
		return data;
	}

	public ArrayList<MenuData> getAllMenu() {
		ArrayList<MenuData> menu = new ArrayList<MenuData>();
		Cursor cursor;

		// get reference of the order database
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + menuTable;
		cursor = db.rawQuery(query, null);

		// parse all results
		if (cursor.moveToFirst()) {
			do {
				MenuData data = new MenuData();

				data.setId(Long.parseLong(cursor.getString(0)));
				data.setMenuName(Integer.parseInt(cursor.getString(1)));
				data.setMenuImage(Integer.parseInt(cursor.getString(2)));
				data.setMenuSize(Integer.parseInt(cursor.getString(3)));
				data.setMenuPrice(Double.parseDouble(cursor.getString(4)));
				data.setMenuPromoted(Integer.parseInt(cursor.getString(5)));

//				Log.v("ALL", "data: " + data.toString());

				// Add data to menu
				menu.add(data);
			} while (cursor.moveToNext());
		}

		db.close();
		return menu;
	}

	public ArrayList<MenuData> getMenuAll(int promoted) {
		ArrayList<MenuData> menu = new ArrayList<MenuData>();
		Cursor cursor;

		// get reference of the order database
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + menuTable + " WHERE promoted = " + String.valueOf(promoted);
		cursor = db.rawQuery(query, null);

		// parse all results
		if (cursor.moveToFirst()) {
			do {
				MenuData data = new MenuData();

				data.setId(Long.parseLong(cursor.getString(0)));
				data.setMenuName(Integer.parseInt(cursor.getString(1)));
				data.setMenuImage(Integer.parseInt(cursor.getString(2)));
				data.setMenuSize(Integer.parseInt(cursor.getString(3)));
				data.setMenuPrice(Double.parseDouble(cursor.getString(4)));
				data.setMenuPromoted(Integer.parseInt(cursor.getString(5)));

				Log.v("ALL", "data: " + data.toString());

				// Add data to menu
				menu.add(data);
			} while (cursor.moveToNext());
		}

		db.close();
		return menu;
	}

	public int updateMenu(MenuData data) {
		// get reference of the database
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be updated
		ContentValues values = new ContentValues();
		values.put(menuName, data.getMenuName());
		values.put(menuImage, data.getMenuImage());
		values.put(menuSize, data.getMenuSize());
		values.put(menuPrice, data.getMenuPrice());
		values.put(menuPromoted, data.getMenuPromoted());

		// update
		int i = db.update(menuTable, values, menuId + " = ?",
						new String[] { String.valueOf(data.getId()) });

		db.close();
		return i;
	}

	// Delete the menu
	public void deleteMenu(MenuData data) {
		// get reference of the database
		SQLiteDatabase db = this.getWritableDatabase();

		// delete the menu
		db.delete(menuTable, menuId + " = ?", new String[] { String.valueOf(data.getId()) });
		db.close();
	}
}
