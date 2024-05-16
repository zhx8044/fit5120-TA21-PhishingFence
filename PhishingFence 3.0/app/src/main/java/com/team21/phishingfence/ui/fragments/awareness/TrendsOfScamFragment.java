package com.team21.phishingfence.ui.fragments.awareness;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Paint;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
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
    private BarChart barChart3;
    private TextView description;
    private TrendsOfScamViewModel viewModel;
    private ImageButton imageButtonNext;
    private ImageView imageViewHelp;
    private TextView xAxisLabel1, yAxisLabel1, xAxisLabel2, yAxisLabel2, xAxisLabel3, yAxisLabel3, xAxisLabel4, yAxisLabel4;

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
        this.barChart3 = rootView.findViewById(R.id.barChart3);
        this.description = rootView.findViewById(R.id.description);
        this.imageButtonNext = rootView.findViewById(R.id.imageButton2);
        this.imageViewHelp = rootView.findViewById(R.id.imageViewHelp);
        this.xAxisLabel1 = rootView.findViewById(R.id.xAxisLabel1);
        this.yAxisLabel1 = rootView.findViewById(R.id.yAxisLabel1);
        this.xAxisLabel2 = rootView.findViewById(R.id.xAxisLabel2);
        this.yAxisLabel2 = rootView.findViewById(R.id.yAxisLabel2);
        this.xAxisLabel3 = rootView.findViewById(R.id.xAxisLabel3);
        this.yAxisLabel3 = rootView.findViewById(R.id.yAxisLabel3);
        this.xAxisLabel4 = rootView.findViewById(R.id.xAxisLabel4);
        this.yAxisLabel4 = rootView.findViewById(R.id.yAxisLabel4);
        this.description.setTextIsSelectable(true);

        this.viewModel = new ViewModelProvider(requireActivity()).get(TrendsOfScamViewModel.class);//获取ViewModel


        // 向下滚动提示
        ScrollView scrollView = rootView.findViewById(R.id.scrollView);
        TextView scrollUpIndicator = rootView.findViewById(R.id.scrollUpIndicator);
