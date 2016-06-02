package com.example.chayenjr.alibampos;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Chayenjr on 2/6/2559.
 */
public class List extends ListFragment {

    Intent i;

    String[] listitems = {
            "Activity 1",
            "Activity 2",
            "Activity 3"
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitems));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        switch(position){
            case 0:
                i = new Intent(getActivity(), Activity1.class);
                break;
//            case 1:
//                i = new Intent(getActivity(), Activity2.class);
//                break;
//            case 2:
//                i = new Intent(getActivity(), Activity3.class);
//                break;
        }
        startActivity(i);
    }
}