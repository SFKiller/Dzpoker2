package com.yijian.dzpoker.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.yijian.dzpoker.R;
import com.yijian.dzpoker.activity.MainActivity;
import com.yijian.dzpoker.activity.MainFragmentActivity;
import com.yijian.dzpoker.activity.user.GamesRecordActivity;
import com.yijian.dzpoker.activity.user.ModifyUserInfoActivity;
import com.yijian.dzpoker.activity.user.StoreActivity;
import com.yijian.dzpoker.activity.user.SysConfigActivity;
import com.yijian.dzpoker.constant.Constant;
import com.yijian.dzpoker.util.DzApplication;
import com.yijian.dzpoker.view.CircleTransform;
import com.yijian.dzpoker.view.adapter.MenuAdapter;
import com.yijian.dzpoker.view.data.MenuItemData;
import com.yijian.dzpoker.view.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rabby on 2017/8/10.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private List<MenuItemData> mMenu=new ArrayList<MenuItemData>();
    private  int mMenuId[]={R.drawable.collection,R.drawable.award_ico,R.drawable.record_ico,R.drawable.store_ico,R.drawable.customer_service,R.drawable.set_up};
    private String mMenuText[]={"牌谱收藏","奖励中心","战绩","商店","客服","设置"};
    private RecyclerView rv_menu_list;
    private MenuAdapter mAdapter;
    private User mUser=new User();
    private ImageView iv_user_head;
    private TextView tv_user_name,tv_user_abstract,tv_goldcoin,tv_diamond,tv_user_level;

    private static final int REQUEST_CODE = 0;

    private void updateUI(){
        mUser=((DzApplication)getActivity().getApplication()).getUser();

        if (mUser.userHeadPic!=null && !mUser.userHeadPic.equals("")){
            Picasso.with(getActivity())
                    .load(mUser.userHeadPic)
                    .placeholder(R.drawable.default_male_head)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .resize(100, 100)
                    .error(R.drawable.default_club_head)
                    .transform(new CircleTransform())
                    .into(iv_user_head);
        }
        tv_user_name.setText(mUser.nickName);
        tv_user_abstract.setText(mUser.personalTip);
        tv_goldcoin.setText(mUser.goldcoin+"");
        tv_diamond.setText(mUser.diamond+"");
        tv_user_level.setText(mUser.levelname);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View meLayout = inflater.inflate(R.layout.fragment_me_layout,
                container, false);

        iv_user_head=(ImageView) meLayout.findViewById(R.id.iv_user_head);
        tv_user_name=(TextView) meLayout.findViewById(R.id.tv_user_name);
        tv_user_abstract=(TextView) meLayout.findViewById(R.id.tv_user_abstract);
        tv_goldcoin=(TextView) meLayout.findViewById(R.id.tv_goldcoin);
        tv_diamond=(TextView) meLayout.findViewById(R.id.tv_diamond);
        tv_user_level=(TextView) meLayout.findViewById(R.id.tv_user_level);
        iv_user_head.setOnClickListener(this);

        //初始化菜单选项
        mMenu=new ArrayList<MenuItemData>();
        for(int i=0;i<6;i++){
            MenuItemData menuItemData=new MenuItemData();
            menuItemData.menuId=i+1;
            menuItemData.menuImageId=mMenuId[i];
            menuItemData.menuText=mMenuText[i];
            mMenu.add(menuItemData);
        }

        mAdapter = new MenuAdapter(getActivity(), new MenuAdapter.OnRecordSelectListener() {
            @Override
            public void onRecordSelected(final MenuItemData menuItemData) {

                //获取该页面的title, 传递给下级页面.
                String title = ((MainFragmentActivity)getActivity()).getPageTitle();

                //点击菜单胡处理
                switch (menuItemData.menuId){

                    case 1://牌谱收藏
                        break;
                    case 2://奖励中心
                        break;
                    case 3://战绩
                        Intent recordIntent = new Intent();
                        recordIntent.setClass(getActivity(), GamesRecordActivity.class);
                        recordIntent.putExtra(Constant.INTENT_KEY_BACKTEXT, title);
                        recordIntent.putExtra(Constant.INTENT_KEY_TITLE,mMenuText[menuItemData.menuId-1]);
                        startActivity(recordIntent);
                        break;
                    case 4://商店
                        Intent iStore = new Intent();
                        iStore.setClass(getActivity(), StoreActivity.class);
                        iStore.putExtra(Constant.INTENT_KEY_BACKTEXT, title);
                        iStore.putExtra(Constant.INTENT_KEY_TITLE,mMenuText[menuItemData.menuId-1]);
                        startActivityForResult(iStore,REQUEST_CODE);
                        break;
                    case 5://客服
                        break;
                    case 6://设置
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), SysConfigActivity.class);
                        intent.putExtra(Constant.INTENT_KEY_BACKTEXT, title);
                        intent.putExtra(Constant.INTENT_KEY_TITLE,mMenuText[menuItemData.menuId-1]);
                        startActivityForResult(intent,REQUEST_CODE);
                        break;
                }

            }
        });
        rv_menu_list=(RecyclerView)  meLayout.findViewById(R.id.rv_menu_list);
        rv_menu_list.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rv_menu_list.setHasFixedSize(true);
        /**
        rv_menu_list.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        rv_menu_list.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));**/

        rv_menu_list.setAdapter(mAdapter);
        mAdapter.setData(mMenu);

        updateUI();
        return meLayout;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        MainActivity.currFragTag = Constant.FRAGMENT_FLAG_ME;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_user_head:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ModifyUserInfoActivity.class);
                String title = ((MainFragmentActivity)getActivity()).getPageTitle();
                intent.putExtra(Constant.INTENT_KEY_BACKTEXT, title);
                startActivityForResult(intent,1);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //从本界面打开奖励中心，商店，修改用户信息回来之后都要重新获得用户数据
        if ( requestCode == REQUEST_CODE){
            updateUI();
        }
    }
}
