package com.example.opencurtain.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opencurtain.Adapter.ListViewAdapter;
import com.example.opencurtain.Model.SettingListViewItem;
import com.example.opencurtain.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        ListView listView = (ListView) view.findViewById(R.id.setting_list);
        ArrayList<SettingListViewItem> data = new ArrayList<>();
        SettingListViewItem logout = new SettingListViewItem("로그아웃");
        data.add(logout);

        ListViewAdapter adapter = new ListViewAdapter(getActivity(), R.layout.setting_item_list, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    //로그아웃 함수
                }

            }
        });

        return view;
    }

}
