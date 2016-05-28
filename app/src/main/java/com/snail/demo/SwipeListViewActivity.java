package com.snail.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.demo.widget.SwipeListView;
import com.snail.demo.widget.SwipeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwipeListViewActivity extends AppCompatActivity {

    private SwipeListView mSwipeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_list_view);
        mSwipeListView = (SwipeListView) findViewById(R.id.slv);

        //初始化数据
        final List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("这是第" + i + "条数据");
        }
        final MySwipeListViewAdapter adapter = new MySwipeListViewAdapter(this, datas);
        mSwipeListView.setSwipeListViewAdapter(adapter);
        //添加item的点击事件处理
        adapter.setOnItemViewClickListener(new MySwipeListViewAdapter.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int potion) {
                switch (view.getId()) {
                    case R.id.tv_bottom_delete:
                        //Toast.makeText(MainActivity.this,"点击删除--->位置为--->"+potion,Toast.LENGTH_LONG).show();
                        datas.remove(potion);
                        adapter.notifyDataSetChanged();
                        adapter.closeAllItems();
                        break;
                    case R.id.tv_bottom_upload:
                        Toast.makeText(SwipeListViewActivity.this, adapter.getItem(potion).toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        //listview的item点击事件
        mSwipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SwipeListViewActivity.this, "setOnItemClickListener" + adapter.getItem(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private final class MySwipeListViewAdapter extends SwipeListViewAdapter {

        private Context mContext;
        private List<String> mDatas;

        public MySwipeListViewAdapter(Context context, List<String> datas) {
            mContext = context;
            mDatas = datas;
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.swipe;
        }

        @Override
        public View generateView(int position, ViewGroup parent) {
            View view = View.inflate(mContext, R.layout.swiplayout_item, null);
            return view;
        }

        @Override
        public void bindValues(int position, View convertView) {
            TextView message = (TextView) convertView.findViewById(R.id.tv_message);
            message.setText(mDatas.get(position));
        }

        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

}
