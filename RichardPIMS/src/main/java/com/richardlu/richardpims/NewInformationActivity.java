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
import android.widget.RadioGroup;

import com.richardlu.data.DataService;
import com.richardlu.service.ToastShow;

public class NewInformationActivity extends ActionBarActivity {

    private static String TABLE_NAME = "PatientInfo";
    private Button btn_save = null;
    private EditText[] patientsConditionEditTexts = new EditText[20];

    private RadioGroup isInHospital = null;
    private String isTreated = "0_正在治疗";


    public void init()
    {
        isInHospital = (RadioGroup) findViewById(R.id.isInHospital);
        btn_save = (Button) findViewById(R.id.save);
    }

    public void setupView()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

        //选择数据表Radio

        isInHospital.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.treating) {
        /*把treating的内容传到isTreated*/

                    isTreated = "0_正在治疗";
                    ToastShow.ShowShortToast(NewInformationActivity.this,"已勾选“正在治疗”");
                } else if (checkedId == R.id.treated) {
        /*把treated的内容传到isTreated*/

                    isTreated = "1_已出院";
                    ToastShow.ShowShortToast(NewInformationActivity.this,"已勾选“已出院”");
                }
            }
        });

        //save Button

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
                //save and show toast
//******************************************************//

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_information);
        ToastShow.ShowShortToast(NewInformationActivity.this,"已勾选“正在治疗”");

        init();
        setupView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    private EditText[] getEditTextData()
    {
        patientsConditionEditTexts[0] = (EditText) findViewById(R.id.name);
        patientsConditionEditTexts[1] = (EditText) findViewById(R.id.sex);
        patientsConditionEditTexts[2] = (EditText) findViewById(R.id.age);
        patientsConditionEditTexts[3] = (EditText) findViewById(R.id.birthday);
        patientsConditionEditTexts[4] = (EditText) findViewById(R.id.fundRemark);
        patientsConditionEditTexts[5] = (EditText) findViewById(R.id.SN);
        patientsConditionEditTexts[6] = (EditText) findViewById(R.id.inHospitalDate);
        patientsConditionEditTexts[7] = (EditText) findViewById(R.id.diagnosis);
        patientsConditionEditTexts[8] = (EditText) findViewById(R.id.surgeryDate);
        patientsConditionEditTexts[9] = (EditText) findViewById(R.id.surgeon);
        patientsConditionEditTexts[10] = (EditText) findViewById(R.id.specialWay);
        patientsConditionEditTexts[11] = (EditText) findViewById(R.id.pathologicalDiagnosis);
        patientsConditionEditTexts[12] = (EditText) findViewById(R.id.pathlogicNumber);
        patientsConditionEditTexts[13] = (EditText) findViewById(R.id.surgeryRemark);
        patientsConditionEditTexts[14] = (EditText) findViewById(R.id.outDate);
        patientsConditionEditTexts[15] = (EditText) findViewById(R.id.reviewPeriod);
        patientsConditionEditTexts[16] = (EditText) findViewById(R.id.oralMedication);
        patientsConditionEditTexts[17] = (EditText) findViewById(R.id.outRemark);

        return patientsConditionEditTexts;
    }

    /**
     * A placeholder fragment containing a simple view.
     */


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewInformationActivity.this);
        builder.setMessage("确定要保存吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                        if(DataService.saveAllTextIntoSQLite(getEditTextData(), isTreated, NewInformationActivity.this))
                        {
                            ToastShow.ShowShortToast(NewInformationActivity.this, "患者信息已保存");
                            //back to main
                            Intent intent = new Intent();
                            intent.setClass(NewInformationActivity.this, MainActivity.class);
                            NewInformationActivity.this.finish();
                        }else{

                            ToastShow.ShowShortToast(NewInformationActivity.this, "患者信息未保存");
                        }
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