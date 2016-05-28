package com.snail.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.main_lv);

        //初始化数据
        final List<String> datas=new ArrayList<>();
        for(int i=0;i<50;i++){
            datas.add("这是第"+i+"条数据");
        }
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, datas);
//        mListView.setAdapter(adapter);
        //使用baseSwipeAdapter
        final MySwipeAdapter adapter = new MySwipeAdapter(this, datas);
        mListView.setAdapter(adapter);
        adapter.setMode(Attributes.Mode.Single);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //滑动时关闭所有的打开的item
                if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    adapter.closeAllItems();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        //添加item的点击事件处理
        adapter.setOnItemViewClickListener(new MySwipeAdapter.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int potion) {
                switch (view.getId()){
                    case R.id.tv_bottom_delete:
                        //Toast.makeText(MainActivity.this,"点击删除--->位置为--->"+potion,Toast.LENGTH_LONG).show();
                        datas.remove(potion);
                        adapter.notifyDataSetChanged();
                        adapter.closeAllItems();
                        break;
                    case R.id.tv_bottom_upload:
                        Toast.makeText(MainActivity.this,adapter.getItem(potion).toString(),Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        //listview的item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,SwipeListViewActivity.class);
                startActivity(intent);
            }
        });
    }

}
