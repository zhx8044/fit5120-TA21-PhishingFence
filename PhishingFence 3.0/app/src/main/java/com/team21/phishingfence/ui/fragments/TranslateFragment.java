package com.team21.phishingfence.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.team21.phishingfence.R;
import com.team21.phishingfence.viewmodels.TranslateViewModel;

import java.util.Objects;

public class TranslateFragment extends Fragment {
    private Spinner spinnerSourceLang, spinnerTargetLang;
    private EditText editText;
    private ImageView imageViewCheck, imageViewCancel,imageViewVerify;
    private TextView textView;
    private TranslateViewModel viewModel;

    public TranslateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);
        this.spinnerSourceLang = rootView.findViewById(R.id.spinnerSourceLang);
        this.spinnerTargetLang = rootView.findViewById(R.id.spinnerTargetLang);
        this.editText = rootView.findViewById(R.id.editTextText);
        this.imageViewCancel = rootView.findViewById(R.id.imageViewCancel);
        this.imageViewCheck = rootView.findViewById(R.id.imageViewCheck);
        this.textView = rootView.findViewById(R.id.textViewResult);
        this.textView.setTextIsSelectable(true);
        this.imageViewVerify = rootView.findViewById(R.id.imageViewVerify);

        this.viewModel = new ViewModelProvider(requireActivity()).get(TranslateViewModel.class);
        //从ViewModel处获取用户输入和之前翻译结果
        this.editText.setText(this.viewModel.getTextToTranslate());
        if (this.viewModel.getTranslatedText() != null) {//如果之前有翻译内容，则显示之前翻译内容
            this.textView.setText(this.viewModel.getTranslatedText());
        }

        setSpinnerOption();//设置语言选项
        setImageViewCheckOnClickListner();//设置确认按钮监听器
        setImageViewCancelOnClickListener();//设置删除按钮监听器
        setEditTextChangeListener();//设置输入框监听器
        setButtonOnClickListener();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.imageViewVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个Bundle对象来包含需要传递的数据
                Bundle bundle = new Bundle();
                bundle.putString("message to verify", TranslateFragment.this.viewModel.getTextToTranslate());

                // 使用NavController进行跳转并传递Bundle
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_translateFragment_to_verifyScamFragment, bundle);
            }
        });
    }

    private void setSpinnerOption() {
        if (getContext() != null) {
            // 创建一个ArrayAdapter使用默认的spinner布局和语言数组
            ArrayAdapter<CharSequence> sourceLangAdapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.source_languages, R.layout.custom_spinner_item);
            ArrayAdapter<CharSequence> targetLangAdapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.target_languages, R.layout.custom_spinner_item);

            // 指定下拉列表的默认布局
            sourceLangAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
            targetLangAdapter.setDropDownViewResource(R.layout.custom_spinner_item);

            // 应用适配器到Spinner
            this.spinnerSourceLang.setAdapter(sourceLangAdapter);
            this.spinnerTargetLang.setAdapter(targetLangAdapter);
        }
    }

    private void setImageViewCheckOnClickListner() {
        this.imageViewCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(TranslateFragment.this.editText.getText())) {//检测用户是否输入
                    return;
                }
                closeKeyboard(v);//关闭键盘
                //转换用户选择语言选项为对应语言代码
                String sourceLang = langSourceOption(TranslateFragment.this.spinnerSourceLang.getSelectedItem().toString());
                String targetLang = langTargetOption(TranslateFragment.this.spinnerTargetLang.getSelectedItem().toString());

                TranslateFragment.this.viewModel.translate(sourceLang, targetLang);//ViewModel执行翻译

                String result = TranslateFragment.this.viewModel.getTranslatedText();//获取翻译结果
                Toast.makeText(requireActivity(), R.string.fragment_translate_toast, Toast.LENGTH_SHORT).show();//提示用户翻译已完成
                TranslateFragment.this.textView.setText(result);//在textView上显示翻译结果
            }

            private void closeKeyboard(View v) {
                //关闭键盘
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && v != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

    private void setImageViewCancelOnClickListener() {
        this.imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将输入框内文字删除
                TranslateFragment.this.editText.setText(null);
            }
        });
    }

    private void setEditTextChangeListener() {
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {//如果用户删除全部内容则重置TextView中内容
                    TranslateFragment.this.textView.setText(R.string.fragment_translate_text3);
                }
                //将用户输入或删除后数据传入viewModel
                TranslateFragment.this.viewModel.setTextToTranslate(s.toString());
            }
        });
    }

    private String langSourceOption(String option) {
        //参考https://developers.deepl.com/docs/v/zh/api-reference/translate/openapi-spec-for-text-translation
        if (Objects.equals(option, "中文")) {
            return "ZH";
        } else if (Objects.equals(option, "English")) {
            return "EN";
        } else if (Objects.equals(option, "한국")) {
            return "KO";
        } else if (Objects.equals(option, "Français")) {
            return "FR";
        } else if (Objects.equals(option, "Português")) {
            return "PT";
        } else if (Objects.equals(option, "日本語")) {
            return "JA";
        } else if (Objects.equals(option, "Melayu dan Indonesia")) {
            return "ID";
        } else if (Objects.equals(option, "Русский")) {
            return "RU";
        } else if (Objects.equals(option, "Español")) {
            return "ES";
        } else if (Objects.equals(option, "Deutsch")) {
            return "DE";
        } else if (Objects.equals(option, "Italiano")) {
            return "IT";
        } else {
            //转换用户选择语言
            return null;
        }
    }

    private String langTargetOption(String option) {
        //参考https://developers.deepl.com/docs/v/zh/api-reference/translate/openapi-spec-for-text-translation
        if (Objects.equals(option, "中文")) {
            return "ZH";
        } else if (Objects.equals(option, "English")) {
            return "EN-US";
        } else if (Objects.equals(option, "한국")) {
            return "KO";
        } else if (Objects.equals(option, "Français")) {
            return "FR";
        } else if (Objects.equals(option, "Português")) {
            return "PT-PT";
        } else if (Objects.equals(option, "日本語")) {
            return "JA";
        } else if (Objects.equals(option, "Melayu dan Indonesia")) {
            return "ID";
        } else if (Objects.equals(option, "Русский")) {
            return "RU";
        } else if (Objects.equals(option, "Español")) {
            return "ES";
        } else if (Objects.equals(option, "Deutsch")) {
            return "DE";
        } else if (Objects.equals(option, "Italiano")) {
            return "IT";
        } else {
            return null;
        }
        //转换用户选择语言
    }
}
