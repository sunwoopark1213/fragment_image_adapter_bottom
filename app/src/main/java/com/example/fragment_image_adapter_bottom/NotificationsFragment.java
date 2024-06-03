package com.example.fragment_image_adapter_bottom;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class NotificationsFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] items = {"Notification Item 1", "Notification Item 2", "Notification Item 3"};
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Bundle에서 데이터 읽어오기
        Bundle bundle = getArguments();
        if (bundle != null) {
            String extraData = bundle.getString("extraData");
            // extraData 사용
        }

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        listView = view.findViewById(R.id.list_view);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("item", items[position]);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Item Long Clicked")
                        .setMessage("You long-clicked: " + items[position])
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                return true;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 새로고침 작업을 여기서 수행합니다.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        // 예: 데이터를 다시 로드하거나 업데이트
                        adapter.notifyDataSetChanged();
                    }
                }, 2000); // 2초 동안 새로고침 상태 유지
            }
        });

        return view;
    }
}
