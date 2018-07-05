package com.example.a4_day1;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private DrawerLayout drawerLayout;
    private ImageView menuIv;
    private ImageView changeIv;
    private ListView leftLv;
    private LinearLayoutManager manager;
    private List<InfoBean.DataBean> mDatas;
    private ListRvAdapter listRvAdapter;
    public boolean isList = true;
    public String url = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=10&page=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.main_rv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuIv = (ImageView) findViewById(R.id.title_menu);
        changeIv = (ImageView) findViewById(R.id.title_change_iv);
        leftLv = (ListView) findViewById(R.id.left_lv);

        //设置左边抽屉的功能
        List<String>list=new ArrayList<>();
        list.add("新闻");
        list.add("专题");
        list.add("组图");
        list.add("互动");
        list.add("投票");
        LeftAdapter leftAdapter = new LeftAdapter(this, list);
        leftLv.setAdapter(leftAdapter);

//        设置RecyclerView
//        1.设置布局管理者
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);
//        数据源
        mDatas = new ArrayList<>();
//        设置适配器
        listRvAdapter = new ListRvAdapter(this,mDatas);
        rv.setAdapter(listRvAdapter);
//        加载数据
        loadWebData();
    }
    /**
     * 加载网络数据的方法
     * */
    private void loadWebData() {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
                String content = HttpUtils.getStringContent(url);
                return content;}
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s!=null&&!s.isEmpty()) {
                    Gson gson = new Gson();
                    InfoBean bean = gson.fromJson(s, InfoBean.class);
                    List<InfoBean.DataBean> list = bean.getData();
                    mDatas.addAll(list);
                    listRvAdapter.notifyDataSetChanged();
                }}}.execute();}

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.title_menu://打开左边抽屉
                drawerLayout.openDrawer(Gravity.LEFT);
            break;
            case R.id.title_change_iv:
                if (isList) {
                    GridLayoutManager gridManger = new GridLayoutManager(this,2);
                    rv.setLayoutManager(gridManger);
                    isList = false;
                    changeIv.setImageResource(R.mipmap.icon_pic_list_type);
                }else{
                    rv.setLayoutManager(manager);
                    isList = true;
                    changeIv.setImageResource(R.mipmap.icon_pic_grid_type);
                }
                break;
        }
    }
}
