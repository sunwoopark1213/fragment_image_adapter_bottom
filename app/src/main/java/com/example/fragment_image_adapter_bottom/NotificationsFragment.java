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

/**
 * NotificationsFragment는 리스트 뷰와 스와이프 새로고침을 포함합니다.
 */
public class NotificationsFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] items = {"알림 항목 1", "알림 항목 2", "알림 항목 3"};
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // 스와이프 새로고침 레이아웃 설정
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        listView = view.findViewById(R.id.list_view);

        // 리스트 뷰 설정
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
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

        // 리스트 항목 길게 클릭 시 다이얼로그 표시
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setTitle("항목 길게 클릭됨")
                        .setMessage("길게 클릭한 항목: " + items[position])
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                return true;
            }
        });

        // 스와이프 새로고침 동작 설정
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });

        return view;
    }
}
