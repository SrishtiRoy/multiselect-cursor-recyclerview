package com.roy.adapter;

/**
 * Created by sristi on 26/4/16.
 */
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.roy.multiselect.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactAdapter extends CursorRecyclerAdpter<ContactAdapter.SearchResultViewHolder> implements View.OnClickListener

{
    private Context context;
    private Cursor cursor;
    int[] androidColors;
    private  ArrayList<String > selectedItems=new ArrayList<String>();
    private AdapterView.OnItemClickListener

    public ContactAdapter(Context context, Cursor cursor) {

        super(context, cursor);

        androidColors = context.getResources().getIntArray(R.array.androidcolors);
    }



    @Override
    public SearchResultViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, Cursor cursor) {

        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        holder.imgView.setBackgroundColor(randomAndroidColor);
        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        holder.textViewName.setText(name);
        holder.tvNumber.setText(number);



    }



    /*
     * View.OnClickListener
     */

    /*@Override
    public void onClick(final View view)
    {
        if (this.onItemClickListener != null)
        {
            final RecyclerView recyclerView = (RecyclerView) view.getParent();
            final int position = recyclerView.getChildLayoutPosition(view);
            if (position != RecyclerView.NO_POSITION)
            {
                final Cursor cursor = this.getItem(position);
                this.onItemClickListener.onItemClicked(cursor);
            }
        }
    }*/

    public static class SearchResultViewHolder extends RecyclerView.ViewHolder  {
        @Bind(R.id.contact_name)
        TextView textViewName;
        @Bind(R.id.contact_number)
        TextView tvNumber;
        @Bind(R.id.recent_item_pic)
        RoundedImageView imgView;
        @Bind(R.id.chkSelcected)
        CheckBox chkSelected;



        public SearchResultViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final Cursor cursor) {
            final String name = cursor.getString(cursor.getColumnIndex("name"));
            this.textViewName.setText(name);
        }


        }

    public ArrayList<String> getSelectedItems() {


        return selectedItems;
    }

    @Override
    public void onClick(final View view)
    {
        if (this.onItemClickListener != null)
        {
            final RecyclerView recyclerView = (RecyclerView) view.getParent();
            final int position = recyclerView.getChildLayoutPosition(view);
            if (position != RecyclerView.NO_POSITION)
            {
                final Cursor cursor = this.getItem(position);
                this.onItemClickListener.onItemClicked(cursor);
            }
        }
    }
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
}
