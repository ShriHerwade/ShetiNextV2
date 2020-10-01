package com.shetinext.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SHETINXT";

    // below are the tables
    public static final String USER_TABLE = "USER";
    public static final String POF_TABLE = "POF";
    public static final String CROP_META_TABLE = "CROP_META";//crop details : meta-data
    public static final String FARM_TABLE = "FARM";
    public static final String PLOT_TABLE = "PLOT";
    public static final String CROP_TABLE = "CROP";
    public static final String EXPENSE_TABLE = "EXPENSE";
    public static final String CROP_LIFECYCLE_TABLE = "CROP_LIFECYCLE";


    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {// every where 0 is TRUE or  a standard state
        db.execSQL("create TABLE " + USER_TABLE + "(user_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name TEXT, mobile INTEGER, email TEXT UNIQUE,pwd TEXT, pin_enabled INTEGER DEFAULT 1, pin INTEGER)");
        db.execSQL("create TABLE " + POF_TABLE + "(pof_id INTEGER PRIMARY KEY AUTOINCREMENT ,pof_name TEXT,mobile INTEGER,daily_wadger INTEGER DEFAULT 1, partner INTEGER DEFAULT 1)");
        db.execSQL("create TABLE " + CROP_META_TABLE + "(crop_id INTEGER PRIMARY KEY AUTOINCREMENT ,crop_name TEXT,variety TEXT )");//meta data

        db.execSQL("create TABLE " + FARM_TABLE + "(farm_id INTEGER PRIMARY KEY AUTOINCREMENT ,farm_name TEXT,area INTEGER, unit TEXT, farm_type  INTEGER DEFAULT 0, status INTEGER DEFAULT 0 )"); //farm_type 0 : owned & self doing, 1 : owned & in partnership,2 : on rent
        db.execSQL("create TABLE " + PLOT_TABLE + "(plot_id INTEGER PRIMARY KEY AUTOINCREMENT ,plot_name TEXT,area INTEGER, unit TEXT, multicrop_enabled INTEGER DEFAULT 0,start_date DATE, end_date DATE,status INTEGER DEFAULT 0 ,FOREIGN KEY (farm_id) REFERENCES FARM (farm_id) \n" +
                "            ON DELETE CASCADE ON UPDATE NO ACTION )");

        db.execSQL("create TABLE " + CROP_TABLE + "(crop_id INTEGER PRIMARY KEY AUTOINCREMENT ,crop_name TEXT,area INTEGER, unit TEXT,start_date DATE,end_date DATE, multicrop_number INTEGER DEFAULT 0, status INTEGER DEFAULT 0 ,FOREIGN KEY (farm_id) REFERENCES FARM (farm_id) \n" +
                "            ON DELETE NO ACTION ON UPDATE NO ACTION )");

        db.execSQL("create TABLE " + EXPENSE_TABLE + "(expnse_id INTEGER PRIMARY KEY AUTOINCREMENT ,date DATE, type TEXT,sub_type TEXT, amount INTEGER, otherDetails  TEXT, status INTEGER DEFAULT 0 ," +
                "FOREIGN KEY (pof_id) REFERENCES POF (pof_id),FOREIGN KEY (crop_id) REFERENCES CROP (crop_id) ON DELETE NO ACTION ON UPDATE NO ACTION )");

        db.execSQL("create TABLE " + CROP_LIFECYCLE_TABLE + "(activity_id INTEGER PRIMARY KEY AUTOINCREMENT ,activity_key TEXT,activity_value TEXT,start_date DATE, end_date DATE, FOREIGN KEY (crop_id) REFERENCES CROP (crop_id) ON DELETE NO ACTION ON UPDATE NO ACTION )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + POF_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CROP_META_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FARM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLOT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CROP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CROP_LIFECYCLE_TABLE);
        onCreate(db);
    }
}
