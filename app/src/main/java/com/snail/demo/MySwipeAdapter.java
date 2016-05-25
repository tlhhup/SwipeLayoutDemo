package com.snail.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.List;

/**
 * Created by ping on 2016/5/25.
 */
public class MySwipeAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private List<String> mDats;

    public MySwipeAdapter(Context context, List<String> dats) {
        mContext = context;
        mDats = dats;
    }

    //返回在listView中item布局中的swipelayout控件的id
    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    //返回listview中的item布局生成的view
    @Override
    public View generateView(final int i, ViewGroup viewGroup) {
        View view = View.inflate(mContext, R.layout.swiplayout_item, null);
        return view;
    }

    //设置数据及对子控件添加点击事件
    @Override
    public void fillValues(final int i, View view) {
        TextView message = (TextView) view.findViewById(R.id.tv_message);
        message.setText(mDats.get(i));
        view.findViewById(R.id.tv_bottom_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemViewClickListener!=null){
                    mOnItemViewClickListener.onItemViewClick(v,i);
                }
            }
        });
        view.findViewById(R.id.tv_bottom_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemViewClickListener!=null){
                    mOnItemViewClickListener.onItemViewClick(v,i);
                }
            }
        });
    }

    @Override
    public int getCount() {
        if(mDats!=null){
            return mDats.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mDats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private OnItemViewClickListener mOnItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        mOnItemViewClickListener = onItemViewClickListener;
    }

    //将点击事件抛出交由外部处理
    public interface OnItemViewClickListener{

       void onItemViewClick(View view,int potion);

    }
}
