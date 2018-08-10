package ca.codeward.escapethehorde.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String TAG = DBAdapter.class.getSimpleName();

    private static final String DATABASE_NAME = "escapeTheHorde";
    private static final int DATABASE_VERSION = 1;

    private Context mContext;
    private static DatabaseHelper mDbHelper;

    private static final String TABLE_USERS = "users";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT UNIQUE, " +
                    "uid TEXT NOT NULL, created_at TEXT NOT NULL)";

    // Constructors and Functions
    public DBAdapter(Context context) {
        mContext = context.getApplicationContext();
    }

    public SQLiteDatabase openReadDb() {
        if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(mContext);
        }
        return mDbHelper.getWritableDatabase();
    }

    public SQLiteDatabase openWriteDb() {
        if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(mContext);
        }
        return mDbHelper.getWritableDatabase();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(TABLE_CREATE_ARMOR);
//            db.execSQL(TABLE_CREATE_ITEM);
//            db.execSQL(TABLE_CREATE_NPC);
//            db.execSQL(TABLE_CREATE_NPCLOOT);
//            db.execSQL(TABLE_CREATE_PLAYER);
//            db.execSQL(TABLE_CREATE_PLAYERINVENTORY);
//            db.execSQL(TABLE_CREATE_PLAYERSTATS);
//            db.execSQL(TABLE_CREATE_ROOM);
//            db.execSQL(TABLE_CREATE_ROOMEXITS);
            db.execSQL(TABLE_CREATE_USERS);
//            db.execSQL(TABLE_CREATE_WEAPON);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " +
                    newVersion + ", which will destroy all old data");
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARMOR);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NPC);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NPCLOOT);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERINVENTORY);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERSTATS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMEXITS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEAPON);
            onCreate(db);
        }

        private boolean tableExists(String table_name) {

            return false;
        }
    }
}
