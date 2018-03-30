package ru.kirillius.spendcar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.kirillius.spendcar.R;
import ru.kirillius.spendcar.interfaces.OnSelectItem;

/**
 * Created by Lavrentev on 30.03.2018.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    Context context;
    private ArrayList<String> listItem;

    public SimpleAdapter(Context context, ArrayList<String> listItem) {
        this.context=context;
        this.listItem=listItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_simple, parent, false);

        ViewHolder vh = new ViewHolder(v, (OnSelectItem)context);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.setItem(listItem.get(position));
        holder.tvItem.setText(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvItem;
        private String item;
        OnSelectItem listenerClick;

        public ViewHolder(View v, OnSelectItem listenerClick) {
            super(v);
            tvItem = v.findViewById(R.id.tvItem);
            this.listenerClick = listenerClick;
            v.setOnClickListener(this);
        }

        public void setItem(String item) {
            this.item = item;
        }

        @Override
        public void onClick(View view) {
            listenerClick.onSelect(item);
        }
    }
}
