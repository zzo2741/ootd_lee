package com.example.ootd.ootd_1.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ootd.ootd_1.Adapter.ImageViewAdapter;
import com.example.ootd.ootd_1.R;
import com.example.ootd.ootd_1.model.ShowImage_Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ScrollFragment extends Fragment {

    String[] resultURLArray;
    ImageViewAdapter adapter;
    private ArrayList<ShowImage_Model> images = new ArrayList<>();
    SharedPreferences sp;

    public ScrollFragment() {
        Log.d("ScrollFragment", "SCROLL OPEN");
        // Required empty public constructor
        if (getArguments() != null) {
            resultURLArray = getArguments().getStringArray("result");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycle_scroll, container, false);


        sp = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.scroll_recycler_view);
        recyclerView.setHasFixedSize(true);
        HashSet<String> strings;
        strings = (HashSet) sp.getStringSet("result", null);
        if (strings != null) {
            images.clear();
            Iterator it = strings.iterator();
            while (it.hasNext()) {
                images.add(new ShowImage_Model("title", (String) it.next(), ""));
            }
        }
        //리니어
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageViewAdapter(context, images);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return view;
    }

    public void updateView() {
        images.clear();

        if (sp != null) {
            HashSet<String> strings = (HashSet) sp.getStringSet("result", null);
            if (strings != null) {
                Iterator it = strings.iterator();
                while (!it.hasNext()) {
                    images.add(new ShowImage_Model("title", (String) it.next(), "memo"));
                }
                adapter.notifyDataSetChanged();
            }
        }

    }
}


    /*사용할 데이터를 미리 준비해 놓는다. 준비하는 형태에 따라 구현방법이 조금 달라 질 수 있다.
    1. 수동으로 Item을 입력해서 추가 하도록 할 수 있다.
    2. 온라인에서 DB에서 일괄 가져 올 수 도 있다.
    어떻게든 itmes 배열에 데이터를 형식에 맞게 넣어 어답터와 연결만 하면 화면에 내용이 출력될것이다.

    private void initDataset() {
        images.clear();
//        images.add(new ShowImage_Model("01", "https://firebasestorage.googleapis.com/v0/b/ootd1-954e1.appspot.com/o/image%2F2.jpg?alt=media&token=16ae082a-a1ed-4e0e-994c-a68fbfa72212", "hi babo"));
//        images.add(new ShowImage_Model("02", "https://firebasestorage.googleapis.com/v0/b/ootd1-954e1.appspot.com/o/image%2F4.jpg?alt=media&token=47f3f167-9d89-414d-ba74-527fac0daac3", "hi babo2"));
//        images.add(new ShowImage_Model("03", "https://firebasestorage.googleapis.com/v0/b/ootd1-954e1.appspot.com/o/image%2F6.jpg?alt=media&token=f34d4145-a520-4471-8e94-2cf62555e2a0", "hi babo3"));
    }

    public void initDataset_notnull(String[] imgURLArray, ImageViewAdapter adapter){
        String[] imageURL = imgURLArray;
        images.clear();

        if(imageURL != null) {
            for(int i = 0; i<imageURL.length; i++){
                images.add(new ShowImage_Model("image", imageURL[i], "memo"));
            }
            adapter.notifyDataSetChanged();
        }

    }
    public void changeDataSet(String[] arr) {
        images.clear();

        for(int i = 0; i<arr.length; i++){
            images.add(new ShowImage_Model("image", arr[i], "memo"));
        }
        adapter.notifyDataSetChanged();
    }

     */

