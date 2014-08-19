package com.richardlu.richardpims;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.richardlu.service.ToastShow;

public class InquiryActivity extends ActionBarActivity {

    RadioGroup searchBy = null;
    EditText checkText = null;

    Button check_btn = null;
    Button reset_btn = null;

    String searchType = "SN";
    String searchValue = "*";

    public void init()
    {
        checkText = (EditText)findViewById(R.id.checkText);
        searchBy = (RadioGroup)findViewById(R.id.searchBy_radio);
        reset_btn = (Button)findViewById(R.id.reset_btn);
        check_btn = (Button)findViewById(R.id.check_btn);
    }


    public void setupView()
    {
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        checkText.setInputType(InputType.TYPE_CLASS_NUMBER);
        ToastShow.ShowShortToast(this,"已勾选“按住院号查询”");

        //数据获取

        searchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.searchByName_rb)
                {
                    searchType = "name";
                    checkText.setInputType(InputType.TYPE_CLASS_TEXT);
                    checkText.setMaxLines(1);
                    ToastShow.ShowShortToast(InquiryActivity.this,"已勾选“按姓名查询”");
                }else if (checkedId == R.id.searchBySN_rb)
                {
                    searchType = "SN";
                    checkText.setInputType(InputType.TYPE_CLASS_NUMBER);

                    ToastShow.ShowShortToast(InquiryActivity.this,"已勾选“按住院号查询”");
                }
            }


        });


        //配置两个按钮

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkText.setText("");
                checkText.setHint("请输入要查询的信息");
            }
        });


        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkText.getText().toString().equals(""))

                    searchValue = checkText.getText().toString();
                else {
                    Intent intent = new Intent();
                    intent.setClass(InquiryActivity.this, NoDataFound.class);
                    startActivity(intent);
                }


                Intent intent = new Intent();
                intent.putExtra("searchType",searchType);
                intent.putExtra("searchValue","'"+searchValue+"'");
                intent.setClass(InquiryActivity.this, ShowResult.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        init();
        setupView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inquiry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


}
