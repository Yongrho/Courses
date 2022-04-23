package com.example.cruddypizza.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SizeSQLiteHelper extends SQLiteOpenHelper {
	// database version
	private static final int databaseVersion = 1;
	// database name
	private static final String sizeDBName = "PizzaSize";
	private static final String sizeTable = "size";
	private static final String sizeId = "id";
	private static final String menuId = "menu_id";
	private static final String menuSize = "menu_size";
	private static final String menuPrice = "menu_price";

	private static final String[] COLUMNS = { sizeId, menuId, menuSize, menuPrice};

	public SizeSQLiteHelper(Context context) {
		super(context, sizeDBName, null, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create size table
		String create_size_table = "CREATE TABLE size ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
															+ "menu_id TEXT, "
															+ "menu_size TEXT, "
															+ "menu_price TEXT )";
		try {
			db.execSQL(create_size_table);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop toppings table if already exists
		db.execSQL("DROP TABLE IF EXISTS size");
		this.onCreate(db);
	}

	public void createSize(SizeData data) {
		// get reference of the size
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be inserted
		ContentValues values = new ContentValues();
		values.put(menuId, data.getMenuId());
		values.put(menuSize, data.getMenuSize());
		values.put(menuPrice, data.getMenuPrice());

		// insert size
		db.insert(sizeTable, null, values);

		// close database transaction
		db.close();
	}

	public SizeData readSize(long id) {
		// get reference of the size table
		SQLiteDatabase db = this.getReadableDatabase();

		// get size query
		Cursor cursor = db.query(sizeTable,
						COLUMNS,
					"menu_id = ?",
						new String[] { String.valueOf(id) },
				null, null, null, null);

		// if results !=null, parse the first one
		if (cursor != null) {
			cursor.moveToFirst();
		}

		SizeData data = new SizeData();

		data.setId(Long.parseLong(cursor.getString(0)));
		data.setMenuId(Long.parseLong(cursor.getString(1)));
		data.setMenuSize(Integer.parseInt(cursor.getString(2)));
		data.setMenuPrice(Double.parseDouble(cursor.getString(3)));
		return data;
	}

	public ArrayList<SizeData> getAllSize(long menu_id) {
		ArrayList<SizeData> sizeAll = new ArrayList<SizeData>();
		Cursor cursor;

		// get reference of the size
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + sizeTable + " WHERE menu_id = " + menu_id;
		cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				SizeData data = new SizeData();

				data.setId(Long.parseLong(cursor.getString(0)));
				data.setMenuId(Long.parseLong(cursor.getString(1)));
				data.setMenuSize(Integer.parseInt(cursor.getString(2)));
				data.setMenuPrice(Double.parseDouble(cursor.getString(3)));

				// Add size to events
				sizeAll.add(data);
			} while (cursor.moveToNext());
		}

		db.close();
		return sizeAll;
	}

	public int updateSize(SizeData data) {
		// get reference of the size
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be updated
		ContentValues values = new ContentValues();
		values.put(menuId, data.getMenuId());
		values.put(menuSize, data.getMenuSize());
		values.put(menuPrice, data.getMenuPrice());

		// update
		int i = db.update(sizeTable,
						values,
				sizeId + " = ?",
						new String[] { String.valueOf(data.getId()) });

		db.close();
		return i;
	}

	// Delete the size
	public void deleteSize(SizeData data) {
		// get reference of the size
		SQLiteDatabase db = this.getWritableDatabase();

		// delete the size
		db.delete(sizeTable,
				sizeId + " = ?",
				new String[] { String.valueOf(data.getId()) });
		db.close();
	}
}
