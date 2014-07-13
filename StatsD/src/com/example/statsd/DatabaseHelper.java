package com.example.statsd;

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
	private static final String PLAYER_NAME = "playerName";
	private static final String PLAYER_ADDRESS = "PlayerAddress";
	private static final String PLAYER_INFO = "PlayerInfo";
	private static final String PLAYER_RUN = "PlayerRun";
	private static final String PLAYER_WICKET = "PlayerWicket";
	private static final String PLAYER_BALLS = "PlayerBall";
	private static final String PLAYER_AVERAGE = "PlayerAverage";
	private static final String PLAYER_STRATE = "PlayerStRate";
	
	//match table columns
	private static final String MATCH_ID = "match_id";
	private static final String MATCH_OVER = "match_over";
	private static final String MATCH_DATE = "match_date";
	
	//inings table column
	private static final String ININGS_ID = "inings_id";
	private static final String ININGS_RUN = "inings_run";
	private static final String ININGS_BALL = "inings_ball";
	private static final String ININGS_WICKET = "inings_wicket";
	private static final String ININGS_DATE = "inings_date";
	

	
	//SQL
	private static final String PLAYER_TABLE_SQL = "CREATE TABLE "
			+ PLAYER_TABLE + " ( " 
			+ PLAYER_ID + " INT PRIMARY KEY, "
			+ PLAYER_NAME + " TEXT, " 
			+ PLAYER_ADDRESS + " TEXT, " 
			+ PLAYER_INFO + " TEXT, "
			+ PLAYER_RUN + " INT, "
			+ PLAYER_WICKET + " INT, "
			+ PLAYER_BALLS + " INT, "
			+ PLAYER_AVERAGE + " REAL, "
			+ PLAYER_STRATE + " REAL )" ;
	
	
	private static final String MATCH_TABLE_SQL = "CREATE TABLE "
			+ MATCH_TABLE + " (" + MATCH_ID + " INTEGER PRIMARY KEY, "
			+ MATCH_OVER + " NUMBER, "
			+ MATCH_DATE + " TEXT )" ;
	
	
			
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("TAGG" ,PLAYER_TABLE_SQL);
		db.execSQL(PLAYER_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public long insertPlayer(Player plr){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		Log.d("TAGX", PLAYER_TABLE_SQL);
		
		values.put(PLAYER_NAME, plr.Name);			Log.d("TAGA","Came here");
		values.put(PLAYER_ADDRESS, plr.Address);	Log.d("TAGB","Came here");
		values.put(PLAYER_INFO,plr.Info);			Log.d("TAGC","Came here");
		//values.put(PLAYER_RUN,plr.run);				Log.d("TAGD","Came here");
		values.put(PLAYER_WICKET,plr.wickets);		Log.d("TAGE","Came here");
		values.put(PLAYER_BALLS,plr.balls);			Log.d("TAGF","Came here");
		values.put(PLAYER_AVERAGE,plr.average);		Log.d("TAGG","Came here");
		//values.put(PLAYER_STRATE,plr.stRate);		Log.d("TAGH","Came here");
		
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
		
		/*plr.Name = cursor.getString(cursor.getColumnIndex(NAME_FIELD));
		plr.Address = cursor.getString(cursor.getColumnIndex(ADDRESS_FIELD));
		plr.Info = cursor.getString(cursor.getColumnIndex(INFO_FIELD));
		plr.average = cursor.getDouble(cursor.getColumnIndex(columnName))
		*/
		
		return plr;
	}
	
	
	
	

}
