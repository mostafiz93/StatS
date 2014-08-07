package com.example.statsd;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
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
	public static final int DB_VERSION = 10;
	
	
	
	//table names
	private static final String PLAYER_TABLE = "player";
	private static final String MATCH_TABLE = "match";
	private static final String ININGS_TABLE = "inings";
	private static final String ININGS_MATCH_TABLE = "inings_match";
	private static final String ININGS_PLAYER_TABLE = "inings_player";
	private static final String PLAYER_MATCH_TABLE = "player_match";
	
	//common table columns
	private static final String ID = "_id";
	
	//player table columns
	private static final String PLAYER_NAME = "player_name";
	private static final String PLAYER_ADDRESS = "player_address";
	private static final String PLAYER_INFO = "player_info";
	private static final String PLAYER_RUN = "player_run";
	private static final String PLAYER_WICKET = "player_wicket";
	private static final String PLAYER_BALLS = "player_ball";
	private static final String PLAYER_TOTAL_MATCH = "player_total_match";
	private static final String PLAYER_AVERAGE = "player_average";
	private static final String PLAYER_STRATE = "player_st_rate";
	
	//match table columns
	private static final String MATCH_OVER = "match_over";
	private static final String MATCH_DATE = "match_date";
	private static final String MATCH_TIME = "match_time";
	
	//inings table column
	private static final String ININGS_BALL = "inings_ball";
	private static final String ININGS_RUN = "inings_run";
	private static final String ININGS_WICKET = "inings_wicket";
	private static final String ININGS_DATE = "inings_date";
	
	//relational tables' column
	private static final String ININGS_ID = "inings_id";
	private static final String MATCH_ID = "match_id";
	private static final String PLAYER_ID = "player_id";

	
	//SQL
	private static final String PLAYER_TABLE_SQL = "CREATE TABLE "
			+ PLAYER_TABLE + " ( " 
			+ ID + " INTEGER PRIMARY KEY, "
			+ PLAYER_NAME + " TEXT, " 
			+ PLAYER_ADDRESS + " TEXT, " 
			+ PLAYER_INFO + " TEXT, "
			+ PLAYER_RUN + " INTEGER, "
			+ PLAYER_WICKET + " INTEGER, "
			+ PLAYER_BALLS + " INTEGER, "
			+ PLAYER_TOTAL_MATCH + " INTEGER, "
			+ PLAYER_AVERAGE + " REAL, "
			+ PLAYER_STRATE + " REAL )" ;
	
	
	private static final String MATCH_TABLE_SQL = "CREATE TABLE "
			+ MATCH_TABLE + " ( " 
			+ ID + " INTEGER PRIMARY KEY, "
			+ MATCH_OVER + " INTEGER, "
			+ MATCH_DATE + " TEXT , "
			+ MATCH_TIME + " TEXT )" ;
	
	private static final String ININGS_TABLE_SQL = "CREATE TABLE "
			+ ININGS_TABLE + " ( "
			+ ID + " INTEGER PRIMARY KEY, "
			+ ININGS_BALL + " INTEGER, "
			+ ININGS_RUN + " INTEGER, "
			+ ININGS_WICKET + " INTEGER, "
			+ ININGS_DATE + " TEXT )" ;
			
	private static final String ININGS_MATCH_TABLE_SQL = "CREATE TABLE "
			+ ININGS_MATCH_TABLE + " ( "
			+ ID + " INTEGER PRIMARY KEY, "
			+ ININGS_ID + " INTEGER, "
			+ MATCH_ID + " INTEGER )";
	
	private static final String ININGS_PLAYER_TABLE_SQL = "CREATE TABLE "
			+ ININGS_PLAYER_TABLE + " ( "
			+ ID + " INTEGER PRIMARY KEY, "
			+ ININGS_ID + " INTEGER, "
			+ PLAYER_ID + " INTEGER ) ";
	
	private static final String PLAYER_MATCH_TABLE_SQL = " CREATE TABLE "
			+ PLAYER_MATCH_TABLE + " ( "
			+ ID + " INTEGER PRIMARY KEY, "
			+ PLAYER_ID + " INTEGER, "
			+ MATCH_ID + " INTEGER ) ";
	
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.d("TAGG" ,PLAYER_TABLE_SQL);
		Log.d("TAGG" ,MATCH_TABLE_SQL);
		Log.d("TAGG" ,ININGS_TABLE_SQL);
		Log.d("tagxxx", ININGS_MATCH_TABLE_SQL);
		Log.d("tagxxx",ININGS_PLAYER_TABLE_SQL);
		Log.d("tagxxx",PLAYER_MATCH_TABLE_SQL);
		
		
		db.execSQL(PLAYER_TABLE_SQL);
		db.execSQL(MATCH_TABLE_SQL);
		db.execSQL(ININGS_TABLE_SQL);
		db.execSQL(ININGS_MATCH_TABLE_SQL);
		db.execSQL(ININGS_PLAYER_TABLE_SQL);
		db.execSQL(PLAYER_MATCH_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTs " + PLAYER_TABLE);
		db.execSQL("DROP TABLE IF EXISTs  " + MATCH_TABLE);
		db.execSQL("DROP TABLE IF EXISTs  " + ININGS_TABLE);
		db.execSQL("DROP TABLE IF EXISTs  " + ININGS_MATCH_TABLE);
		db.execSQL("DROP TABLE IF EXISTs  " + ININGS_PLAYER_TABLE);
		db.execSQL("DROP TABLE IF EXISTs  " + PLAYER_MATCH_TABLE);
		
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
	

	public int insertInings(Inings in){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		//IntPair p = new IntPair();
		
		v.put(ININGS_BALL, in.balls);
		v.put(ININGS_RUN, in.runs);
		v.put(ININGS_WICKET, in.wickets);
		
		int a = (int)db.insert(ININGS_TABLE, null, v);
		Log.d("tag_insertInings", "insert returned = " + a);
		db.close();
		
		return a;
		
		/*db = this.getReadableDatabase();
		String s = "SELECT * from " + ININGS_TABLE;
		Cursor  c = db.rawQuery(s, null);
		c.moveToLast();
		
		p.b = Integer.parseInt( c.getString( c.getColumnIndex(ININGS_ID)) );
		db.close();
		return p;*/
	}
	
	public long insertIiningsMatch(int inId, int mId){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		
		v.put(ININGS_ID, inId);
		v.put(MATCH_ID, mId);
		
		long inserted = db.insert(ININGS_MATCH_TABLE, null, v);
		
		db.close();
		return inserted;
	}
	
	public long insertIningsPlayer(int inId, int plId){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		
		v.put(ININGS_ID, inId);
		v.put(PLAYER_ID, plId);
		
		long inserted = db.insert(ININGS_PLAYER_TABLE, null, v);
		
		db.close();
		return inserted;
	}
	
	public long insertPlayerMatch(int plId, int mId){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		
		v.put(PLAYER_ID, plId);
		v.put(MATCH_ID, mId);
		
		long inserted = db.insert(PLAYER_MATCH_TABLE, null, v);
		
		db.close();
		return inserted;
	}

	
	
	public Player readSinglePlyer(String playerName){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + 
				PLAYER_NAME + " = " + "\"" + playerName + "\"";
		
		Log.d("tag_readSinglePlayer ", selectQuery);
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		Player plr = new Player();
		if(cursor != null)
			cursor.moveToFirst();
		plr.id = cursor.getInt(cursor.getColumnIndex(ID));
		plr.run = cursor.getInt(cursor.getColumnIndex(PLAYER_RUN));
		plr.wickets = cursor.getInt(cursor.getColumnIndex(PLAYER_WICKET));
		plr.balls = cursor.getInt(cursor.getColumnIndex(PLAYER_BALLS));
		plr.average = cursor.getDouble(cursor.getColumnIndex(PLAYER_AVERAGE));
		plr.stRate = cursor.getDouble(cursor.getColumnIndex(PLAYER_STRATE));
		plr.Name = cursor.getString(cursor.getColumnIndex(PLAYER_NAME));
		plr.Address = cursor.getString(cursor.getColumnIndex(PLAYER_ADDRESS));
		plr.Info = cursor.getString(cursor.getColumnIndex(PLAYER_INFO));
		
		db.close();
		return plr;
	}
	
	public List<String> readAllPlayerNames(){
		SQLiteDatabase db = this.getReadableDatabase();
		List<String> names = new ArrayList<String>();
		
		String selectQuery = "SELECT " + PLAYER_NAME + " FROM " + PLAYER_TABLE;
		Cursor c =db.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()) {
            do {
                names.add(c.getString( c.getColumnIndex(PLAYER_NAME)));
            } while (c.moveToNext());
        }
        
		db.close();
		return names;
	}
	
	public ArrayList<Player> readAllPlayers(){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		String selectQuery = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + ID + " ASC ";
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()){
			do {
				Player plr = new Player();
				
				plr.setName(c.getString(c.getColumnIndex(PLAYER_NAME)));
				plr.setAddress(c.getString(c.getColumnIndex(PLAYER_ADDRESS)));
				plr.setInfo(c.getString(c.getColumnIndex(PLAYER_INFO)));
				
				plr.setId(c.getInt(c.getColumnIndex(ID)));
				plr.setRun(c.getInt(c.getColumnIndex(PLAYER_RUN)));
				plr.setWickets(c.getInt(c.getColumnIndex(PLAYER_WICKET)));
				plr.setBalls(c.getInt(c.getColumnIndex(PLAYER_BALLS)));
				
				plr.setAverage(c.getDouble(c.getColumnIndex(PLAYER_AVERAGE)));
				plr.setStRate(c.getDouble(c.getColumnIndex(PLAYER_STRATE)));
				
				playerList.add(plr);
			} while(c.moveToNext());
		}
		db.close();
		return playerList;
	}
	
	public long insertMatch(Match m){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		
		v.put(MATCH_OVER, m.over);
		v.put(MATCH_DATE, m.matchDate);
		v.put(MATCH_TIME, m.matchTime);
		
		long inserted = db.insert(MATCH_TABLE, null, v);
		db.close();
		return inserted;
	}
	
	public ArrayList<Match> readAllMatches(){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Match> matchList = new ArrayList<Match>();
		
		String selectQuery = "SELECT * FROM " + MATCH_TABLE + " ORDER BY " + 
				ID;
		Cursor c = db.rawQuery(selectQuery, null);
		
		if(c.moveToFirst()){
			do {
				Match m = new Match();
				
				m.setId(c.getInt(c.getColumnIndex(ID)));
				m.setOver(c.getInt(c.getColumnIndex(MATCH_OVER)));
				m.setMatchDate(c.getString(c.getColumnIndex(MATCH_DATE)));
				m.setMatchTime(c.getString(c.getColumnIndex(MATCH_TIME)));
				
				matchList.add(m);
			} while(c.moveToNext());
		}
		db.close();
		return matchList;
	}

	/*public long updatePlayer(int pId, Inings in){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + 
				PLAYER_ID + " = " + pId;
		
		Log.d("tagx ", selectQuery);
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		Player plr = new Player();
		if(cursor != null)
			cursor.moveToFirst();
		
		plr.run = cursor.getInt(cursor.getColumnIndex(PLAYER_RUN)) + in.runs;
		plr.wickets = cursor.getInt(cursor.getColumnIndex(PLAYER_WICKET)) + in.wickets;
		plr.balls = cursor.getInt(cursor.getColumnIndex(PLAYER_BALLS)) + in.balls; 
		plr.totalMatch = cursor.getInt(cursor.getColumnIndex(PLAYER_TOTAL_MATCH));
		plr.average = plr.run/plr.totalMatch;
		plr.stRate = plr.run/plr.balls*100;
		plr.Name = cursor.getString(cursor.getColumnIndex(PLAYER_NAME));
		plr.Address = cursor.getString(cursor.getColumnIndex(PLAYER_ADDRESS));
		plr.Info = cursor.getString(cursor.getColumnIndex(PLAYER_INFO));
		
		db.close();
		db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(PLAYER_RUN,plr.run);				
		values.put(PLAYER_WICKET,plr.wickets);		
		values.put(PLAYER_BALLS,plr.balls);			
		values.put(PLAYER_AVERAGE,plr.average);		
		values.put(PLAYER_STRATE,plr.stRate);		
		
		long updated = db.update(PLAYER_TABLE, values, PLAYER_ID + " = " + plr.id, null);
		return updated;
		
	}*/
	
	
	public int readPlayerIdWithName(String plrName){
		SQLiteDatabase db = this.getReadableDatabase();
		String s = "SELECT " + ID + " FROM " + PLAYER_TABLE + " WHERE "
				+ PLAYER_NAME + " = " + "\"" + plrName + "\"";
		Log.d("tag_PleyerIdWithName", "sql = "+ s);
		Cursor c = db.rawQuery(s, null);
		
		Log.d("tag_PleyerIdWithName", "total row in cursor  = "+c.getCount());
		if(c != null) 
			c.moveToFirst();
		else return -1;
		
		int re = c.getInt(c.getColumnIndex(ID));
		
		Log.d("tag_PleyerIdWithName", "value of re  = "+re);
		db.close();
		return re;
	}

	public long updatePlayer(int pId, Inings in){
		SQLiteDatabase db = this.getWritableDatabase();
		String s = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + ID + " = " + pId;
		
		Cursor c = db.rawQuery(s, null);
		
		Log.d("tag_update_player", ""+c.getCount());
		
		if(c != null)
			c.moveToFirst();
		
		Player p = new Player();
		p.run = c.getInt(c.getColumnIndex(PLAYER_RUN)) + in.runs;
		p.balls = c.getInt(c.getColumnIndex(PLAYER_BALLS)) + in.balls;
		p.wickets = c.getInt(c.getColumnIndex(PLAYER_WICKET)) + in.wickets;
		p.totalMatch = c.getInt(c.getColumnIndex(PLAYER_TOTAL_MATCH))+1;
		p.average = 1.0*p.run/p.totalMatch;
		p.stRate = 1.0*p.run/p.balls*100;
		
		ContentValues v = new ContentValues();
		
		v.put(PLAYER_RUN, p.run);
		v.put(PLAYER_BALLS, p.balls);
		v.put(PLAYER_WICKET, p.wickets);
		v.put(PLAYER_TOTAL_MATCH, p.totalMatch);
		v.put(PLAYER_AVERAGE, p.average);
		v.put(PLAYER_STRATE, p.stRate);
		
		long re = db.update(PLAYER_TABLE, v, ID + " = " + pId, null);
		db.close();
		return re;
		
	}
	
	public String readPlayerNameWithIningsID(int inID){
		String s = "SELECT * FROM " + ININGS_PLAYER_TABLE + " WHERE " +
				ININGS_ID + " = " + inID;
		SQLiteDatabase db = this.getReadableDatabase();
		
		Log.d("Tag_readPlayerNameWithIningsId", "came here with inID = "+inID);
		
		Cursor c = db.rawQuery(s, null);
		Log.d("Tag_readPlayerNameWithIningsId", "row found = "+ c.getCount());
		
		if(c!=null)
			c.moveToFirst();
		int pId = c.getInt(c.getColumnIndex(PLAYER_ID));
		
		Log.d("Tag_readPlayerNameWithIningsId","Player ID = " + pId);
		
		s = "SELECT * FROM " + PLAYER_TABLE + " WHERE " + 
			ID + " = " + pId; 
		c = db.rawQuery(s, null);
		if(c!=null)
			c.moveToFirst();
		s = c.getString(c.getColumnIndex(PLAYER_NAME));
		
		db.close();
		
		return s;
	}
	
	public ArrayList<Inings> readAllIningsWithMatchId(int mId){
		ArrayList<Inings> ar = new ArrayList<Inings>();
		SQLiteDatabase db = this.getReadableDatabase();
		String s = "SELECT * FROM " + ININGS_MATCH_TABLE + " WHERE " +
				MATCH_ID + " = " + mId;
		
		Cursor c = db.rawQuery(s, null);
		if(c!=null)
			c.moveToFirst();
		
		Log.d("Tag_readAllIningsWithMatchId", "total row found = "+c.getCount());
		
		for(int i = 0; i < c.getCount(); i++){
			int inId = c.getInt(c.getColumnIndex(ININGS_ID));
			s = "SELECT * FROM " + ININGS_TABLE + " WHERE " + 
				ID + " = " + inId;
			Cursor cr = db.rawQuery(s, null);
			if(cr!=null)
				cr.moveToFirst();
			
			Inings in = new Inings();
			in.id = cr.getInt(cr.getColumnIndex(ID));
			in.balls = cr.getInt(cr.getColumnIndex(ININGS_BALL));
			in.runs = cr.getInt(cr.getColumnIndex(ININGS_RUN));
			in.wickets = cr.getInt(cr.getColumnIndex(ININGS_WICKET));
			
			Log.d("Tag_readAllIningsWithMatchId",in.toString());
			
			ar.add(in);
			c.moveToNext();
		}
		db.close();
		return ar;
	}
}
