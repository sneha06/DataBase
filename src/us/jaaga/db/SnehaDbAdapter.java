package us.jaaga.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SnehaDbAdapter  {
	 
	snehahelper helper;
	public SnehaDbAdapter(Context context) {
		helper = new snehahelper(context);
	}
	
	public long insertData(String name, String password){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValue = new ContentValues();
		contentValue.put(snehahelper.NAME, name);
		contentValue.put(snehahelper.PASSWORD, password);
		long id = db.insert(snehahelper.TABLE_NAME, null, contentValue);
		return id;
	
	}
	
	public String getAllData(){
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns = {helper.UID,helper.NAME,helper.PASSWORD};
		Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null,null,null);
		StringBuffer buffer = new StringBuffer();
		
		while (cursor.moveToNext()){
			
		int index1 = 	cursor.getColumnIndex(helper.UID);
		int index2 = cursor.getColumnIndex(helper.NAME);
		int index3 = cursor.getColumnIndex(helper.PASSWORD);
		
		int cid = cursor.getInt(index1);
		String name = cursor.getString(index2);
		String password = cursor.getString(index3);
		buffer.append(cid+" "+name+" "+password+" \n");
			
		}
		return buffer.toString();
	}
	
	
	static class snehahelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "snehadatabase";
		private static final String TABLE_NAME = "snehatable";
		private static final int DATABASE_VERSION = 1;
		private static final String UID = "_id";
		private static final String NAME = "Name";
		private static final String PASSWORD = "password";
		
		private static final String CREATE= "CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255));";

		private static final String DROP_TABLE = "DROP TABLE IF EXIST"+TABLE_NAME;
		
		private Context context;
		
		
		
		public snehahelper(Context context){
			super(context,DATABASE_NAME, null,DATABASE_VERSION);
			this.context = context;
			Message.message(context, "Contructor called");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(CREATE);
				Message.message(context, "On create called");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Message.message(context, ""+e);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DROP_TABLE);
				onCreate(db);
				Message.message(context, "On upgrade called");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Message.message(context, ""+e);
			}
		}

	}
}
