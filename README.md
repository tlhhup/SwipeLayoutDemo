# SwipeLayoutDemo
结合github大神的swipelayout控件写的小案例

  1. 使用swipelayout处理侧滑删除
  2. 在adapter中间listview的item的点击事件采用回调的方式将事件抛出
  3. 注意listview的item中需要使用swipelayout时，必须将swipelayout作为根布局，不然listview的item点击事件无效(目前还不知道什么原因)
  4. 添加swipeListViewApater统一处理BottomView的点击事件,通过接口回掉到需要的地方
  5. 添加SwipeListView，目前尚未处理滑动时先关闭所有打开的swipeLayout(还希望各大神指教)

