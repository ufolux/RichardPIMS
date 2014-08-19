package com.richardlu.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Richard on 14-1-20.
 */
public class DataService {

    private DBHelper dbOpenHelper;
    private static final String TABLE_NAME = "PatientInfo";

    public DataService(Context context) {
        this.dbOpenHelper = new DBHelper(context);
    }

    public boolean CreateTable(String sqlstr,DBHelper mOpenHelper) {
        //mOpenHelper.getWritableDatabase()语句负责得到一个可写的SQLite数据库，如果这个数据库还没有建立，
        //那么mOpenHelper辅助类负责建立这个数据库。如果数据库已经建立，那么直接返回一个可写的数据库。
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        Log.i("haiyang:createDB=", sqlstr);

        try {
            db.execSQL("DROP TABLE IF EXISTS diary");
            db.execSQL(sqlstr);
            Log.i("数据表成功重建",sqlstr);

            return true;
        } catch (SQLException e) {
            Log.i("数据表重建错误",e.toString());

            return false;
        }
    }

    /*
     * 删除数据表
     */
    public boolean dropTable(String sqlstr,DBHelper mOpenHelper) {
        //mOpenHelper.getWritableDatabase()语句负责得到一个可写的SQLite数据库，如果这个数据库还没有建立，
        //那么mOpenHelper辅助类负责建立这个数据库。如果数据库已经建立，那么直接返回一个可写的数据库。
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        //String sqlstr = "drop table " + TABLE_NAME;
        try {
            db.execSQL(sqlstr);
            Log.i("数据表成功重建", sqlstr);

            return true;
        } catch (SQLException e) {
            Log.i("数据表重建错误", e.toString());

            return false;
        }
    }

    /*
     * 插入一条数据
     */
    public boolean insertItem(String sqlstr,DBHelper mOpenHelper) {
        //mOpenHelper.getWritableDatabase()语句负责得到一个可写的SQLite数据库，如果这个数据库还没有建立，
        //那么mOpenHelper辅助类负责建立这个数据库。如果数据库已经建立，那么直接返回一个可写的数据库。
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        try {
            // Log.i（）会将参数内容打印到日志当中，并且打印级别是Info级别
            // Android支持5种打印级别，分别是Verbose、Debug、Info、Warning、Error，当然我们在程序当中一般用到的是Info级别
            Log.i("RichardLu=", sqlstr);

            db.execSQL(sqlstr);


            Log.i("数据表成功重建", sqlstr);
            return true;
        } catch (SQLException e) {
            Log.i("数据表重建错误", e.toString());
            System.out.println(e.toString());
            return false;
        }
    }

    /*
     * 删除其中的一条数据
     */
    public boolean deleteItem(String Key, String delCon/*key=xxx*/,DBHelper mOpenHelper) {
        try {
            //mOpenHelper.getWritableDatabase()语句负责得到一个可写的SQLite数据库，如果这个数据库还没有建立，
            //那么mOpenHelper辅助类负责建立这个数据库。如果数据库已经建立，那么直接返回一个可写的数据库。
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            //第一个参数是数据库表名，在这里是TABLE_NAME，也就是diary。
            //第二个参数，相当于SQL语句当中的where部分，也就是描述了删除的条件。
            //如果在第二个参数当中有“？”符号，那么第三个参数中的字符串会依次替换在第二个参数当中出现的“？”符号。

            String delStr[] = {delCon};
            db.delete(TABLE_NAME, Key+"=?", delStr);
            Log.i("删除title为haiyang的一条记录", delStr[0]);

            return true;
        } catch (SQLException e) {
            Log.i("删除title为haiyang的一条记录失败", e.toString());

            return false;
        }

    }

    /*
     * 在屏幕的title区域显示当前数据表当中的数据的条数。
     */
    /*
     * Cursor cur = db.query(TABLE_NAME, col, null, null, null, null, null)语句将查询到的数据放到一个Cursor 当中。
        这个Cursor里边封装了这个数据表TABLE_NAME当中的所有条列。
        query()方法相当的有用，在这里我们简单地讲一下。
            第一个参数是数据库里边表的名字，比如在我们这个例子，表的名字就是TABLE_NAME，也就是"diary"。
            第二个字段是我们想要返回数据包含的列的信息。在这个例子当中我们想要得到的列有title、body。我们把这两个列的名字放到字符串数组里边来。
            第三个参数为selection，相当于SQL语句的where部分，如果想返回所有的数据，那么就直接置为null。
            第四个参数为selectionArgs。在selection部分，你有可能用到“?”，那么在selectionArgs定义的字符串会代替selection中的“?”。
            第五个参数为groupBy。定义查询出来的数据是否分组，如果为null则说明不用分组。
            第六个参数为having ，相当于SQL语句当中的having部分。
            第七个参数为orderBy，来描述我们期望的返回值是否需要排序，如果设置为null则说明不需要排序。
     */

