package com.example.luxevistaresort;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    VideoView videoView;
    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        pass = intent.getStringExtra("password");

    }
    public void logout(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void checkRoomsBtn(View view){
        Intent intent = new Intent(this, RoomsViewActivity.class);
        startActivity(intent);
    }

    public void bookingHistoryBtn(View view){
        Intent intent = new Intent(this, ViewBookingsHistory.class);
        startActivity(intent);
    }

    public void servicesBtn(View view){
        Intent intent = new Intent(this, ServicesViewActivity.class);
        startActivity(intent);
    }

    public void ViewOffers(View view){
        Intent intent = new Intent(this, ViewOffers.class);
        startActivity(intent);
    }

    public void manageAcc (View view){
        Intent intent = new Intent(this, ManageUserActivity.class);
        intent.putExtra("username", user);
        intent.putExtra("password", pass);
        startActivity(intent);


    }


}