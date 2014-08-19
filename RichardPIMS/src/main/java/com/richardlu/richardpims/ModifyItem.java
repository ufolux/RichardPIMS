package com.richardlu.richardpims;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.richardlu.data.DBHelper;
import com.richardlu.data.DataService;
import com.richardlu.service.ToastShow;

import java.util.List;

public class ModifyItem extends ActionBarActivity {

    private String SN;
    private final int numberOfData = 18;
    /*这里是18因为isTreated是用RadioGroup展示的*/
    private EditText[] DetailEditTexts = new EditText[numberOfData];
    private Button btn_save = null;
    private RadioButton treated = null;
    private RadioButton treating = null;
    private RadioGroup  isInHospital = null;
    private String isTreated;
    private List list;
    private String[] Items = new String[numberOfData];
    private String[] key = new String[] { "name", "sex", "age", "birthday", "fundRemark", "SN", "inHospitalDate",
            "diagnosis", "surgeryDate", "surgeon", "specialWay", "pathologicalDiagnosis","pathologicNumber", "surgeryRemark",
            "outDate", "reviewPeriod", "oralMedication", "outRemark", "isTreated" };



    public void init()
    {

        DetailEditTexts[0] = (EditText) findViewById(R.id.mod_name);
        DetailEditTexts[1] = (EditText) findViewById(R.id.mod_sex);
        DetailEditTexts[2] = (EditText) findViewById(R.id.mod_age);
        DetailEditTexts[3] = (EditText) findViewById(R.id.mod_birthday);
        DetailEditTexts[4] = (EditText) findViewById(R.id.mod_fundRemark);
        DetailEditTexts[5] = (EditText) findViewById(R.id.mod_SN);
        DetailEditTexts[6] = (EditText) findViewById(R.id.mod_inHospitalDate);
        DetailEditTexts[7] = (EditText) findViewById(R.id.mod_diagnosis);
        DetailEditTexts[8] = (EditText) findViewById(R.id.mod_surgeryDate);
        DetailEditTexts[9] = (EditText) findViewById(R.id.mod_surgeon);
        DetailEditTexts[10] = (EditText) findViewById(R.id.mod_specialWay);
        DetailEditTexts[11] = (EditText) findViewById(R.id.mod_pathologicalDiagnosis);
        DetailEditTexts[12] = (EditText) findViewById(R.id.mod_pathlogicNumber);
        DetailEditTexts[13] = (EditText) findViewById(R.id.mod_surgeryRemark);
        DetailEditTexts[14] = (EditText) findViewById(R.id.mod_outDate);
        DetailEditTexts[15] = (EditText) findViewById(R.id.mod_reviewPeriod);
        DetailEditTexts[16] = (EditText) findViewById(R.id.mod_oralMedication);
        DetailEditTexts[17] = (EditText) findViewById(R.id.mod_outRemark);

        isInHospital = (RadioGroup)findViewById(R.id.mod_isInHospital);
        treated = (RadioButton)findViewById(R.id.mod_treated);
        treating = (RadioButton)findViewById(R.id.mod_treating);

        btn_save = (Button)findViewById(R.id.mod_save);

    }


    public void setupView()
    {

        SN  = getIntent().getStringExtra("SN");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Items = getIntent().getStringArrayExtra("Items");

        for (int i = 0; i < numberOfData; i++) {
            DetailEditTexts[i].setText(Items[i]);
        }
        for (int i=0; i < Items.length; i++)
        {
            if(!Items[i].equals(null))
            if (Items[i].equals("正在治疗"))
            {
                isTreated = "0_正在治疗";
                ToastShow.ShowShortToast(ModifyItem.this, "已勾选“正在治疗”");
                treating.setSelected(true);

            }
            else if(Items[i].equals("已出院")){

                isTreated = "1_已出院";
                ToastShow.ShowShortToast(ModifyItem.this,"已勾选“已出院”");

                treated.setSelected(true);
            }

        }
        isInHospital.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.mod_treating) {
        /*把treating的内容传到isTreated*/

                    isTreated = "0_正在治疗";
                    ToastShow.ShowShortToast(ModifyItem.this, "已勾选“正在治疗”");
                } else if (checkedId == R.id.mod_treated) {
        /*把treated的内容传到isTreated*/

                    isTreated = "1_已出院";
                    ToastShow.ShowShortToast(ModifyItem.this,"已勾选“已出院”");
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_item);

        init();
        setupView();

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modify_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent intent = new Intent();

            intent.setClass(ModifyItem.this, ShowDetails.class);
            ModifyItem.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */




    private EditText[] getEditTextData()
    {

        return DetailEditTexts;
    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyItem.this);
        builder.setMessage("确定要修改吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        save();
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


    protected void save()
    {


        DataService DS = new DataService(ModifyItem.this);
        DBHelper helper = new DBHelper(ModifyItem.this);
        DS.deleteItem("SN", SN, helper);


        if(DataService.saveAllTextIntoSQLite(getEditTextData(), isTreated, ModifyItem.this))
        {

            if (SN != null) {

                //根据ID查询做数据库操作吧
                list = DS.getDataByCondition("SN",DetailEditTexts[5].getText().toString(), helper);

            /*设置文字*/
//*******************************************************************************************//

                ToastShow.ShowShortToast(ModifyItem.this, "患者信息已修改");
                //back to main
                Intent intent = new Intent();

                intent.setClass(ModifyItem.this, ShowDetails.class);
                ModifyItem.this.finish();
            }else {
                ToastShow.ShowShortToast(ModifyItem.this, "数据不存在");
            }

        }else{
            ToastShow.ShowShortToast(ModifyItem.this, "患者信息未修改");
        }

    }

}