//        TextView scrollDownIndicator = rootView.findViewById(R.id.scrollDownIndicator);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (!scrollView.canScrollVertically(1)) {
                // 如果滚动到顶部，则隐藏向上滚动提示
                scrollUpIndicator.setVisibility(View.GONE);
            } else {
                scrollUpIndicator.setVisibility(View.VISIBLE);
            }
        });


        setChooseOptionObserver();//设置chooseOption监视器
        setSpinnerOption();//设置spinner选项
        setSpinnerListener();//设置spinner监听器
        setButtonOnClickListener();//设置按钮监听器

        //绘图
        drawBarChart1(this.barChart1);//图1
        drawLineChart(this.lineChart);//图2
        drawBarChart2_1(this.barChart2);//图3-1
        drawBarChart2_2(this.barChart3);//图3-2

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

    private void setButtonOnClickListener11() {
        setBackButtonListener();

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

    private void setButtonOnClickListener111() {
        setBackButtonListener();

        this.imageButtonNext.setOnClickListener(new View.OnClickListener() {//改变chooseOption参数
            @Override
            public void onClick(View v) {
                TrendsOfScamFragment.this.viewModel.nextChart();
                int nextOption = TrendsOfScamFragment.this.viewModel.getChooseOption().getValue() - 1; // 更新选择项
                TrendsOfScamFragment.this.spinner.setSelection(nextOption);
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
                        .setPositiveButton(R.string.close, null)
                        .create()
                        .show();
            }
        });
    }

    private void setButtonOnClickListener() {
        setBackButtonListener();

        this.imageButtonNext.setOnClickListener(new View.OnClickListener() {//改变chooseOption参数
            @Override
            public void onClick(View v) {
                TrendsOfScamFragment.this.viewModel.nextChart();
                int nextOption = TrendsOfScamFragment.this.viewModel.getChooseOption().getValue() - 1; // 更新选择项
                TrendsOfScamFragment.this.spinner.setSelection(nextOption);
            }
        });

        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentOption = TrendsOfScamFragment.this.viewModel.getChooseOption().getValue();
                if (currentOption == TrendsOfScamViewModel.BARCHART1) {
                    // 如果当前是第一张图表，返回上一级
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_trendsOfScamFragment_to_scamAwarenessFragment);
                } else {
                    // 否则显示上一张图表
                    TrendsOfScamFragment.this.viewModel.previousChart();
                    int previousOption = TrendsOfScamFragment.this.viewModel.getChooseOption().getValue() - 1; // 更新选择项
                    TrendsOfScamFragment.this.spinner.setSelection(previousOption);
                }
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
                        .setPositiveButton(R.string.close, null)
                        .create()
                        .show();
            }
        });
    }




    private void setChooseOptionObserver1() {//切换视图
        this.viewModel.getChooseOption().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {//当chooseOption参数改变时切换图片与描述,并隐藏其他图片
                    case TrendsOfScamViewModel.BARCHART1 -> {
                        TrendsOfScamFragment.this.barChart1.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.lineChart.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.barChart2.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.barChart3.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.description.setText(R.string.fragment_trend_of_scam_description);
                    }
                    case TrendsOfScamViewModel.LINECHART2 -> {
                        TrendsOfScamFragment.this.barChart1.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.lineChart.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.barChart2.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.barChart3.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.description.setText(R.string.fragment_trend_of_scam_description2);
                    }
                    case TrendsOfScamViewModel.BARCHART3 -> {
                        TrendsOfScamFragment.this.barChart1.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.lineChart.setVisibility(View.GONE);
                        TrendsOfScamFragment.this.barChart2.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.barChart3.setVisibility(View.VISIBLE);
                        TrendsOfScamFragment.this.description.setText(R.string.fragment_trend_of_scam_description3);
                    }
                }
            }
        });
    }

    private void setChooseOptionObserver() {
        this.viewModel.getChooseOption().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case TrendsOfScamViewModel.BARCHART1 -> {
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.VISIBLE);
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.GONE);
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.GONE);
                        setChartVisibility(barChart3, xAxisLabel4, yAxisLabel4, View.GONE);
                        description.setText(R.string.fragment_trend_of_scam_description);
                    }
                    case TrendsOfScamViewModel.LINECHART2 -> {
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.GONE);
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.VISIBLE);
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.GONE);
                        setChartVisibility(barChart3, xAxisLabel4, yAxisLabel4, View.GONE);
                        description.setText(R.string.fragment_trend_of_scam_description2);
                    }
                    case TrendsOfScamViewModel.BARCHART3 -> {
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.GONE);
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.GONE);
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.VISIBLE);
                        setChartVisibility(barChart3, xAxisLabel4, yAxisLabel4, View.VISIBLE);
                        description.setText(R.string.fragment_trend_of_scam_description3);
                    }
                }
            }
        });
    }

    private void setChartVisibility(View chart, TextView xAxisLabel, TextView yAxisLabel, int visibility) {
        chart.setVisibility(visibility);
        if (xAxisLabel != null) xAxisLabel.setVisibility(visibility);
        if (yAxisLabel != null) yAxisLabel.setVisibility(visibility);
    }

    private void setSpinnerOption() {
        // 设置spinner选项-
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
    private void drawLineChart1(LineChart lineChart) {
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


    private void drawLineChart(LineChart lineChart) {
        List<Entry> entries2022 = new ArrayList<>();
        List<Entry> entries2023 = new ArrayList<>();

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
        dataSet2022.setDrawValues(false);

        LineDataSet dataSet2023 = new LineDataSet(entries2023, "2023 Losses (million)");
        dataSet2023.setColor(Color.GREEN);
        dataSet2023.setCircleColor(Color.GREEN);
        dataSet2023.setValueTextSize(12f);
        dataSet2023.setValueFormatter(new ValueFormatter() {
            @Override
            public String getPointLabel(Entry entry) {
                int index = (int) entry.getX();
                return percentChanges[index]; // Display percentage only for 2023
            }
        });

        LineData lineData = new LineData(dataSet2022, dataSet2023);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setValueFormatter(new LargeValueFormatter()); // Format values in the Y-axis to show millions or thousands appropriately

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateY(1000);
        lineChart.invalidate();
    }


    private void drawBarChart2_11(BarChart barChart) {
        // 示例数据
        String[] contactModes = {"Phone Call", "Social Media", "Email", "Internet", "Mobile apps", "In Person", "Text Message"};
        float[] reports2022 = {63816, 13427, 52159, 13692, 10057, 2186, 79835};
        float[] reports2023 = {55418, 17542, 85941, 17568, 8101, 3614, 109621};

        List<BarEntry> entries2022 = new ArrayList<>();
        List<BarEntry> entries2023 = new ArrayList<>();

        for (int i = 0; i < contactModes.length; i++) {
            // 注意i的使用，它将每个条目正确地放在X轴的位置上
            entries2022.add(new BarEntry(i, reports2022[i]));
            entries2023.add(new BarEntry(i + 0.4f, reports2023[i])); // 添加0.4的偏移创建分组效果
        }

        // 创建2022年数据的集合
        BarDataSet dataSet2022 = new BarDataSet(entries2022, "2022 Reports");
        dataSet2022.setColor(Color.BLUE);

        // 创建2023年数据的集合
        BarDataSet dataSet2023 = new BarDataSet(entries2023, "2023 Reports");
        dataSet2023.setColor(Color.GREEN);

        // 使用BarData对象组合所有数据集
        BarData barData = new BarData(dataSet2022, dataSet2023);
        barData.setBarWidth(0.35f); // 设置条目宽度

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setAxisMinimum(0); // 开始于0
        barChart.getAxisLeft().setEnabled(false); // Disable left axis labels
        barChart.getAxisRight().setEnabled(false); // Disable right axis

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(contactModes));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelRotationAngle(-45); // 将标签旋转以防止重叠
        xAxis.setLabelCount(contactModes.length); // Set explicit label count to match number of entries

        // Remove grid lines
        xAxis.setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);

        barChart.setFitBars(true); // 使两个条形不超过图表边界
        barChart.animateY(1500);
        barChart.invalidate(); // 刷新图表
    }

    private void drawBarChart2_1(BarChart barChart) {
        // 示例数据
        String[] contactModes = {"Phone Call", "Social Media", "Email", "Internet", "Mobile apps", "In Person", "Text Message"};
        float[] reports2022 = {63816, 13427, 52159, 13692, 10057, 2186, 79835};
        float[] reports2023 = {55418, 17542, 85941, 17568, 8101, 3614, 109621};

        List<BarEntry> entries2022 = new ArrayList<>();
        List<BarEntry> entries2023 = new ArrayList<>();

        for (int i = 0; i < contactModes.length; i++) {
            // 注意i的使用，它将每个条目正确地放在X轴的位置上
            entries2022.add(new BarEntry(i, reports2022[i]));
            entries2023.add(new BarEntry(i + 0.4f, reports2023[i])); // 添加0.4的偏移创建分组效果
        }

        // 创建2022年数据的集合
        BarDataSet dataSet2022 = new BarDataSet(entries2022, "2022 Reports");
        dataSet2022.setColor(Color.BLUE);

        // 创建2023年数据的集合
        BarDataSet dataSet2023 = new BarDataSet(entries2023, "2023 Reports");
        dataSet2023.setColor(Color.GREEN);

        // 使用BarData对象组合所有数据集
        BarData barData = new BarData(dataSet2022, dataSet2023);
        barData.setBarWidth(0.35f); // 设置条目宽度

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);

        // 开始于0的左侧Y轴
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0); // 从0开始
        leftAxis.setEnabled(true); // 启用左侧Y轴显示

        // 禁用右侧Y轴显示
        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(contactModes));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelRotationAngle(-45); // 将标签旋转以防止重叠
        xAxis.setLabelCount(contactModes.length); // Set explicit label count to match number of entries

        // Remove grid lines
        xAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);

        barChart.setFitBars(true); // 使两个条形不超过图表边界
        barChart.animateY(1500);
        barChart.invalidate(); // 刷新图表
    }


    private void drawBarChart2_2(BarChart barChart) {
        // 示例数据
        String[] contactModes = {"Phone Call", "Social Media", "Email", "Internet", "Mobile apps", "In Person", "Text Message"};
        float[] losses2022 = {141.0f, 80.2f, 77.3f, 73.5f, 71.7f, 30.6f, 28.5f};
        float[] losses2023 = {116.0f, 93.5f, 80.0f, 69.7f, 64.8f, 21.5f, 26.9f};

        List<BarEntry> entries2022 = new ArrayList<>();
        List<BarEntry> entries2023 = new ArrayList<>();

        for (int i = 0; i < contactModes.length; i++) {
            // 注意i的使用，它将每个条目正确地放在X轴的位置上
            entries2022.add(new BarEntry(i, losses2022[i]));
            entries2023.add(new BarEntry(i + 0.4f, losses2023[i])); // 添加0.4的偏移创建分组效果
        }

        // 创建2022年数据的集合
        BarDataSet dataSet2022 = new BarDataSet(entries2022, "2022 Losses (million)");
        dataSet2022.setColor(Color.BLUE);

        // 创建2023年数据的集合
        BarDataSet dataSet2023 = new BarDataSet(entries2023, "2023 Losses (million)");
        dataSet2023.setColor(Color.GREEN);

        // 使用BarData对象组合所有数据集
        BarData barData = new BarData(dataSet2022, dataSet2023);
        barData.setBarWidth(0.35f); // 设置条目宽度

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setAxisMinimum(0); // 开始于0
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(contactModes));
        barChart.getXAxis().setLabelRotationAngle(-45); // 设置标签倾斜角度为-45度
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getAxisRight().setEnabled(false);
        barChart.setFitBars(true); // 使两个条形不超过图表边界
        barChart.getXAxis().setDrawGridLines(false); // 不显示X轴网格线
        barChart.getAxisLeft().setDrawGridLines(false); // 不显示左侧Y轴网格线

        barChart.animateY(1500);
        barChart.invalidate(); // 刷新图表
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