    public void showItems(String TABLE_NAME,DBHelper mOpenHelper) {

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String col[] = {"name", "sex", "age", "birthday", "fundRemark", "SN", "inHospitalDate", "diagnosis", "surgeryDate"
                , "surgeon", "specialWay", "pathologicalDiagnosis",
                "pathologicNumber", "surgeryRemark", "outDate", "reviewPeriod", "oralMedication", "outRemark", "isTreated"};
        //查询数据
        Cursor cur = db.query(TABLE_NAME, col, null, null, null, null, null);
        Integer num = cur.getCount();
        //Log.i(Integer.toString(num) + " 条记录",);
    }

//得到某一个结果集
    public List<Map<String, Object>> getDataList(String searchType/*searchType*/, String searchValue/*searchValue*/, DBHelper mOpenHelper/*DBHelper*/) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
//        Patient p = new Patient();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        String condition = searchType+"="+searchValue;
        Cursor cur = db.query(TABLE_NAME, new String[] { "_id","name", "SN", "isTreated" }, condition,
                null, null, null, "isTreated asc,SN asc");
        while (cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                cur.moveToPosition(i);
                String _id = cur.getString(0);
                String name = cur.getString(1);
                String SN = cur.getString(2);
                String isTreated = cur.getString(3);

                if(isTreated.equals("0_正在治疗") )
                {
                    isTreated = "正在治疗";
                }else if (isTreated.equals("1_已出院") )
                {
                    isTreated = "已出院";
                }

                map = new HashMap<String, Object>();

                map.put("_id", _id);
                map.put("name", name);
                map.put("SN", SN);
                map.put("isTreated", isTreated);

                list.add(map);
            }
        }

        return list;
    }
//得到所有的结果集
    public List<Map<String, Object>> getAllDataList(DBHelper mOpenHelper/*DBHelper*/) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
