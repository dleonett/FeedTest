package com.example.daniel.feedtest;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by afermin on 12-07-2015.
 */
public class HustlerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = HustlerListAdapter.class.getSimpleName();
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    public static final int ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTARION_VERTICAL = 2;

    private List<Hustler> mItems;
    private OnItemClickListener mListener;
    private int mOrientation;

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        @Bind(R.id.img_banner)
        ImageView imgBanner;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvFavoriteSkill)
        TextView tvProfession;
        @Bind(R.id.price)
        TextView tvPrice;
        @Bind(R.id.tvMoreSkills)
        TextView tvMoreSkills;

        public ViewHolderItem(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public class ViewHolderFooter extends RecyclerView.ViewHolder {

        public ViewHolderFooter(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public HustlerListAdapter(int orientation, OnItemClickListener listener) {
        mItems = new ArrayList<>();

        mItems.add(new Hustler("http://cdn.thesimpledollar.com/wp-content/uploads/2014/11/uber-driver-600x400.jpg", "Daniel Leonett", "Driver with super cars and crazy people", "$80", 4));
        mItems.add(new Hustler("http://i.imgur.com/OHTOsEM.jpg", "Danimir Bermudez", "Developer", "$60", 1));
        mItems.add(new Hustler("http://www.imchef.org/wp-content/uploads/2013/04/chef-exigente-pasion-imchef.jpg", "Daniela Perez", "Chef", "$100", 1));
        mItems.add(new Hustler("http://www.defsa.org.za/sites/default/files/field/image/pro-designers6.jpg", "Rafael Villanueva", "Designer", "$75", 2));
        mItems.add(new Hustler("http://www.conpats.com/wp-content/uploads/2016/07/protestor.jpg", "Jose Saad", "Protester", "$90", 7));
        mItems.add(new Hustler("https://cdn.techinasia.com/wp-content/uploads/2016/03/THUAN-750x422.jpg", "Luis Hernandez", "CTO", "$95", 1));
        mItems.add(new Hustler("https://image.freepik.com/free-photo/doctor-with-co-workers-analyzing-an-x-ray_1098-581.jpg", "Alexander Fermin", "Doctor", "$50", 3));

        mOrientation = orientation;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v;
        if (mOrientation == ORIENTATION_HORIZONTAL) {
            if (viewType == TYPE_ITEM) {
                v = vi.inflate(R.layout.item_list_hustler_small, parent, false);
                return new ViewHolderItem(v);
            } else {
                v = vi.inflate(R.layout.item_list_hustler_small_more, parent, false);
                return new ViewHolderFooter(v);
            }
        } else {
            v = vi.inflate(R.layout.item_list_hustler, parent, false);
            return new ViewHolderItem(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mOrientation == ORIENTATION_HORIZONTAL) {
            if (getItemViewType(position) == TYPE_ITEM) {
                initHustlerInfo(holder, position);
            } else {
                final ViewHolderFooter viewHolderFooter = (ViewHolderFooter) holder;
                viewHolderFooter.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mListener != null) {
                            mListener.onSeeMoreClick();
                        }
                    }
                });
            }
        } else {
            initHustlerInfo(holder, position);
        }
    }

    private void initHustlerInfo(RecyclerView.ViewHolder holder, int position) {
        final Hustler hustler = mItems.get(position);

        final ViewHolderItem viewHolderItem = (ViewHolderItem) holder;

        viewHolderItem.tvName.setText(hustler.getName());
        viewHolderItem.tvPrice.setText(hustler.getPrice());
        viewHolderItem.tvProfession.setText(hustler.getFavoriteSkill());
        if (hustler.getSkillsCount() > 1) {
            viewHolderItem.tvMoreSkills.setVisibility(View.VISIBLE);
            //viewHolderItem.tvMoreSkills.setText((hustler.getSkillsCount() - 1) + " more skills");
            viewHolderItem.tvMoreSkills.setText("More skills");
        } else {
            viewHolderItem.tvMoreSkills.setVisibility(View.GONE);
        }
        Glide.with(viewHolderItem.itemView.getContext())
                .load(hustler.getBanner())
                .error(android.R.drawable.ic_delete)
                .into(viewHolderItem.imgBanner);

        viewHolderItem.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onHustlerClick(hustler);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (mOrientation == ORIENTATION_HORIZONTAL) {
            if (position < mItems.size()) {
                return TYPE_ITEM;
            }
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (mOrientation == ORIENTATION_HORIZONTAL) {
            return mItems.size() + 1;
        }
        return mItems.size();
    }

    public interface OnItemClickListener {
        void onHustlerClick(Hustler hustler);

        void onSeeMoreClick();
    }

}