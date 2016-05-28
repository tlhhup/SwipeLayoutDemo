package com.snail.demo.widget;

import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;

/**
 * Created by hup on 2016/5/28.
 */
public abstract class SwipeListViewAdapter extends BaseSwipeAdapter {

    @Override
    public void fillValues(final int position, View convertView) {
        //设置数据
        bindValues(position,convertView);
        //统一处理bottomview的点击事件
        if(convertView instanceof SwipeLayout){
            SwipeLayout swipeLayout= (SwipeLayout) convertView;
            View bottomView = swipeLayout.getCurrentBottomView();
            if(bottomView instanceof ViewGroup){
                int childCount = ((ViewGroup) bottomView).getChildCount();
                for(int j=0;j<childCount;j++){
                    ((ViewGroup) bottomView).getChildAt(j).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mOnItemViewClickListener!=null){
                                mOnItemViewClickListener.onItemViewClick(v,position);
                            }
                        }
                    });
                }
            }else{
                bottomView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mOnItemViewClickListener!=null){
                            mOnItemViewClickListener.onItemViewClick(v,position);
                        }
                    }
                });
            }
        }
    }

    /** 判断是否有打开的SwipeLayout
     * @return
     */
    public boolean hasOpenSwipeView(){
        return super.getOpenLayouts().size()>0?true:false;
    }

    /** 设置数据
     * @param position
     * @param convertView
     */
    public abstract void bindValues(int position,View convertView);

    private OnItemViewClickListener mOnItemViewClickListener;

    /** 添加item中子控件的点击事件
     * @param onItemViewClickListener
     */
    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        mOnItemViewClickListener = onItemViewClickListener;
    }

    //将点击事件抛出交由外部处理
    public interface OnItemViewClickListener{

        /**
         * @param view 点击的view
         * @param potion 点击的位置
         */
        void onItemViewClick(View view,int potion);

    }

}
