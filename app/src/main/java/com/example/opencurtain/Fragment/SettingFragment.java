package com.example.opencurtain.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.opencurtain.Activity.IntroductionActivity;
import com.example.opencurtain.Adapter.ListViewAdapter;
import com.example.opencurtain.Model.SettingListViewItem;
import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private APIRequest settingRequest;

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

        //            settingRequest = new APIRequest(API.logout, Method.GET);
        settingRequest = APIRequest.getInstance();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    try {
                        settingRequest.execute(API.logout.getEndPoint(), Method.GET, new RequestHandler() {
                            @Override
                            public void onRequestOK(JSONObject jsonObject) {
                                Toast.makeText(getContext(),"로그아웃 되었습니다",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), IntroductionActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onRequestErr(int code) {
                                Toast.makeText(getContext(), "Logout Failed", Toast.LENGTH_LONG).show();
                                Log.e("Setting Fragment", String.format("HTTP Response code: %d", code));
                            }
                        });
                    } catch(MalformedURLException me) {
                        me.printStackTrace();
                    }
                }

            }
        });

        return view;
    }

}
