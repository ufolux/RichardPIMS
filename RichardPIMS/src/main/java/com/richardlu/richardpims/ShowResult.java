package com.richardlu.richardpims;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.richardlu.data.DBHelper;
import com.richardlu.data.DataService;
import com.richardlu.service.MapService;

import java.util.List;
import java.util.Map;

/**
 * Created by Richard on 14-1-25.
 */
public class ShowResult extends ActionBarActivity {

    private ListView listView;
    private DataService DS;
    private String searchType = null;
    private String searchValue = null;
    private String SN = null;
    private List<Map<String, Object>> list;


    public void init()
    {

    }

    public void setupView()
    {
        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //************************************/

        DS = new DataService(this);

        searchType = (String) getIntent().getExtras().get("searchType");
        searchValue = (String) getIntent().getExtras().get("searchValue");

        listView = (ListView) this.findViewById(R.id.patient_list);
        DBHelper helper = new DBHelper(this);

        if (!searchValue.equals("'*'")) {
            list = DS.getDataList(searchType, searchValue, helper);
        } else if (searchValue.equals("'*'")) {
            list = DS.getAllDataList(helper);
        }

        if (list.size() != 0) {




            final SimpleAdapter adapter = new SimpleAdapter(this, list,
                    R.layout.activity_show_result, new String[]{"name", "SN", "isTreated"},
                    new int[]{R.id.list_name, R.id.list_SN,
                            R.id.list_isTreated});
            //实现列表的显示


            listView.setAdapter(adapter);
            //条目点击事件
            listView.setOnItemClickListener(new ItemClickListener());
        } else {
            Intent intent = new Intent();

            intent.setClass(ShowResult.this, NoDataFound.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        init();

        setupView();
    }

    //获取点击事件
    private final class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Map<String, Object> map = list.get(position);
               SN = (String) MapService.getSingleValueOfMap(map, "SN");

            Intent intent = new Intent();
            intent.putExtra("SN",SN);
            intent.setClass(ShowResult.this, ShowDetails.class);

            startActivity(intent);


        }
    }

    @Override
    protected void onResume()
    {

        super.onResume();

        DS = new DataService(this);


        listView = (ListView) this.findViewById(R.id.patient_list);
        DBHelper helper = new DBHelper(this);

        if (!searchValue.equals("'*'")) {
            list = DS.getDataList(searchType, searchValue, helper);
        } else if (searchValue.equals("'*'")) {
            list = DS.getAllDataList(helper);
        }

        if (list.size() != 0) {




            final SimpleAdapter adapter = new SimpleAdapter(this, list,
                    R.layout.activity_show_result, new String[]{"name", "SN", "isTreated"},
                    new int[]{R.id.list_name, R.id.list_SN,
                            R.id.list_isTreated});
            //实现列表的显示


            listView.setAdapter(adapter);
            //条目点击事件
            listView.setOnItemClickListener(new ItemClickListener());
        } else {
            Intent intent = new Intent();

            intent.setClass(ShowResult.this, NoDataFound.class);
            startActivity(intent);
        }


    }
//*********************************************************************************/


}