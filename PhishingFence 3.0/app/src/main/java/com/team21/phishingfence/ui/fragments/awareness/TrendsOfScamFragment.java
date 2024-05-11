package com.team21.phishingfence.ui.fragments.awareness;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.team21.phishingfence.R;
import com.team21.phishingfence.ui.fragments.StatisticalTrendFragment;
import com.team21.phishingfence.viewmodels.StatisticalTrendViewModel;
import com.team21.phishingfence.viewmodels.TrendsOfScamViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrendsOfScamFragment extends Fragment {
    private ImageButton imageButton;

    private Spinner spinner;
//    private PieChart pieChart;
    private LineChart lineChart;
    private BarChart barChart1;
    private BarChart barChart2;
    private TextView description;
    private TrendsOfScamViewModel viewModel;
    private ImageButton imageButtonBack, imageButtonNext;
    private ImageView imageViewHelp;

    public TrendsOfScamFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trends_of_scam, container, false);

        this.imageButton = rootView.findViewById(R.id.imageButton);

        //初始化控件
        this.spinner = rootView.findViewById(R.id.spinner);


        this.barChart1 = rootView.findViewById(R.id.barChart1);
        this.lineChart = rootView.findViewById(R.id.lineChart);
        this.barChart2 = rootView.findViewById(R.id.barChart2);
        this.description = rootView.findViewById(R.id.description);
        this.imageButtonBack = rootView.findViewById(R.id.imageButton);
        this.imageButtonNext = rootView.findViewById(R.id.imageButton2);
        this.imageViewHelp = rootView.findViewById(R.id.imageViewHelp);
        this.description.setTextIsSelectable(true);

        this.viewModel = new ViewModelProvider(requireActivity()).get(TrendsOfScamViewModel.class);//获取ViewModel
        setChooseOptionObserver();//设置chooseOption监视器
        setSpinnerOption();//设置spinner选项
        setSpinnerListener();//设置spinner监听器
        setButtonOnClickListener();//设置按钮监听器

        //绘图
        drawBarChart1(this.barChart1);//图1
        drawLineChart(this.lineChart);//图2
