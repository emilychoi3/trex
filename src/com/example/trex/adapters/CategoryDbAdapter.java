package com.example.trex.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class CategoryDbAdapter {
	
	static String NAME = "cname" ;
	
	private String TAG = "CategoryDbAdapter";
    private static final String DATABASE_NAME = "trex";
    private static final String DATABASE_TABLE = "categories";
    private static final int DATABASE_VERSION = 1;


   

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

    	private String TAG = "DatabaseHelper" ;
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.v(TAG, "In Constructor") ;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

   
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       //     Log.w(TAG, "Upgrading database from version " + oldVersion + " to " //$NON-NLS-1$//$NON-NLS-2$
         //           + newVersion + ", which will destroy all old data"); //$NON-NLS-1$
            //db.execSQL("DROP TABLE IF EXISTS usersinfo"); //$NON-NLS-1$
     //       onCreate(db);
        }
    }


    public CategoryDbAdapter(Context ctx) {
        this.mCtx = ctx;
        Log.v(TAG, "In constructor") ;
    }


    public CategoryDbAdapter open() throws SQLException {
        this.mDbHelper = new DatabaseHelper(this.mCtx);
        this.mDb = this.mDbHelper.getWritableDatabase();
        Log.v(TAG, "Database opened") ;
        return this;
    }

   
    public void close() {
        this.mDbHelper.close();
        Log.v(TAG, "Database closed") ;
    }


    public long insertCategory(String cname )
    {
    	Log.v(TAG, "In insertCategory") ;
    	ContentValues initialValues = new ContentValues() ;
    	initialValues.put(NAME, cname);
    	
    	
		return this.mDb.insert(DATABASE_TABLE, null, initialValues);
    	
    }


    public boolean deleteCategory(long cid) {

    	Log.v(TAG, "In deleteCategory") ;
        return this.mDb.delete(DATABASE_TABLE, "_id = " + cid, null) > 0; //$NON-NLS-1$
    }


    public Cursor fetchAllCategories( ) {

    	Log.v(TAG, "In fetchAllCategories") ;
        Cursor mCursor = this.mDb.query(DATABASE_TABLE, new String[] { "_id",NAME},
        		null, null, null, null, null);
        return mCursor ;
    }


    
	

}
