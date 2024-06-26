package com.example.phishingfence.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.phishingfence.R;
import com.deepl.api.*;
import com.example.phishingfence.viewmodel.TranslationViewModel;

import java.util.Objects;

public class TranslationFragment extends Fragment
{
    private TranslationViewModel translationViewModel;
    private Spinner sourceLangSpinner;
    private Spinner targetLangSpinner;
    private EditText mEditText;
    private ImageView btn_translate;
    private TextView translatedText;


    public TranslationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化ViewModel
        translationViewModel = new ViewModelProvider(this).get(TranslationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_translation, container, false);

        //初始化控件
        this.sourceLangSpinner = rootview.findViewById(R.id.source_lang);
        this.targetLangSpinner = rootview.findViewById(R.id.target_lang);
        this.mEditText = rootview.findViewById(R.id.ed_Text);
        this.btn_translate = rootview.findViewById(R.id.check);
        this.translatedText = rootview.findViewById(R.id.text_TranslatedText);

        setSpinnerOption();//设置选择语言选项

        setupClickListeners();//设置点击监听器
        setupEditTextListener();//设置输入框监听器

        return rootview;
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        if(hidden)
        {
            //当Fragment被隐藏时清空用户输入
            if(mEditText != null)
            {
                mEditText.setText("");
            }
        }
    }

    private void setupClickListeners()
    {
        setupTranslateButtonListener();
    }

    private void setupTranslateButtonListener()
    {
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String editText = mEditText.getText().toString();
                //获取并转换用户选择的语言选项
                String sourceLang = langOption(sourceLangSpinner.getSelectedItem().toString());
                //获取并转换用户选择的语言选项
                String targetLang = langOption(targetLangSpinner.getSelectedItem().toString());
                translate(editText,sourceLang,targetLang);//翻译
            }
        });
    }

    private void translate(String editText,String sourceLang,String targetLang)
    {
        if(!TextUtils.isEmpty(editText))
        {
            // 请求翻译并提供回调
            translationViewModel.translateText(editText, sourceLang ,targetLang, new TranslationViewModel.TranslationCallback() {
                @Override
                public void onTranslationResult(String translatedTextResult) {
                    translatedText.setText(translatedTextResult);
                }

                @Override
                public void onTranslationError(Exception e) {
                    // 错误处理，显示错误信息
                    Toast.makeText(getActivity(),"failure",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            //如果用户没有输入，则提示用户输入
            Toast.makeText(getActivity(),"Please enter the content you want to translate",Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinnerOption()
    {
        // 创建一个ArrayAdapter使用默认的spinner布局和语言数组
        ArrayAdapter<CharSequence> sourceLangAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.source_languages, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> targetLangAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.target_languages, android.R.layout.simple_spinner_item);

        // 指定下拉列表的默认布局
        sourceLangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        targetLangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 应用适配器到Spinner
        sourceLangSpinner.setAdapter(sourceLangAdapter);
        targetLangSpinner.setAdapter(targetLangAdapter);
    }

    private String langOption(String option)
    {
        //参考https://developers.deepl.com/docs/v/zh/api-reference/translate/openapi-spec-for-text-translation
        if(Objects.equals(option, "Chinese"))
        {
            return "ZH";
        } else if (Objects.equals(option, "English (British)")) {
            return "EN-GB";
        } else if (Objects.equals(option,"English (American)")) {
            return "EN-US";
        } else if (Objects.equals(option,"Russian")) {
            return "RU";
        }else if(Objects.equals(option,"French"))
        {
            return "FR";
        }
        //转换用户选择语言
        return null;
    }

    private void setupEditTextListener()
    {
        //设置输入框监听器
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 如果输入框为空，设置translatedText为默认文本
                if (s.length() == 0) {
                    translatedText.setText(" Here is your result..."); // 替换为您的默认文本资源
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}