//        drawBarChart2(this.barChart2);//图3

        return rootView;
    }

    private void setSpinnerListener() {
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {//根据用户选择选项改变chooseOption参数
                    case 0 ->
                            TrendsOfScamFragment.this.viewModel.setChooseOption(TrendsOfScamViewModel.BARCHART1);
                    case 1 ->
                            TrendsOfScamFragment.this.viewModel.setChooseOption(TrendsOfScamViewModel.LINECHART2);
                    case 2 ->
                            TrendsOfScamFragment.this.viewModel.setChooseOption(TrendsOfScamViewModel.BARCHART3);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setButtonOnClickListener() {
        setBackButtonListener();

//        this.imageButtonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavController controller = Navigation.findNavController(v);
//                controller.navigate(R.id.action_statisticalTrendFragment_to_scamScenairoFragment);
//            }
//        });

        this.imageButtonNext.setOnClickListener(new View.OnClickListener() {//改变chooseOption参数
            @Override
            public void onClick(View v) {
                TrendsOfScamFragment.this.viewModel.nextChart();
            }
        });

        this.imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String helpInfo = "";
                switch (TrendsOfScamFragment.this.viewModel.getChooseOption().getValue()) {
                    case TrendsOfScamViewModel.BARCHART1 -> helpInfo = requireActivity().getString(R.string.fragment_trend_of_scam_chart1_question);
                    case TrendsOfScamViewModel.LINECHART2 -> helpInfo = requireActivity().getString(R.string.fragment_trend_of_scam_chart2_question);
                    case TrendsOfScamViewModel.BARCHART3 -> helpInfo = requireActivity().getString(R.string.fragment_trend_of_scam_chart3_question);
                }
                new AlertDialog.Builder(requireActivity())
                        .setMessage(helpInfo)
                        .setPositiveButton(R.string.close,null)
                        .create()
                        .show();
            }
        });


    }


    private void setChooseOptionObserver() {//切换视图
        this.viewModel.getChooseOption().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {//当chooseOption参数改变时切换图片与描述,并隐藏其他图片
                    case TrendsOfScamViewModel.BARCHART1 -> {
                        TrendsOfScamFragment.this.barChart1.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.lineChart.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.barChart2.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.description.setText(R.string.fragment_trend_of_scam_description);
                    }
                    case TrendsOfScamViewModel.LINECHART2 -> {
                        TrendsOfScamFragment.this.barChart1.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.lineChart.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.barChart2.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.description.setText(R.string.fragment_trend_of_scam_description2);
                    }
                    case TrendsOfScamViewModel.BARCHART3 -> {
                        TrendsOfScamFragment.this.barChart1.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.lineChart.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.barChart2.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.description.setText(R.string.fragment_trend_of_scam_description3);
                    }
                }
            }
        });
    }

    private void setSpinnerOption() {
        // 设置spinner选项--
        // 后续要改chart_options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.type_of_scam_chart_options, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinner.setAdapter(adapter);
    }

    // 绘图
    private void drawBarChart1(BarChart barChart) { //图1
        List<BarEntry> entries = new ArrayList<>();
        float[] losses = {377, 40, 24, 24, 21, 13, 10, 9, 9, 8}; // 数据点
        for (int i = 0; i < losses.length; i++) {
            entries.add(new BarEntry(i, losses[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Scam Losses(million)");
        dataSet.setColor(getContext().getResources().getColor(R.color.background_color2));
        dataSet.setValueTextColor(getContext().getResources().getColor(R.color.black));
        dataSet.setValueTextSize(10f);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f); // Adjust the bar width

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getXAxis().setDrawGridLines(false); // Disable X-axis grid lines 删除x网格线
        barChart.getAxisLeft().setDrawGridLines(false); // Disable Y-axis grid lines 删除y网格线

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);  // False for scrolling
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getCategories()));
        xAxis.setLabelRotationAngle(-45);
        xAxis.setTextSize(10f);

        // Enable dragging and scaling
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setScaleXEnabled(true);
        barChart.setScaleYEnabled(false);  // Disable vertical scaling
        barChart.setPinchZoom(false);

        barChart.setVisibleXRangeMinimum(3); // Show at least 3 items
        barChart.setVisibleXRangeMaximum(6); // Show at most 6 items at a time
        barChart.moveViewToX(0); // Start at the first entry

        barChart.animateY(1000);
        barChart.invalidate(); // Refresh the chart
    }





    private List<String> getCategories() { // 图1 的标签
        return Arrays.asList("Investment scams", "Dating & romance scams", "False billing",
                "Phishing", "Remote access scams", "Threats to life, arrest or other",
                "Identity theft", "Jobs & employment scams", "Online shopping scams", "Classified scams");
    }

    //图2
    private void drawLineChart11(LineChart lineChart) {
        List<Entry> entries2022 = new ArrayList<>();
        List<Entry> entries2023 = new ArrayList<>();

        // 假设的数据，你需要替换为实际的数据
        float[] data2022 = {33.1f, 37.9f, 34.4f, 37.1f, 51.3f, 37.6f, 42.8f, 44.7f, 43.5f, 49.2f, 51.7f, 43.3f};
        float[] data2023 = {53.3f, 43.2f, 45.3f, 51.5f, 53.3f, 38.1f, 42.4f, 38.6f, 29.0f, 31.3f, 25.7f, 25.1f};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        for (int i = 0; i < data2022.length; i++) {
            entries2022.add(new Entry(i, data2022[i]));
            entries2023.add(new Entry(i, data2023[i]));
        }

        LineDataSet dataSet2022 = new LineDataSet(entries2022, "2022 Losses (million)");
        dataSet2022.setColor(Color.BLUE);
        dataSet2022.setValueTextColor(Color.BLACK);
        dataSet2022.setLineWidth(2f);
        dataSet2022.setCircleRadius(3f);
        dataSet2022.setCircleColor(Color.BLUE);
        dataSet2022.setHighLightColor(Color.RED);  // Color for the highlight indicator

        LineDataSet dataSet2023 = new LineDataSet(entries2023, "2023 Losses (million)");
        dataSet2023.setColor(Color.GREEN);
        dataSet2023.setValueTextColor(Color.BLACK);
        dataSet2023.setLineWidth(2f);
        dataSet2023.setCircleRadius(3f);
        dataSet2023.setCircleColor(Color.GREEN);
        dataSet2023.setHighLightColor(Color.RED);  // Color for the highlight indicator

        LineData lineData = new LineData(dataSet2022, dataSet2023);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.getAxisRight().setEnabled(false); // Disable right Y-axis
        lineChart.animateY(1000);
        lineChart.invalidate(); // Refresh the chart
    }

    private void drawLineChart(LineChart lineChart) {
        List<Entry> entries2022 = new ArrayList<>();
        List<Entry> entries2023 = new ArrayList<>();

        // 数据
        float[] data2022 = {33.1f, 37.9f, 34.4f, 37.1f, 51.3f, 37.6f, 42.8f, 44.7f, 43.5f, 49.2f, 51.7f, 43.3f};
        float[] data2023 = {53.3f, 43.2f, 45.3f, 51.5f, 53.3f, 38.1f, 42.4f, 38.6f, 29.0f, 31.3f, 25.7f, 25.1f};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] percentChanges = {"61.1%", "14.0%", "31.7%", "38.6%", "3.9%", "1.3%", "-1.0%", "-13.7%", "-33.3%", "-36.4%", "-50.3%", "-42.0%"};

        for (int i = 0; i < data2022.length; i++) {
            entries2022.add(new Entry(i, data2022[i]));
            entries2023.add(new Entry(i, data2023[i]));
        }

        LineDataSet dataSet2022 = new LineDataSet(entries2022, "2022 Losses (million)");
        dataSet2022.setColor(Color.BLUE);
        dataSet2022.setCircleColor(Color.BLUE);
        dataSet2022.setDrawValues(false); // 确保不绘制任何文本值

        LineDataSet dataSet2023 = new LineDataSet(entries2023, "2023 Losses (million)");
        dataSet2023.setColor(Color.GREEN);
        dataSet2023.setCircleColor(Color.GREEN);

        dataSet2023.setValueTextSize(12f); // 设置“百分比”字段的字体大小

        dataSet2023.setValueFormatter(new ValueFormatter() {
            @Override
            public String getPointLabel(Entry entry) {
                int index = (int) entry.getX();
                return percentChanges[index]; // 只显示百分比
            }
        });

        LineData lineData = new LineData(dataSet2022, dataSet2023);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);

        lineChart.getXAxis().setDrawGridLines(false); // 不显示X轴网格线
        lineChart.getAxisLeft().setDrawGridLines(false); // 不显示左侧Y轴网格线
        lineChart.getAxisRight().setDrawGridLines(false); // 不显示右侧Y轴网格线

        lineChart.setDrawGridBackground(false); // 不显示网格背景

        lineChart.animateY(1000);
        lineChart.invalidate();
    }







    private void setBackButtonListener() {
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_trendsOfScamFragment_to_scamAwarenessFragment);
            }
        });
    }


}