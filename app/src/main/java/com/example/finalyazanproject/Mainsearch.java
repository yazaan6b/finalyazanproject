package com.example.finalyazanproject;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Mainsearch extends AppCompatActivity {
    private TextView tvSearch;
    private EditText etSearch;
    private RecyclerView recyclerResults;
    private TextView resultsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainsearch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        tvSearch = findViewById(R.id.tvSearch);
        recyclerResults = findViewById(R.id.recyclerResults);
        resultsTitle = findViewById(R.id.resultsTitle);
        etSearch = findViewById(R.id.etSearch);
    }
}