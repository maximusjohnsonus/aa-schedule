package doop.aa_schedule;


import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public EventAdapter(LayoutInflater inflater){
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public JSONObject getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Resources r = convertView.getResources();

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.event_in_list, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.event_list_title);
            holder.description = (TextView) convertView.findViewById(R.id.event_list_description);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        JSONObject jsonObject = getItem(position);

        holder.title.setText(jsonObject.optString(r.getString(R.string.JSON_event_title)));

        if (jsonObject.has(r.getString(R.string.JSON_event_description))) {
            holder.description.setText(jsonObject.optString(r.getString(R.string.JSON_event_description)));
        }

        return convertView;
    }
    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    // this is used so you only ever have to do
    // inflation and finding by ID once ever per View
    private static class ViewHolder {
        public TextView title;
        public TextView description;
    }
}
