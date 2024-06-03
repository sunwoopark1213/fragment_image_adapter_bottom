package com.example.fragment_image_adapter_bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * HomeFragment는 이미지와 리스트 뷰를 포함합니다.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private String[] items = {"홈 항목 1", "홈 항목 2", "홈 항목 3"};
    private ImageView homeImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 이미지 뷰 설정
        homeImage = view.findViewById(R.id.home_image);
        homeImage.setImageResource(R.drawable.ic_home);

        // 리스트 뷰 설정
        listView = view.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // 리스트 항목 클릭 시 상세 보기 액티비티로 전환
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("item", items[position]);
                startActivity(intent);
            }
        });

        return view;
    }
}
