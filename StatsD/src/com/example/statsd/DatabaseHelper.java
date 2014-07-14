package com.example.statsd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	//database information
	public static final String DB_NAME = "statistics";
	public static final int DB_VERSION = 1;
	
	
	
	//table names
	private static final String PLAYER_TABLE = "player";
	private static final String MATCH_TABLE = "match";
	private static final String ININGS_TABLE = "inings";
					
	//common colums
	private static final String ID_FIELD = "id";
	
	
	//player table columns
	private static final String PLAYER_ID = "player_id";
	private static final String PLAYER_NAME = "player_name";
	private static final String PLAYER_ADDRESS = "player_address";
	private static final String PLAYER_INFO = "player_info";
	private static final String PLAYER_RUN = "player_run";
	private static final String PLAYER_WICKET = "player_wicket";
	private static final String PLAYER_BALLS = "player_ball";
	private static final String PLAYER_AVERAGE = "player_average";
	private static final String PLAYER_STRATE = "player_st_rate";
	
	//match table columns
	private static final String MATCH_ID = "match_id";
	private static final String MATCH_OVER = "match_over";
	private static final String MATCH_DATE = "match_date";
	
	//inings table column
	private static final String ININGS_ID = "inings_id";
	private static final String ININGS_BALL = "inings_ball";
	private static final String ININGS_RUN = "inings_run";
	private static final String ININGS_WICKET = "inings_wicket";
	private static final String ININGS_DATE = "inings_date";
	

	
	//SQL
	private static final String PLAYER_TABLE_SQL = "CREATE TABLE "
			+ PLAYER_TABLE + " ( " 
			+ PLAYER_ID + " INTEGER PRIMARY KEY, "
			+ PLAYER_NAME + " TEXT, " 
			+ PLAYER_ADDRESS + " TEXT, " 
			+ PLAYER_INFO + " TEXT, "
			+ PLAYER_RUN + " INTEGER, "
			+ PLAYER_WICKET + " INTEGER, "
			+ PLAYER_BALLS + " INTEGER, "
			+ PLAYER_AVERAGE + " REAL, "
			+ PLAYER_STRATE + " REAL )" ;
	
	
	private static final String MATCH_TABLE_SQL = "CREATE TABLE "
			+ MATCH_TABLE + " ( " 
			+ MATCH_ID + " INTEGER PRIMARY KEY, "
			+ MATCH_OVER + " INTEGER, "
			+ MATCH_DATE + " TEXT )" ;
	
	private static final String ININGS_TABLE_SQL = "CREATE TABLE "
			+ ININGS_TABLE + " ( "
			+ ININGS_ID + " INTEGER PRIMARY KEY, "
			+ ININGS_BALL + " INTEGER, "
			+ ININGS_RUN + " INTEGER, "
			+ ININGS_WICKET + " INTEGER, "
			+ ININGS_DATE + " TEXT )" ;
			
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.d("TAGG" ,PLAYER_TABLE_SQL);
		Log.d("TAGG" ,MATCH_TABLE_SQL);
		Log.d("TAGG" ,ININGS_TABLE_SQL);
		
		db.execSQL(PLAYER_TABLE_SQL);
		db.execSQL(MATCH_TABLE_SQL);
		db.execSQL(ININGS_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + MATCH_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + ININGS_TABLE);
		
		onCreate(db);
	}
	
	public long insertPlayer(Player plr){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		Log.d("TAGX", PLAYER_TABLE_SQL);
		
		values.put(PLAYER_NAME, plr.Name);			
		values.put(PLAYER_ADDRESS, plr.Address);	
		values.put(PLAYER_INFO,plr.Info);			
		values.put(PLAYER_RUN,plr.run);				
		values.put(PLAYER_WICKET,plr.wickets);		
		values.put(PLAYER_BALLS,plr.balls);			
		values.put(PLAYER_AVERAGE,plr.average);		
		values.put(PLAYER_STRATE,plr.stRate);		
		
		long inserted = db.insert(PLAYER_TABLE, null, values);
		
		db.close();
		return inserted;
	}
	
	public Player readSinglePlyer(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + ID_FIELD + " = " + id;
		
		Log.e("Data base ", selectQuery);
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		Player plr = new Player();
		if(cursor != null)
			cursor.moveToFirst();
		
		plr.run = cursor.getInt(cursor.getColumnIndex(PLAYER_RUN));
		plr.wickets = cursor.getInt(cursor.getColumnIndex(PLAYER_WICKET));
		plr.balls = cursor.getInt(cursor.getColumnIndex(PLAYER_BALLS));
		plr.average = cursor.getDouble(cursor.getColumnIndex(PLAYER_AVERAGE));
		plr.stRate = cursor.getDouble(cursor.getColumnIndex(PLAYER_STRATE));
		plr.Name = cursor.getString(cursor.getColumnIndex(PLAYER_NAME));
		plr.Address = cursor.getString(cursor.getColumnIndex(PLAYER_ADDRESS));
		plr.Info = cursor.getString(cursor.getColumnIndex(PLAYER_INFO));
		
		return plr;
	}
	
	public List<String> readAllNames(){
		SQLiteDatabase db = this.getReadableDatabase();
		List<String> names = new ArrayList<String>();
		
		String selectQuery = "SELECT " + PLAYER_NAME + " FROM " + PLAYER_TABLE;
		Cursor c =db.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()) {
            do {
                names.add(c.getString( c.getColumnIndex(PLAYER_NAME)));
            } while (c.moveToNext());
        }
         
		return names;
	}
	
	public ArrayList<Player> readAllPlayers(){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		String selectQuery = "SELECT * FROM " + PLAYER_TABLE;
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()){
			do {
				Player plr = new Player();
				
				plr.setName(c.getString(c.getColumnIndex(PLAYER_NAME)));
				plr.setAddress(c.getString(c.getColumnIndex(PLAYER_ADDRESS)));
				plr.setInfo(c.getString(c.getColumnIndex(PLAYER_INFO)));
				
				plr.setId(c.getInt(c.getColumnIndex(PLAYER_ID)));
				plr.setRun(c.getInt(c.getColumnIndex(PLAYER_RUN)));
				plr.setWickets(c.getInt(c.getColumnIndex(PLAYER_WICKET)));
				plr.setBalls(c.getInt(c.getColumnIndex(PLAYER_BALLS)));
				
				plr.setAverage(c.getDouble(c.getColumnIndex(PLAYER_AVERAGE)));
				plr.setStRate(c.getDouble(c.getColumnIndex(PLAYER_STRATE)));
				
				playerList.add(plr);
			} while(c.moveToNext());
		}
		return playerList;
	}
	
	
	
	

}
