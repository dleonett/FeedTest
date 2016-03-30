package com.example.daniel.feedtest;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by afermin on 12-07-2015.
 */
public class HustlerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = HustlerListAdapter.class.getSimpleName();

    private Context mContext;

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        @Bind(R.id.img_banner) public ImageView imgBanner;
        @Bind(R.id.name) public TextView tvName;
        @Bind(R.id.profession) public TextView tvProfession;
        @Bind(R.id.price) public TextView tvPrice;
        @Bind(R.id.skill_type_price) public TextView tvTypePrice;

        public ViewHolderItem(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public HustlerListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v = vi.inflate(R.layout.item_list_hustler, parent, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderItem viewHolderItem = (ViewHolderItem) holder;

        viewHolderItem.tvName.setText("Lorem Ipsum");
        viewHolderItem.tvPrice.setText("$100");
        viewHolderItem.tvTypePrice.setText("Per hour");
        viewHolderItem.tvProfession.setText("Carpenter");
        Glide.with(mContext)
                .load("http://i.imgur.com/OHTOsEM.jpg")
                .error(android.R.drawable.ic_delete)
                .into(viewHolderItem.imgBanner);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

}