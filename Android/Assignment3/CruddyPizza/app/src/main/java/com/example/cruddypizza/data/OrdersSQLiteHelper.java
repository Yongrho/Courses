package com.example.cruddypizza.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class OrdersSQLiteHelper extends SQLiteOpenHelper {
	// database version
	private static final int databaseVersion = 1;
	// database name
	private static final String pizzaOrderDBName = "PizzaOrder";

	private static final String orderTable = "orders";
	private static final String orderId = "id";
	private static final String orderMenuId = "menu_id";
	private static final String orderDate = "datetime";
	private static final String orderQuantity = "quantity";
	private static final String orderTotalPrice = "total_price";
	private static final String orderCustomerName = "customer_name";
	private static final String orderCustomerEmail = "customer_email";
	private static final String orderCustomerAddress = "customer_address";
	private static final String orderCustomerPhone = "customer_phone";

	private static final String[] COLUMNS = { orderId, orderMenuId,
											orderDate, orderQuantity, orderTotalPrice,
											orderCustomerName, orderCustomerEmail,
											orderCustomerAddress, orderCustomerPhone };

	public OrdersSQLiteHelper(Context context) {
		super(context, pizzaOrderDBName, null, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create orders table
		String create_order_table = "CREATE TABLE orders ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
															+ "menu_id TEXT, "
															+ "datetime TEXT, "
															+ "quantity TEXT, "
															+ "total_price TEXT, "
															+ "customer_name TEXT, "
															+ "customer_email TEXT, "
															+ "customer_address TEXT, "
															+ "customer_phone TEXT)";
		try {
			db.execSQL(create_order_table);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop orders table if already exists
		db.execSQL("DROP TABLE IF EXISTS orders");
		this.onCreate(db);
	}

	public long createOrder(OrderData data) {
		// get reference of the order
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be inserted
		ContentValues values = new ContentValues();
		values.put(orderMenuId, data.getOrderMenuId());
		values.put(orderDate, data.getOrderDate());
		values.put(orderQuantity, data.getOrderQuantity());
		values.put(orderTotalPrice, data.getOrderTotalPrice());
		values.put(orderCustomerName, data.getOrderCustomerName());
		values.put(orderCustomerEmail, data.getOrderCustomerEmail());
		values.put(orderCustomerAddress, data.getOrderCustomerAddress());
		values.put(orderCustomerPhone, data.getOrderCustomerPhone());

		// insert order
		long id = db.insert(orderTable, null, values);

		// close database transaction
		db.close();
		return id;
	}

	public OrderData readOrder(long id) {
		// get reference of the database
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(orderTable,
				COLUMNS, " id = ?", new String[] { String.valueOf(id) },
				null, null, null, null);

		// if results !=null, parse the first one
		if (cursor != null) {
			cursor.moveToFirst();
		}

		OrderData data = new OrderData();
		data.setId(Long.parseLong(cursor.getString(0)));
		data.setOrderMenuId(Long.parseLong(cursor.getString(1)));
		data.setOrderDate(cursor.getString(2));
		data.setOrderQuantity(Integer.parseInt(cursor.getString(3)));
		data.setOrderTotalPrice(Double.parseDouble(cursor.getString(4)));
		data.setOrderCustomerName(cursor.getString(5));
		data.setOrderCustomerEmail(cursor.getString(6));
		data.setOrderCustomerAddress(cursor.getString(7));
		data.setOrderCustomerPhone(cursor.getString(8));
		return data;
	}

	public ArrayList<OrderData> getAllOrders() {
		ArrayList<OrderData> orders = new ArrayList<OrderData>();
		Cursor cursor;

		// get reference of the order database
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT * FROM " + orderTable + " ORDER BY id DESC";
		cursor = db.rawQuery(query, null);

		// parse all results
		if (cursor.moveToFirst()) {
			do {
				OrderData data = new OrderData();
				data.setId(Long.parseLong(cursor.getString(0)));
				data.setOrderMenuId(Long.parseLong(cursor.getString(1)));
				data.setOrderDate(cursor.getString(2));
				data.setOrderQuantity(Integer.parseInt(cursor.getString(3)));
				data.setOrderTotalPrice(Double.parseDouble(cursor.getString(4)));
				data.setOrderCustomerName(cursor.getString(5));
				data.setOrderCustomerEmail(cursor.getString(6));
				data.setOrderCustomerAddress(cursor.getString(7));
				data.setOrderCustomerPhone(cursor.getString(8));

//				Log.v("ALL", "data: " + data.toString());

				// Add order to orders
				orders.add(data);
			} while (cursor.moveToNext());
		}

		db.close();
		return orders;
	}

	public int updateOrder(OrderData data) {
		// get reference of the database
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be updated
		ContentValues values = new ContentValues();
		values.put(orderMenuId, data.getOrderMenuId());
		values.put(orderDate, data.getOrderDate());
		values.put(orderQuantity, data.getOrderQuantity());
		values.put(orderTotalPrice, data.getOrderTotalPrice());
		values.put(orderCustomerName, data.getOrderCustomerName());
		values.put(orderCustomerEmail, data.getOrderCustomerEmail());
		values.put(orderCustomerAddress, data.getOrderCustomerAddress());
		values.put(orderCustomerPhone, data.getOrderCustomerPhone());

		// update
		int i = db.update(orderTable, values, orderId + " = ?",
						new String[] { String.valueOf(data.getId()) });

		db.close();
		return i;
	}

	// Delete the order
	public void deleteOrder(OrderData data) {
		// get reference of the database
		SQLiteDatabase db = this.getWritableDatabase();

		// delete the order
		db.delete(orderTable, orderId + " = ?", new String[] { String.valueOf(data.getId()) });
		db.close();
	}
}