//        Patient p = new Patient();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();


        Cursor cur = db.query(TABLE_NAME, new String[] { "_id","name", "SN", "isTreated" }, null,
                null, null, null, "isTreated asc,SN asc");
        while (cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                cur.moveToPosition(i);
                String _id = cur.getString(0);
                String name = cur.getString(1);
                String SN = cur.getString(2);
                String isTreated = cur.getString(3);


                if(isTreated.equals("0_正在治疗") )
                {
                    isTreated = "正在治疗";
                }else if (isTreated.equals("1_已出院") )
                {
                    isTreated = "已出院";
                }

                map = new HashMap<String, Object>();

                map.put("_id", _id);
                map.put("name", name);
                map.put("SN", SN);
                map.put("isTreated", isTreated);

                list.add(map);
            }
        }

        return list;
    }


    /*获取全部详细数据*/
    public List<Map<String, Object>> getDataByCondition(String Key, String Value, DBHelper mOpenHelper/*DBHelper*/) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();


        /*_id是真正的_id*/
        /*数据区*/
        String _id = null;
        String name = null;
        String sex = null;
        String age = null;
        String birthday = null;
        String fundmark = null;
        String SN = null;
        String inHospitalDate = null;
        String diagnosis = null;
        String surgeryDate = null;
        String surgeon = null;
        String specialWay = null;
        String pathologicalDiagnosis = null;
        String pathologicNumber = null;
        String surgeryRemark = null;
        String outDate = null;
        String reviewPeriod = null;
        String oralMedication = null;
        String outRemark = null;
        String isTreated = null;

        /*Logic Area*/

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String condition;
        if(Key.equals("_id") )
        {
           condition = Key+ "="+ Value;
        }else{
           condition = Key+ "= '"+Value+"'";
        }

        Cursor cur = db.query(TABLE_NAME, new String[] { "_id","name", "sex", "age", "birthday", "fundRemark", "SN", "inHospitalDate",
                "diagnosis", "surgeryDate", "surgeon", "specialWay", "pathologicalDiagnosis","pathologicNumber", "surgeryRemark",
                "outDate", "reviewPeriod", "oralMedication", "outRemark", "isTreated" }, condition,
                null, null, null, null);
        while (cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                cur.moveToPosition(i);

                _id = cur.getString(0);
                name = cur.getString(1);
                sex = cur.getString(2);
                age = cur.getString(3);
                birthday = cur.getString(4);
                fundmark = cur.getString(5);
                SN = cur.getString(6);
                inHospitalDate = cur.getString(7);
                diagnosis = cur.getString(8);
                surgeryDate = cur.getString(9);
                surgeon = cur.getString(10);
                specialWay = cur.getString(11);
                pathologicalDiagnosis = cur.getString(12);
                pathologicNumber = cur.getString(13);
                surgeryRemark = cur.getString(14);
                outDate = cur.getString(15);
                reviewPeriod = cur.getString(16);
                oralMedication = cur.getString(17);
                outRemark = cur.getString(18);
                isTreated = cur.getString(19);


                if(isTreated.equals("0_正在治疗") )
                {
                    isTreated = "正在治疗";
                }else if (isTreated.equals("1_已出院") )
                {
                    isTreated = "已出院";
                }

                map = new HashMap<String, Object>();

                map.put("_id", _id);
                map.put("name", name);
                map.put("sex", sex);
                map.put("age",age);
                map.put("birthday",birthday);
                map.put("fundRemark",fundmark);
                map.put("SN",SN);
                map.put("inHospitalDate",inHospitalDate);
                map.put("diagnosis",diagnosis);
                map.put("surgeryDate",surgeryDate);
                map.put("surgeon",surgeon);
                map.put("specialWay",specialWay);
                map.put("pathologicalDiagnosis",pathologicalDiagnosis);
                map.put("pathologicNumber",pathologicNumber);
                map.put("surgeryRemark",surgeryRemark);
                map.put("outDate",outDate);
                map.put("reviewPeriod",reviewPeriod);
                map.put("oralMedication",oralMedication);
                map.put("outRemark",outRemark);
                map.put("isTreated",isTreated);
                list.add(map);
            }
        }

        return list;
    }




    /* 
     * get the count of the database 
     */
    public long getCount(){
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select count(*) from Patient", null);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }


    public static boolean saveAllTextIntoSQLite(EditText[] patientsConditionEditTexts,String isTreated,Context context) {
        String VALUE;
        String KEY;
        String[] patientConditionStrings = new String[20];
        boolean sRect;
        DataService DS = new DataService(context);
        DBHelper helper = new DBHelper(context);



        //***************************************************************************************//
        //*  int i from 0~17

        for (int i = 0; i < 18; i++) {
            patientConditionStrings[i] = patientsConditionEditTexts[i].getText().toString();

        }
        KEY = "name,sex,age,birthday,fundRemark,SN,inHospitalDate,diagnosis,surgeryDate,surgeon,specialWay,pathologicalDiagnosis"+
                ", pathologicNumber,surgeryRemark,outDate,reviewPeriod,oralMedication,outRemark,isTreated";
        VALUE = "'" +patientConditionStrings[0] +
                "','" + patientConditionStrings[1] +
                "','" + patientConditionStrings[2] +
                "','" + patientConditionStrings[3] +
                "','" + patientConditionStrings[4] +
                "','" + patientConditionStrings[5] +
                "','" + patientConditionStrings[6] +
                "','" + patientConditionStrings[7] +
                "','" + patientConditionStrings[8] +
                "','" + patientConditionStrings[9] +
                "','" + patientConditionStrings[10] +
                "','" + patientConditionStrings[11] +
                "','" + patientConditionStrings[12] +
                "','" + patientConditionStrings[13] +
                "','" + patientConditionStrings[14] +
                "','" + patientConditionStrings[15] +
                "','" + patientConditionStrings[16] +
                "','" + patientConditionStrings[17] +
                "','" + isTreated +"');" ;


        String sqlStr = "INSERT INTO " + TABLE_NAME +" ("+ KEY +") VALUES ("+ VALUE +")";
        //Null Pointer
       /* DS.CreateTable(sqlStr);*/
        sRect = DS.insertItem(sqlStr, helper);

        return sRect;
    }

    public boolean deleteDataById(String _id,DBHelper mOpenHelper/*DBHelper*/)
    {

        try {

            SQLiteDatabase db = mOpenHelper.getWritableDatabase();

            db.delete(TABLE_NAME,"_id="+_id,null);
            Log.i("删除title为haiyang的一条记录",_id);

            return true;
        } catch (SQLException e) {
            Log.i("删除title为haiyang的一条记录失败", e.toString());

            return false;
        }

    }





//*******************************************************************************************************************//



}