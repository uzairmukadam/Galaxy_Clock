package com.uzitech.galaxyclock.HomeDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.uzitech.galaxyclock.R;

import java.io.File;
import java.util.ArrayList;

public class DigitalClockDialog extends Dialog {

    TextView day, date;
    TextClock clock;
    Activity activity;
    SharedPreferences preferences;

    Spinner font_selector;
    Button day_color_selector, date_color_selector, clock_color_selector, done;

    public DigitalClockDialog(Activity activity, TextClock clock, TextView day, TextView date) {
        super(activity);
        this.activity = activity;
        this.clock = clock;
        this.day = day;
        this.date = date;
        preferences = activity.getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.digital_clock_settings);

        font_selector = findViewById(R.id.font_selector);
        day_color_selector = findViewById(R.id.day_color_selector);
        date_color_selector = findViewById(R.id.date_color_selector);
        clock_color_selector = findViewById(R.id.clock_color_selector);
        done = findViewById(R.id.done);

        prepareFontSelector();
        day_color_selector.setOnClickListener(v -> startColorPicker(day));
        date_color_selector.setOnClickListener(v -> startColorPicker(date));
        clock_color_selector.setOnClickListener(v -> startColorPicker());
        done.setOnClickListener(v -> dismiss());
    }

    private void prepareFontSelector() {
        ArrayList<String> fonts = new ArrayList<>();
        File temp = new File("/system/fonts/");
        String fontSuffix = ".ttf";

        fonts.add("Select a Font");
        for (File font : temp.listFiles()) {
            String fontName = font.getName();
            if (fontName.endsWith(fontSuffix)) {
                fonts.add(fontName.subSequence(0, fontName.lastIndexOf(fontSuffix)).toString());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, fonts);
        font_selector.setAdapter(adapter);

        font_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    clock.setTypeface(Typeface.createFromFile("/system/fonts/" + fonts.get(position) + ".ttf"));
                    day.setTypeface(Typeface.createFromFile("/system/fonts/" + fonts.get(position) + ".ttf"));
                    date.setTypeface(Typeface.createFromFile("/system/fonts/" + fonts.get(position) + ".ttf"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void saveColor(int color) {
        SharedPreferences.Editor editor = preferences.edit();
        /*switch (preferences.getInt("clock_mode", 0)) {
            case 0:
                editor.putInt("amoled_dclock_color", color);
                break;
            case 2:
                editor.putInt("photo_dclock_color", color);
                break;
        }*/
        editor.apply();
    }

    private void startColorPicker(TextView textView) {
        ColorPickerDialogBuilder
                .with(activity)
                .setTitle("Choose color")
                .initialColor(ContextCompat.getColor(activity, android.R.color.white))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(3)
                .setOnColorSelectedListener(selectedColor -> Log.d("COLOR", "onColorSelected: 0x" + Integer.toHexString(selectedColor)))
                .setPositiveButton("ok", (dialog, selectedColor, allColors) -> {
                    textView.setTextColor(selectedColor);
                    saveColor(selectedColor);
                })
                .setNegativeButton("cancel", (dialog, which) -> {
                })
                .build()
                .show();
    }

    private void startColorPicker() {
        ColorPickerDialogBuilder
                .with(activity)
                .setTitle("Choose color")
                .initialColor(ContextCompat.getColor(activity, android.R.color.white))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(3)
                .setOnColorSelectedListener(selectedColor -> Log.d("COLOR", "onColorSelected: 0x" + Integer.toHexString(selectedColor)))
                .setPositiveButton("ok", (dialog, selectedColor, allColors) -> {
                    clock.setTextColor(selectedColor);
                    saveColor(selectedColor);
                })
                .setNegativeButton("cancel", (dialog, which) -> {
                })
                .build()
                .show();
    }
}
