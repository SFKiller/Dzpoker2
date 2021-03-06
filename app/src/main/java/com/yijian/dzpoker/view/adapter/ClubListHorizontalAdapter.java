package com.yijian.dzpoker.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.yijian.dzpoker.activity.club.ClubChatActivity;
import com.yijian.dzpoker.util.DzApplication;
import com.yijian.dzpoker.view.CircleTransform;
import com.yijian.dzpoker.view.data.ClubInfo;

/**
 * Created by koyabr on 10/22/15.
 */
public class ClubListHorizontalAdapter extends BaseListAdapter<ClubInfo, ClubListHorizontalAdapter.ViewHolder> {

    public ClubListHorizontalAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_club_list_horizontal, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ClubInfo clubInfo = mData.get(position);
        if (clubInfo == null) return;
        if (clubInfo.clubHeadPic!=null && !clubInfo.clubHeadPic.equals("")) {
            Picasso.with(mContext)
                    .load(clubInfo.clubHeadPic)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .resize(100, 100)
                    .error(R.drawable.default_club_head)
                    .transform(new CircleTransform())
                    .into(holder.mClubHead);
        }

        holder.mClubName.setText(clubInfo.clubName);


    }

    static class ViewHolder extends RecyclerView.ViewHolder  {

        public View mView;
        public ImageView mClubHead;
        public TextView mClubName;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mClubHead=(ImageView) itemView.findViewById(R.id.iv_club_head);
            mClubName=(TextView) itemView.findViewById(R.id.tv_club_name);

        }
    }



}

