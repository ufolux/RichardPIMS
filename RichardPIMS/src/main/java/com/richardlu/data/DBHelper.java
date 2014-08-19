package com.richardlu.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Richard on 14-1-20.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "PatientDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PatientInfo";


    private static final String[] KEY = {"name", "sex", "age", "birthday", "fundRemark", "SN", "inHospitalDate", "diagnosis", "surgeryDate", "surgeon", "specialWay", "pathologicalDiagnosis","pathologicNumber", "surgeryRemark", "outDate", "reviewPeriod", "oralMedication", "outRemark", "isTreated"};



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //在数据库第一次生成的时候会调用这个方法，一般我们在这个方法里边生成数据库表。
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1;


        sql1 = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," + KEY[0]
                + " NTEXT, " + KEY[1] + " NTEXT, " + KEY[2] + " NTEXT, " + KEY[3] + " NTEXT, " + KEY[4] +
                " NTEXT, " + KEY[5] + " NTEXT, " + KEY[6] + " NTEXT, " + KEY[7] + " NTEXT, " + KEY[8] +
                " NTEXT, " + KEY[9] + " NTEXT, " + KEY[10] + " NTEXT, " + KEY[11] + " NTEXT, " + KEY[12] +
                " NTEXT, " + KEY[13] + " NTEXT, " + KEY[14] + " NTEXT, " + KEY[15] + " NTEXT, " + KEY[16] +
                " NTEXT, outRemark NTEXT, isTreated NTEXT)";
        System.out.println("我被调用了 oncreate");
        Log.i("haiyang:createDB=", sql1);
        db.execSQL(sql1);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



}


