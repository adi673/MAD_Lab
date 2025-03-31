package com.example.q3;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.List;

import java.util.Arrays;

import java.util.Comparator;



public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    private Button btnFilter;
    private String originalText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvContent = findViewById(R.id.tvContent);
        btnFilter = findViewById(R.id.btnFilter);

        originalText = tvContent.getText().toString();

        btnFilter.setOnClickListener(this::showFilterMenu);
    }

    private void showFilterMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_filter, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_search:
                    searchKeywords();
                    return true;
                case R.id.menu_highlight:
                    highlightKeywords();
                    return true;
                case R.id.menu_sort_alpha:
                    sortAlphabetically();
                    return true;
                case R.id.menu_sort_relevance:
                    sortByRelevance();
                    return true;
                default:
                    return false;
            }
        });

        popup.show();
    }

    // Function to search keywords
    private void searchKeywords() {
        KeywordInputDialog.show(this, keyword -> {
            if (keyword.isEmpty()) {
                tvContent.setText(originalText);
                Toast.makeText(this, "Enter a keyword!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Highlight the search result
            SpannableString spannableString = new SpannableString(originalText);
            int index = originalText.toLowerCase().indexOf(keyword.toLowerCase());

            if (index != -1) {
                spannableString.setSpan(new BackgroundColorSpan(0xFFFFFF00),
                        index, index + keyword.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvContent.setText(spannableString);
                Toast.makeText(this, "Keyword found: " + keyword, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Keyword not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Function to highlight specific keywords
    private void highlightKeywords() {
        KeywordInputDialog.show(this, keyword -> {
            if (keyword.isEmpty()) {
                Toast.makeText(this, "Enter a word to highlight", Toast.LENGTH_SHORT).show();
                return;
            }

            SpannableString spannableString = new SpannableString(originalText);
            String[] words = originalText.split("\\s+");

            for (int i = 0; i < words.length; i++) {
                if (words[i].equalsIgnoreCase(keyword)) {
                    int start = originalText.indexOf(words[i]);
                    int end = start + words[i].length();
                    spannableString.setSpan(new BackgroundColorSpan(0xFF00FF00),
                            start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            tvContent.setText(spannableString);
            Toast.makeText(this, "Highlighted: " + keyword, Toast.LENGTH_SHORT).show();
        });
    }

    // Function to sort content alphabetically
    private void sortAlphabetically() {
        List<String> words = Arrays.asList(originalText.split("\\s+"));
        Collections.sort(words);
        String sortedText = String.join(" ", words);
        tvContent.setText(sortedText);
        Toast.makeText(this, "Sorted Alphabetically", Toast.LENGTH_SHORT).show();
    }

    // Function to sort content by relevance to search keywords
    private void sortByRelevance() {
        KeywordInputDialog.show(this, keyword -> {
            if (keyword.isEmpty()) {
                Toast.makeText(this, "Enter a keyword for relevance sorting", Toast.LENGTH_SHORT).show();
                return;
            }

            List<String> words = Arrays.asList(originalText.split("\\s+"));
            words.sort(Comparator.comparingInt(word -> -word.toLowerCase().contains(keyword.toLowerCase()) ? 1 : 0));

            String sortedText = String.join(" ", words);
            tvContent.setText(sortedText);
            Toast.makeText(this, "Sorted by relevance to: " + keyword, Toast.LENGTH_SHORT).show();
        });
    }
}