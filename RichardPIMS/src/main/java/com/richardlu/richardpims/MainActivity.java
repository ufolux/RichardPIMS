package com.richardlu.richardpims;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.richardlu.service.ExportService;
import com.richardlu.service.ToastShow;

public class MainActivity extends ActionBarActivity {

    private Button btn_addNew = null;
    private Button btn_lookUp = null;
    private Button btn_about = null;
    private Button btn_exportXLS = null;
    private Button btn_exit = null;


    public void init()
    {
        btn_addNew = (Button) findViewById(R.id.addNew);
        btn_lookUp = (Button) findViewById(R.id.lookUp);
        btn_exportXLS = (Button)findViewById(R.id.exportXLS);
        btn_about = (Button)findViewById(R.id.about);
        btn_exit = (Button) findViewById(R.id.exit);
    }

    public void setupView()
    {
        //新增

        btn_addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewInformationActivity.class);

                startActivity(intent);

            }
        });

        //查询

        btn_lookUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, InquiryActivity.class);

                startActivity(intent);

            }
        });



        //导出

        btn_exportXLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ExportService.copyFile().equals("false"))
                {
                    ToastShow.ShowShortToast(MainActivity.this,"已导出到SD卡PIMS文件夹下");
                }else{
                    ToastShow.ShowShortToast(MainActivity.this,"导出失败");
                }

            }
        });


        //关于
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setClass(MainActivity.this,AboutActivity.class);
                startActivity(intent);

            }
        });
        //退出

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();

            }
        });
    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确定要退出PIMS吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                        System.exit(1);
                        android.os.Process.killProcess(android.os.Process.myPid());
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupView();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */


}
