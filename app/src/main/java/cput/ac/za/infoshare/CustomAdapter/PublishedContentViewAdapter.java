package cput.ac.za.infoshare.CustomAdapter;

/**
 * Created by user9 on 2016/04/21.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import cput.ac.za.infoshare.R;
import cput.ac.za.infoshare.domain.content.PublishedContent;


public class PublishedContentViewAdapter extends BaseAdapter
{

    LayoutInflater inflater;
    List<PublishedContent> items;

    public PublishedContentViewAdapter(Activity context, List<PublishedContent> items) {
        super();
        this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PublishedContent item = items.get(position);
        View view = convertView;

        if(convertView==null)
            view = inflater.inflate(R.layout.published_content_adapter, null);
        TextView cont_Title = (TextView) view.findViewById(R.id.cont_Title);
        TextView cont_creator = (TextView) view.findViewById(R.id.cont_creator);
        TextView cont_preview = (TextView) view.findViewById(R.id.cont_preveiw);
        TextView cont_dateCreated = (TextView) view.findViewById(R.id.cont_dateCreated);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
        cont_Title.setText(item.getTitle().toUpperCase());
        cont_creator.setText(item.getCreator());
        cont_preview.setText(item.getContent().substring(0,48)+"....");
        cont_dateCreated.setText(format.format(item.getDateCreated()));
        return view;
    }
}