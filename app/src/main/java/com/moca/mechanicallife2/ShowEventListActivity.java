package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;
import com.moca.mechanicallife2.myentity.MyEventInfo;

import java.util.Calendar;
import java.util.List;

public class ShowEventListActivity extends AppCompatActivity {

    private ListView listView;
    private String[] names={"women","diaos","dmakmd","adodad"};
    List<MyEvent> eventsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event_list);
        System.out.println("跳转过来");
        Bundle bundle =getIntent().getExtras();
        int startDateNumber = bundle.getInt("startDateNumber");
        int endDateNumber = bundle.getInt("endDateNumber");
        int flag = bundle.getInt("flag");
        System.out.println("ShowEventListActivity1  "+startDateNumber+":"+endDateNumber);


        //当天事件列表特殊处理
        String strWeekFlag = "";
        EventDao eventDao = new EventDao(this);




        if (flag == 0){
            int thisWeek = bundle.getInt("week");
            switch (thisWeek){
                case 1:
                    strWeekFlag = "week7";
                    break;
                case 2:
                    strWeekFlag = "week1";
                    break;
                case 3:
                    strWeekFlag = "week2";
                    break;
                case 4:
                    strWeekFlag = "week3";
                    break;
                case 5:
                    strWeekFlag = "week4";
                    break;
                case 6:
                    strWeekFlag = "week5";
                    break;
                case 7:
                    strWeekFlag = "week6";
                    break;
                default:
                    break;
            }
            eventsList = eventDao.queryAllByDate(startDateNumber,strWeekFlag);
        }else {
            eventsList = getSpanPlanNumber(startDateNumber,endDateNumber);
        }



        listView = findViewById(R.id.show_list);
        //添加数据适配器
        MyDataAdapter dataAdapter = new MyDataAdapter();
        listView.setAdapter(dataAdapter);


    }

    //自定义数据适配器
    class MyDataAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return eventsList.size();
        }

        @Override
        public Object getItem(int position) {

            return eventsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MyViewHolder viewHolder;
            final MyEventInfo myEventInfo = new MyEventInfo(eventsList.get(position));

            if (convertView == null){
                convertView = View.inflate(ShowEventListActivity.this,R.layout.event_list_item,null);
                //界面中的控件
                viewHolder = new MyViewHolder();
                viewHolder.name = convertView.findViewById(R.id.event_list_name);
                viewHolder.state = convertView.findViewById(R.id.event_list_state);
                viewHolder.startTime = convertView.findViewById(R.id.item_starttime);
                viewHolder.endTime = convertView.findViewById(R.id.item_endtime);

                //根据事件类型设置颜色
                LinearLayout linearLayout = convertView.findViewById(R.id.item_onclick) ;
                switch (myEventInfo.getEventTypeNumber()){
                    case 0:
                        linearLayout.setBackgroundResource(R.drawable.corner_view1);
                        break;
                    case 1:
                        linearLayout.setBackgroundResource(R.drawable.corner_view2);
                        break;
                    case 2:
                        linearLayout.setBackgroundResource(R.drawable.corner_view3);
                        break;
                    case 3:
                        linearLayout.setBackgroundResource(R.drawable.corner_view4);
                        break;
                    default:
                        break;
                }


                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowEventListActivity.this,EventDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",myEventInfo.getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (MyViewHolder)convertView.getTag();
            }
            viewHolder.name.setText(myEventInfo.getEventName());
            viewHolder.state.setText(myEventInfo.getEventStateNow());
            viewHolder.startTime.setText(myEventInfo.getDetailStartTime());
            viewHolder.endTime.setText(myEventInfo.getDetailEndTime());





            return convertView;
        }

        //定义一个ViewHold
        class MyViewHolder{
            TextView name,state,startTime,endTime;
        }


    }




    public List getSpanPlanNumber(int startDateNumber, int endDateNumber){
        EventDao eventDao = new EventDao(ShowEventListActivity.this);
        System.out.println("ShowEventListActivity1  "+startDateNumber+":"+endDateNumber);
        List<MyEvent> List = eventDao.querySpanByDate(startDateNumber, endDateNumber);


        return List;

    }
}
