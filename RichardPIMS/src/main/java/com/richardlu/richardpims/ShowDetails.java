package com.richardlu.richardpims;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.richardlu.data.DBHelper;
import com.richardlu.data.DataService;
import com.richardlu.service.MapService;
import com.richardlu.service.ToastShow;

import java.util.List;
import java.util.Map;

public class ShowDetails extends ActionBarActivity {
    private final int numberOfData = 19;

    private List list;
    private TextView[] DetailTextViews = new TextView[numberOfData];

    private String SN;

    private int i = 0;
    private Button delete_btn = null;

    private String[] Items = new String[numberOfData];
    private String[] key = new String[] { "name", "sex", "age", "birthday", "fundRemark", "SN", "inHospitalDate",
            "diagnosis", "surgeryDate", "surgeon", "specialWay", "pathologicalDiagnosis","pathologicNumber", "surgeryRemark",
            "outDate", "reviewPeriod", "oralMedication", "outRemark", "isTreated" };

    private void init()
    {

        delete_btn = (Button)findViewById(R.id.delete_item);

        DetailTextViews[0] = (TextView) findViewById(R.id.show_name);
        DetailTextViews[1] = (TextView) findViewById(R.id.show_sex);
        DetailTextViews[2] = (TextView) findViewById(R.id.show_age);
        DetailTextViews[3] = (TextView) findViewById(R.id.show_birthday);
        DetailTextViews[4] = (TextView) findViewById(R.id.show_fundRemark);
        DetailTextViews[5] = (TextView) findViewById(R.id.show_SN);
        DetailTextViews[6] = (TextView) findViewById(R.id.show_inHospitalDate);
        DetailTextViews[7] = (TextView) findViewById(R.id.show_diagnosis);
        DetailTextViews[8] = (TextView) findViewById(R.id.show_surgeryDate);
        DetailTextViews[9] = (TextView) findViewById(R.id.show_surgeon);
        DetailTextViews[10] = (TextView) findViewById(R.id.show_specialWay);
        DetailTextViews[11] = (TextView) findViewById(R.id.show_pathologicalDiagnosis);
        DetailTextViews[12] = (TextView) findViewById(R.id.show_pathlogicNumber);
        DetailTextViews[13] = (TextView) findViewById(R.id.show_surgeryRemark);
        DetailTextViews[14] = (TextView) findViewById(R.id.show_outDate);
        DetailTextViews[15] = (TextView) findViewById(R.id.show_reviewPeriod);
        DetailTextViews[16] = (TextView) findViewById(R.id.show_oralMedication);
        DetailTextViews[17] = (TextView) findViewById(R.id.show_outRemark);
        DetailTextViews[18] = (TextView) findViewById(R.id.show_isTreated);
    }

    public void setupView()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SN =  getIntent().getStringExtra("SN");
//wrong

//**********************************************************************************************/
        //*用sn还是id
        if (SN != null) {


            DBHelper helper = new DBHelper(this);
            DataService DS = new DataService(this);
            //根据ID查询做数据库操作吧
            list = DS.getDataByCondition("SN",SN, helper);
            Map<String, Object> map = (Map<String, Object>)list.get(0);
            for (i = 0; i < numberOfData; i++) {

                Items[i] = (String)MapService.getSingleValueOfMap(map,key[i]);
                DetailTextViews[i].setText(Items[i]);
            }



            /*设置文字*/
//*******************************************************************************************//
        } else {
            ToastShow.ShowLongToast(this, "没有这个病人");
        }


        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            dialog();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        init();
        setupView();
//*************************************************************************************************************************************************/

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setupView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.show_details, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id;
        if (menuItem.isCheckable()) {
            menuItem.setChecked(true);
        }
        if (R.id.edit_acbtn == menuItem.getItemId()) {


                Intent intent = new Intent();
                intent.setClass(ShowDetails.this,ModifyItem.class);
                intent.putExtra("SN", getIntent().getStringExtra("SN"));
                intent.putExtra("Items", Items);
                startActivity(intent);

            }else{

                finish();
            return true;
        }



        return true;
    }


    protected void deleteItem()
    {
        DBHelper helper = new DBHelper(ShowDetails.this);
        DataService DS = new DataService(ShowDetails.this);
        DS.deleteItem("SN",SN,helper);
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowDetails.this);
        String message = "确定要删除“"+Items[0]+"”“"+Items[5]+"”吗?";
        builder.setMessage(message);
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        deleteItem();
                        dialog.dismiss();
                        ShowDetails.this.finish();
                    }
                });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }



}
//******************************************************************************************************************************************//

