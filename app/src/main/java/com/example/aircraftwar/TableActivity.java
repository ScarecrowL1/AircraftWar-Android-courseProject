package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aircraftwar.DataDAO.DAOImpl;
import com.example.aircraftwar.DataDAO.UserData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        listView = (ListView) findViewById(R.id.list);

        List<UserData> userDataList = DAOImpl.userDataList;
        int rank = 0;
        String division = "          ";
        ArrayList<String> dataList = new ArrayList();
        dataList.add("排名"+division+"ID"+division+"分数"+division+division+division+"日期");
        for (UserData ud : userDataList) {
            StringBuilder sb = new StringBuilder();
            //division为10个空格
            sb.append(++rank).append(division)
                    .append(ud.getID()).append(division)
                    .append(ud.getScore()).append(division)
                    .append(ud.getDateInfo()).append(division);
            dataList.add(sb.toString());
        }



        ArrayAdapter<String> adapter=new ArrayAdapter<>(TableActivity.this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        //6、为列表中选中的项添加单击响应事件

    }
}