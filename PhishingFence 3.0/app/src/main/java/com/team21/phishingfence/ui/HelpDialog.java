package com.team21.phishingfence.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.team21.phishingfence.R;

public class HelpDialog extends Dialog {

    private final String helpText;

    public HelpDialog(Context context, String helpText) {
        // 使用一个主题，其中对话框背景透明
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.helpText = helpText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.help_dialog);

        TextView textView = findViewById(R.id.helpText);
        textView.setText(helpText);

        Button gotItButton = findViewById(R.id.gotItButton);
        gotItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭对话框
            }
        });
    }
}
