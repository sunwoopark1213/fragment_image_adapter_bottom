package com.example.fragment_image_adapter_bottom;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * MainActivity는 BottomNavigationView를 관리하고 각 프래그먼트로 전환합니다.
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BottomNavigationView 초기화
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.navigation_dashboard) {
                    selectedFragment = new DashboardFragment();
                } else if (item.getItemId() == R.id.navigation_notifications) {
                    selectedFragment = new NotificationsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("extraData", "Some extra data");
                    selectedFragment.setArguments(bundle);
                }
                // 현재 프래그먼트를 선택된 프래그먼트로 교체
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                return true;
            }
        });

        // 처음 시작할 때 홈 프래그먼트를 표시
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }
}
