package com.example.rezorpayment.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rezorpayment.R;
import com.example.rezorpayment.model.User;
import com.example.rezorpayment.services.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Dash extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName;
    private TextView txtWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


       // navigationView.setItemIconTintList(null);
       // navHeader = navigationView.getHeaderView(0);

        //txtName = (TextView) navHeader.findViewById(R.id.name);
        //imgNavHeaderBg = navHeader.findViewById(R.id.imageView);
        User user = SharedPrefManager.getInstance(this).getUser();

      //  txtName.setText(user.getName());
        System.out.println(user.getEmail()+"pramod");





        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_notification:
                Intent Notification = new Intent(this, Notifications.class);
                startActivity(Notification);
                break;
            case R.id.nav_setting:
                Intent intent1 = new Intent(this, Setting.class);
                startActivity(intent1);
                break;

            case R.id.menuLogout:
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                finish();
                // startActivity(new Intent(this, SignIn.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }








    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}