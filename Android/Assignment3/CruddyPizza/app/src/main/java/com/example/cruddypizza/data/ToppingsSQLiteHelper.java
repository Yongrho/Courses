package com.example.cruddypizza.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ToppingsSQLiteHelper extends SQLiteOpenHelper {
	// database version
	private static final int databaseVersion = 1;
	// database name
	private static final String toppingDBName = "PizzaTopping";
	private static final String toppingTable = "toppings";
	private static final String toppingId = "id";
	private static final String toppingMenuId = "menu_id";
	private static final String toppingName = "topping_name";
	private static final String toppingPrice = "topping_price";

	private static final String[] COLUMNS = { toppingId, toppingMenuId, toppingName, toppingPrice};

	public ToppingsSQLiteHelper(Context context) {
		super(context, toppingDBName, null, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create topping table
		String create_topping_table = "CREATE TABLE toppings ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
															+ "menu_id TEXT, "
															+ "topping_name TEXT, "
															+ "topping_price TEXT )";
		try {
			db.execSQL(create_topping_table);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop toppings table if already exists
		db.execSQL("DROP TABLE IF EXISTS toppings");
		this.onCreate(db);
	}

	public void createTopping(ToppingData data) {
		// get reference of the topping
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be inserted
		ContentValues values = new ContentValues();
		values.put(toppingMenuId, data.getMenuId());
		values.put(toppingName, data.getToppingName());
		values.put(toppingPrice, data.getToppingPrice());

		// insert topping
		db.insert(toppingTable, null, values);

		// close database transaction
		db.close();
	}

	public ToppingData readTopping(long id) {
		// get reference of the topping table
		SQLiteDatabase db = this.getReadableDatabase();

		// get topping query
		Cursor cursor = db.query(toppingTable,
						COLUMNS,
					" id = ?",
						new String[] { String.valueOf(id) },
				null, null, null, null);

		// if results !=null, parse the first one
		if (cursor != null) {
			cursor.moveToFirst();
		}

		ToppingData data = new ToppingData();

		data.setId(Long.parseLong(cursor.getString(0)));
		data.setMenuId(Long.parseLong(cursor.getString(1)));
		data.setToppingName(Integer.parseInt(cursor.getString(2)));
		data.setToppingPrice(Double.parseDouble(cursor.getString(3)));
		return data;
	}

	public ArrayList<ToppingData> getAllToppings(long menu_id) {
		ArrayList<ToppingData> toppings = new ArrayList<ToppingData>();
		Cursor cursor;

		// get reference of the topping
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + toppingTable + " WHERE menu_id = " + menu_id;
		cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				ToppingData data = new ToppingData();

				data.setId(Long.parseLong(cursor.getString(0)));
				data.setMenuId(Long.parseLong(cursor.getString(1)));
				data.setToppingName(Integer.parseInt(cursor.getString(2)));
				data.setToppingPrice(Double.parseDouble(cursor.getString(3)));

				// Add data to toppings
				toppings.add(data);
			} while (cursor.moveToNext());
		}

		db.close();
		return toppings;
	}

	public int updateTopping(ToppingData data) {
		// get reference of the topping
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be updated
		ContentValues values = new ContentValues();
		values.put(toppingMenuId, data.getMenuId());
		values.put(toppingName, data.getToppingName());
		values.put(toppingPrice, data.getToppingPrice());

		// update
		int i = db.update(toppingTable,
						values,
				toppingId + " = ?",
						new String[] { String.valueOf(data.getId()) });

		db.close();
		return i;
	}

	// Delete the topping
	public void deleteTopping(long id) {
		// get reference of the topping
		SQLiteDatabase db = this.getWritableDatabase();

		// delete the topping
		db.delete(toppingTable,
				toppingId + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}
}