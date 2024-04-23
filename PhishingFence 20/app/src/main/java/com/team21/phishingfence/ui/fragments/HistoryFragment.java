package com.team21.phishingfence.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.team21.phishingfence.R;
import com.team21.phishingfence.models.HistoryInfo;
import com.team21.phishingfence.models.NewsInfo;
import com.team21.phishingfence.ui.activities.NewsDetailsActivity;
import com.team21.phishingfence.ui.adapters.HistoryInfoAdapter;
import com.team21.phishingfence.viewmodels.HistoryInfoViewmodel;

import java.util.List;

public class HistoryFragment extends Fragment {
    private ImageButton imageButton;
    private RecyclerView recyclerView;
    private HistoryInfoAdapter historyListAdapter;
    private HistoryInfoViewmodel historyViewModel;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);
        this.recyclerView = rootView.findViewById(R.id.historyRecyclerView);
        this.historyListAdapter = new HistoryInfoAdapter();
        this.recyclerView.setAdapter(historyListAdapter);
        this.historyViewModel = new ViewModelProvider(requireActivity()).get(HistoryInfoViewmodel.class);

        historyViewModel.getAllHistoryInfosLive().observe(getViewLifecycleOwner(), historyInfos -> {
            historyListAdapter.setListData(historyInfos);
        });

        setButtonOnClickListener();
        setAdapterListeners();

        return rootView;
    }

    private void setButtonOnClickListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_historyFragment_to_newsFeedFragment);
            }
        });
    }

    private void setAdapterListeners() {
        historyListAdapter.setOnItemClickListener(new HistoryInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HistoryInfo historyInfo) {
                // 处理点击事件，启动新的新闻详情Activity
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //intent传地对象的时候，实体类实现Serializable
                NewsInfo newsInfo = new NewsInfo(0,historyInfo.getTitle(),"","",historyInfo.getDetailUrl(),historyInfo.getNewsImage());
                intent.putExtra("newsInfo",newsInfo);
                startActivity(intent);
            }

            @Override
            public void delOnCLick(HistoryInfo historyInfo) {
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.fragment_history_deleteDialog_title)
                        .setMessage(R.string.fragment_history_deleteDialog_message)
                        .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                            historyViewModel.deleteHistoryInfos(historyInfo);
                            Toast.makeText(getContext(), getString(R.string.fragment_history_deleteToast), Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
    }
}