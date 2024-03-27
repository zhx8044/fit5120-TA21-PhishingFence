package com.example.phishingfence.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phishingfence.R;
import com.example.phishingfence.localdb.HistoryDbHelper;
import com.example.phishingfence.model.HistoryInfo;
import com.example.phishingfence.model.NewsInfo;
import com.example.phishingfence.ui.activities.NewsDetailesActivity;
import com.example.phishingfence.ui.adapters.HistoryListAdapter;
import com.example.phishingfence.ui.adapters.NewsListAdapter;
import com.example.phishingfence.viewmodel.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment
{
    private ImageView btnBack;
    private OnHistoryFragmentInteractionListener mOnHistoryFragmentInteractionListener;
    private RecyclerView historyRecyclerView;
    private HistoryListAdapter mHistoryListAdapter;
    private HistoryViewModel historyViewModel;

    public HistoryFragment()
    {
        // Required empty public constructor
    }

    public interface OnHistoryFragmentInteractionListener
    {
        void onBackClick();
    }

    public void setOnHistoryFragmentInteractionListener(OnHistoryFragmentInteractionListener onHistoryFragmentInteractionListener) {
        this.mOnHistoryFragmentInteractionListener = onHistoryFragmentInteractionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        historyViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HistoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_history, container, false);

        //初始化控件
        this.btnBack = rootview.findViewById(R.id.img_back);
        this.historyRecyclerView = rootview.findViewById((R.id.historyRecyclerView));

        this.mHistoryListAdapter = new HistoryListAdapter();
        this.historyRecyclerView.setAdapter(mHistoryListAdapter);
        updateList();//更新历史记录列表

        setupClickListeners();

        return rootview;
    }

    private void setupClickListeners()
    {
        setupBackButtonListener();
        setupRecyclerViewClickListeners();
    }

    private void setupBackButtonListener()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnHistoryFragmentInteractionListener!=null)
                {
                    mOnHistoryFragmentInteractionListener.onBackClick();
                }
            }
        });
    }

    private void setupRecyclerViewClickListeners()
    {
        //recyclerView点击事件应该放在这里
        mHistoryListAdapter.setOnItemClickListener(new HistoryListAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(HistoryInfo historyInfo) {
                // 处理点击事件，启动新的新闻详情Activity
                Intent intent = new Intent(getActivity(), NewsDetailesActivity.class);
                //intent传地对象的时候，实体类实现Serializable
                NewsInfo newsInfo = new NewsInfo(0,historyInfo.getTitle(),"","",historyInfo.getDetailUrl(),historyInfo.getNewsImage());
                intent.putExtra("newsInfo",newsInfo);
                startActivity(intent);
            }

            @Override
            public void delOnCLick(HistoryInfo historyInfo) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("tips")
                        .setMessage("Confirm whether to delete the record")
                        .setPositiveButton("sure",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog,int which)
                            {
                                HistoryDbHelper.getInstance(getActivity()).delete(historyInfo.getHistory_id()+"");
                                updateList();
                            }
                        })
                        .setNegativeButton("cancel",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog,int which)
                            {

                            }
                        })
                        .show();
            }
        });
    }

    //更新历史记录列表
    public void updateList()
    {
        Runnable updateListRunnable = new Runnable() {
            @Override
            public void run() {
                // 根据id获取HistoryViewModel
                historyViewModel.loadHistoryForUser("zsan");
                List<HistoryInfo> historyList = historyViewModel.getHistoryInfoList();

                // 创建一个用于更新UI的Runnable
                Runnable updateUiRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (isAdded()) { // 再次检查Fragment是否附加到Activity
                            mHistoryListAdapter.setListData(historyList);
                            mHistoryListAdapter.notifyDataSetChanged();
                        }
                    }
                };

                // 现在将这个Runnable传递给runOnUiThread
                if(getActivity() != null) {
                    getActivity().runOnUiThread(updateUiRunnable);
                }
            }
        };

        new Thread(updateListRunnable).start();
    }
}