package com.example.hppc.mymovie.User;

/**
 * Created by HP PC on 25-04-2018.
 */

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hppc.mymovie.R;

import java.util.List;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById( R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);
        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;

        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);
        }
    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSeat;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;
        public EmptyViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<AbstractItem> mItems;
    private List<Integer> disbled;
    private List<Integer> selected;

    public AirplaneAdapter(Context context, List<AbstractItem> items,List<Integer> d) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
        disbled = d;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;

            if( disbled.contains(position )){
                holder.imgSeat.setEnabled(false);
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    toggleSelection(position);

                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                    //Toast.makeText(mContext,"Position Center: "+position,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext,"" + getSelectedItems().toString(),Toast.LENGTH_SHORT).show();
                    SeatSelect.b = getSelectedItems();
                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);


        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            if( disbled.contains(position )){
                holder.imgSeat.setEnabled(false);
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleSelection(position);
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                    //Toast.makeText(mContext,"Position Edge: "+position,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext,"" + getSelectedItems().toString(),Toast.LENGTH_SHORT).show();

                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        }else if (type == AbstractItem.TYPE_EMPTY) {
            final EmptyItem item = (EmptyItem) mItems.get(position);
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;

            if( disbled.contains(position )){
                holder.imgSeat.setEnabled(false);
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleSelection(position);
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                    //Toast.makeText(mContext,"Position Edge: "+position,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext,"" + getSelectedItems().toString(),Toast.LENGTH_SHORT).show();

                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        }

    }
    public List<Integer> listSeatSelected(){
        selected = getSelectedItems();
        return selected;
    }
}