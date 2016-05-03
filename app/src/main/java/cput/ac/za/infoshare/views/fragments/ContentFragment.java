package cput.ac.za.infoshare.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.CustomAdapter.PublishedContentViewAdapter;
import cput.ac.za.infoshare.R;
import cput.ac.za.infoshare.domain.content.PublishedContent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements FloatingActionButton.OnClickListener, AdapterView.OnItemClickListener {


    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_content, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        //
        PublishedContentViewAdapter adapter = new PublishedContentViewAdapter(this.getActivity(),testTable());
        ListView contentListView = (ListView) view.findViewById(R.id.content_list_view);

        contentListView.setAdapter(adapter);
        contentListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //  view.setSelected(true);
        Snackbar.make(view, "Replace with your own action "+position+" content id is "+id, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    private List<PublishedContent> testTable() {
        String cont = " Using any type of tobacco puts you on a collision course with cancer." +
                " Smoking has been linked to various types of cancer — including cancer of the lung, bladder," +
                " cervix and kidney. And chewing tobacco has been linked to cancer of the oral cavity and pancreas." +
                " Even if you don't use tobacco, exposure to secondhand smoke might increase your risk of lung cancer." +
                "Avoiding tobacco — or deciding to stop using it — is one of the most important health decisions you can make." +
                " It's also an important part of cancer prevention. If you need help quitting tobacco," +
                " ask your doctor about stop-smoking products and other strategies for quitting.";
        List<PublishedContent> contents = new ArrayList<>();
        PublishedContent content= new PublishedContent.Builder()
                .id(KeyGenerator.getEntityId())
                .category("uncategorized")
                .creator("thulebona Hadebe")
                .source("mobile")
                .category("uncategorized")
                .content(cont)
                .contentType("Text")
                .org("CPUT")
                .status("raw")
                .title("HIV prevention")
                .dateCreated(new Date()).build();

        for (int i=0; i<10; i++)
            contents.add(content);
        return contents;
    }
}
