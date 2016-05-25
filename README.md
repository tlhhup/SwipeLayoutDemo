# SwipeLayoutDemo
结合github大神的swipelayout控件写的小案例

  1. 使用swipelayout处理侧滑删除
  2. 在adapter中间listview的item的点击事件采用回调的方式将事件抛出
  3. 注意listview的item中需要使用swipelayout时，必须将swipelayout作为根布局，不然listview的item点击事件无效(目前还不知道什么原因)
