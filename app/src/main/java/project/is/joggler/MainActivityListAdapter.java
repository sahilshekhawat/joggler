package project.is.joggler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityListAdapter extends RecyclerView.Adapter<MainActivityListAdapter.ViewHolder> {

    private Context context;
    ArrayList<MainActivityListItem> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitle;
        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title);
        }
    }

    public MainActivityListAdapter(Context context, ArrayList<MainActivityListItem> items) {
//        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
        this.mDataSet = items;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public MainActivityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view

        //RecyclerView.Adapter
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //TextView mTitleTextView = (TextView) v.findViewById(R.id.title);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitle.setText(mDataSet.get(position).getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



/*    private class ViewHolder{
        TextView titleText;
    }*/

/*    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        MainActivityListItem fragmentItemListItem = (MainActivityListItem)getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.activity_main_list_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.title);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        holder.titleText.setText(fragmentItemListItem.getTitle()); return viewToUse;
    }*/
}
