package com.team21.phishingfence.ui.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.team21.phishingfence.R;
import com.team21.phishingfence.viewmodels.StatisticalTrendViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticalTrendFragment extends Fragment {
    private Spinner spinner;
    private PieChart pieChart;
    private LineChart lineChart;
    private BarChart barChart1;
    private BarChart barChart2;
    private TextView description;
    private StatisticalTrendViewModel viewModel;
    private ImageButton imageButtonBack, imageButtonNext;
    private ImageView imageViewHelp;

    private TextView xAxisLabel1, yAxisLabel1, xAxisLabel2, yAxisLabel2, xAxisLabel3, yAxisLabel3;

    public StatisticalTrendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statistical_trend, container, false);

        //初始化控件
        this.spinner = rootView.findViewById(R.id.spinner);
        this.pieChart = rootView.findViewById(R.id.pieChart);
        this.lineChart = rootView.findViewById(R.id.lineChart);
        this.barChart1 = rootView.findViewById(R.id.barChart1);
        this.barChart2 = rootView.findViewById(R.id.barChart2);
        this.description = rootView.findViewById(R.id.description);
        this.imageButtonBack = rootView.findViewById(R.id.imageButton);
        this.imageButtonNext = rootView.findViewById(R.id.imageButton2);
        this.imageViewHelp = rootView.findViewById(R.id.imageViewHelp);
        this.xAxisLabel1 = rootView.findViewById(R.id.xAxisLabel1);
        this.yAxisLabel1 = rootView.findViewById(R.id.yAxisLabel1);
        this.xAxisLabel2 = rootView.findViewById(R.id.xAxisLabel2);
        this.yAxisLabel2 = rootView.findViewById(R.id.yAxisLabel2);
        this.xAxisLabel3 = rootView.findViewById(R.id.xAxisLabel3);
        this.yAxisLabel3 = rootView.findViewById(R.id.yAxisLabel3);
        this.description.setTextIsSelectable(true);

        this.viewModel = new ViewModelProvider(requireActivity()).get(StatisticalTrendViewModel.class);//获取ViewModel
        setChooseOptionObserver();//设置choosenOption监视器
        setSpinnerOption();//设置spinner选项
        setSpinnerListener();//设置spinner监听器
        setButtonOnClickListener();//设置按钮监听器

        //绘图
        drawPopulationPieChart(this.pieChart);//图1
        drawDataTrendLineChart(this.lineChart);//图2
        drawComparisonBarChart(this.barChart1);//图3
        drawAuthoritiesBarChart(this.barChart2);//图4

        return rootView;
    }

    private void setSpinnerListener() {
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {//根据用户选择选项改变choosenOption参数
                    case 0 ->
                            StatisticalTrendFragment.this.viewModel.setChoosenOption(StatisticalTrendViewModel.PIECHART1);
                    case 1 ->
                            StatisticalTrendFragment.this.viewModel.setChoosenOption(StatisticalTrendViewModel.LINECHART2);
                    case 2 ->
                            StatisticalTrendFragment.this.viewModel.setChoosenOption(StatisticalTrendViewModel.BARCHART3);
                    case 3 ->
                            StatisticalTrendFragment.this.viewModel.setChoosenOption(StatisticalTrendViewModel.BARCHART4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setButtonOnClickListener11() {//设置返回按钮和next chart按钮监听器
        this.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_statisticalTrendFragment_to_scamScenairoFragment);
            }
        });

        this.imageButtonNext.setOnClickListener(new View.OnClickListener() {//改变choosenOption参数
            @Override
            public void onClick(View v) {
                StatisticalTrendFragment.this.viewModel.nextChart();
            }
        });

        this.imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String helpInfo = "";
                switch (StatisticalTrendFragment.this.viewModel.getChoosenOption().getValue()) {
                    case StatisticalTrendViewModel.PIECHART1 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart1_question);
                    case StatisticalTrendViewModel.LINECHART2 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart2_question);
                    case StatisticalTrendViewModel.BARCHART3 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart3_question);
                    case StatisticalTrendViewModel.BARCHART4 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart4_question);
                }
                new AlertDialog.Builder(requireActivity())
                        .setMessage(helpInfo)
                        .setPositiveButton(R.string.close,null)
                        .create()
                        .show();
            }
        });
    }
    private void setButtonOnClickListener() {//设置返回按钮和next chart按钮监听器
        this.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_statisticalTrendFragment_to_scamScenairoFragment);
            }
        });

        this.imageButtonNext.setOnClickListener(new View.OnClickListener() {//改变choosenOption参数
            @Override
            public void onClick(View v) {
                StatisticalTrendFragment.this.viewModel.nextChart();
                int nextOption = StatisticalTrendFragment.this.viewModel.getChoosenOption().getValue() - 1; // 更新选择项
                StatisticalTrendFragment.this.spinner.setSelection(nextOption);
            }
        });

        this.imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String helpInfo = "";
                switch (StatisticalTrendFragment.this.viewModel.getChoosenOption().getValue()) {
                    case StatisticalTrendViewModel.PIECHART1 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart1_question);
                    case StatisticalTrendViewModel.LINECHART2 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart2_question);
                    case StatisticalTrendViewModel.BARCHART3 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart3_question);
                    case StatisticalTrendViewModel.BARCHART4 -> helpInfo = requireActivity().getString(R.string.fragment_statistical_trend_chart4_question);
                }
                new AlertDialog.Builder(requireActivity())
                        .setMessage(helpInfo)
                        .setPositiveButton(R.string.close,null)
                        .create()
                        .show();
            }
        });
    }

    private void setChooseOptionObserver11() {//切换视图
        this.viewModel.getChoosenOption().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {//当choosenOption参数改变时切换图片与描述,并隐藏其他图片
                    case StatisticalTrendViewModel.PIECHART1 -> {
                        StatisticalTrendFragment.this.pieChart.setVisibility(View.VISIBLE);
                        StatisticalTrendFragment.this.lineChart.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.barChart1.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.barChart2.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.description.setText(R.string.fragment_statistical_trend_chart1_description);
                    }
                    case StatisticalTrendViewModel.LINECHART2 -> {
                        StatisticalTrendFragment.this.lineChart.setVisibility(View.VISIBLE);
                        StatisticalTrendFragment.this.pieChart.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.barChart1.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.barChart2.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.description.setText(R.string.fragment_statistical_trend_chart2_description);
                    }
                    case StatisticalTrendViewModel.BARCHART3 -> {
                        StatisticalTrendFragment.this.barChart1.setVisibility(View.VISIBLE);
                        StatisticalTrendFragment.this.pieChart.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.lineChart.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.barChart2.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.description.setText(R.string.fragment_statistical_trend_chart3_description);
                    }
                    case StatisticalTrendViewModel.BARCHART4 -> {
                        StatisticalTrendFragment.this.barChart2.setVisibility(View.VISIBLE);
                        StatisticalTrendFragment.this.pieChart.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.lineChart.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.barChart1.setVisibility(View.GONE);
                        StatisticalTrendFragment.this.description.setText(R.string.fragment_statistical_trend_chart4_description);
                    }
                }
            }
        });
    }

    private void setChooseOptionObserver() {
        this.viewModel.getChoosenOption().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case StatisticalTrendViewModel.PIECHART1 -> {
                        setChartVisibility(pieChart, null, null, View.VISIBLE);
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.GONE);
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.GONE);
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.GONE);
                        description.setText(R.string.fragment_statistical_trend_chart1_description);
                    }
                    case StatisticalTrendViewModel.LINECHART2 -> {
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.VISIBLE);
                        setChartVisibility(pieChart, null, null, View.GONE);
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.GONE);
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.GONE);
                        description.setText(R.string.fragment_statistical_trend_chart2_description);
                    }
                    case StatisticalTrendViewModel.BARCHART3 -> {
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.VISIBLE);
                        setChartVisibility(pieChart, null, null, View.GONE);
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.GONE);
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.GONE);
                        description.setText(R.string.fragment_statistical_trend_chart3_description);
                    }
                    case StatisticalTrendViewModel.BARCHART4 -> {
                        setChartVisibility(barChart2, xAxisLabel3, yAxisLabel3, View.VISIBLE);
                        setChartVisibility(pieChart, null, null, View.GONE);
                        setChartVisibility(lineChart, xAxisLabel2, yAxisLabel2, View.GONE);
                        setChartVisibility(barChart1, xAxisLabel1, yAxisLabel1, View.GONE);
                        description.setText(R.string.fragment_statistical_trend_chart4_description);
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
        // 设置spinner选项
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.chart_options, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinner.setAdapter(adapter);
    }

    //以下代码为绘图代码
    private void drawPopulationPieChart(PieChart pieChart)//图1
    {
        pieChart.clear();
        List<String> labelsPieChart = Arrays.asList("China", "India", "US", "South Korea", "Malaysia", "Nepal", "Brazil", "Japan", "Thailand", "Indonesia");
        List<Float> valuesPieChart = Arrays.asList(88963f, 50125f, 2162f, 6164f, 9554f, 30716f, 6450f, 2779f, 9316f, 10507f); // 饼图数据

        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < valuesPieChart.size(); i++) {
            // 根据传入的标签和值创建PieEntry（扇形）
            entries.add(new PieEntry(valuesPieChart.get(i), labelsPieChart.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Label"); // Label为整个饼图的描述

        // 自定义颜色--开始
        ArrayList<Integer> vibrantColors = new ArrayList<>();
        // 添加鲜艳的颜色
        vibrantColors.add(Color.parseColor("#F44336")); // 鲜红色
        vibrantColors.add(Color.parseColor("#E91E63")); // 鲜粉色
        vibrantColors.add(Color.parseColor("#9C27B0")); // 鲜紫色
        vibrantColors.add(Color.parseColor("#673AB7")); // 深紫色
        vibrantColors.add(Color.parseColor("#3F51B5")); // 鲜蓝色
        vibrantColors.add(Color.parseColor("#2196F3")); // 亮蓝色
        vibrantColors.add(Color.parseColor("#03A9F4")); // 天蓝色
        vibrantColors.add(Color.parseColor("#00BCD4")); // 青色
        vibrantColors.add(Color.parseColor("#009688")); // 青绿色
        vibrantColors.add(Color.parseColor("#4CAF50")); // 鲜绿色
        vibrantColors.add(Color.parseColor("#8BC34A")); // 酸橙绿
        vibrantColors.add(Color.parseColor("#CDDC39")); // 柠檬黄
        vibrantColors.add(Color.parseColor("#FFEB3B")); // 鲜黄色
        vibrantColors.add(Color.parseColor("#FFC107")); // 琥珀色
        vibrantColors.add(Color.parseColor("#FF9800")); // 橙色
        vibrantColors.add(Color.parseColor("#FF5722")); // 深橙色
        dataSet.setColors(vibrantColors); // 应用自定义颜色


        dataSet.setValueTextSize(6f); // 设置扇形上值的文本大小
        dataSet.setValueTextColor(Color.WHITE); // 设置扇形上值的文本颜色

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setUsePercentValues(true); // 设置饼图显示百分比
        pieChart.getDescription().setEnabled(false); // 不显示描述文本
        pieChart.setEntryLabelTextSize(6f); // 设置扇形标签的文本大小
        pieChart.setEntryLabelColor(Color.BLACK); // 设置扇形标签的文本颜色

        pieChart.setDrawHoleEnabled(false);//删除饼图中心空白
        setupPieChartLegend(pieChart);//启用自动换行图例

        pieChart.animateY(1400, Easing.EaseInOutQuad); // 设置饼图的加载动画

        // 刷新图表以显示数据
        pieChart.invalidate();
    }

    //自定义图例，以便当条目过多而无法容纳在一行中时，它会显示在多行中
    private void setupPieChartLegend(PieChart pieChart) {
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setWordWrapEnabled(true); // Enable word wrapping
        pieChart.invalidate(); // Refresh the chart to apply changes
    }

    private void drawDataTrendLineChart(LineChart lineChart) {//自定义图表格式--线图 图2
        lineChart.clear();
        List<ILineDataSet> dataSets = new ArrayList<>();

        int[] data2019 = new int[]{90260, 183900, 72150, 44500, 34260, 46040, 143840, 54960, 45300, 50830, 38260, 38690};
        int[] data2022 = new int[]{28030, 49310, 28180, 21130, 24750, 29480, 71220, 40650, 35560, 28690, 33060, 35840};
        int[] data2023 = new int[]{59240, 142580, 53640, 42830, 43950, 50620, 131640, 48770, 45090, 38790, 34550, 41670};
        int[] data2024 = new int[]{82890, 181350, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // 假设部分数据不可用

        // 添加2019年数据集
        dataSets.add(createDataSet(data2019, "2019", Color.RED));
        // 添加2022年数据集
        dataSets.add(createDataSet(data2022, "2022", Color.GREEN));
        // 添加2023年数据集
        dataSets.add(createDataSet(data2023, "2023", Color.BLUE));
        // 添加2024年数据集
        dataSets.add(createDataSet(data2024, "2024", Color.MAGENTA));

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        customizeLineChartAppearance();
        lineChart.invalidate();
    }

    private LineDataSet createDataSet(int[] data, String label, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            // 注意：这里应当检查您的数据中的0是否表示没有数据，如果是，您可能不想添加这些点
            if (data[i] > 0) {
                entries.add(new Entry(i, data[i]));
            }
        }
        LineDataSet dataSet = new LineDataSet(entries, label);
        dataSet.setColor(color);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(3f);
        // 根据需要添加更多样式配置
        return dataSet;
    }

    private void customizeLineChartAppearance() {//自定义图表格式--线图
        List<String> labelsLineChart = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsLineChart));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // 根据需要自定义其他样式，如Y轴，图例等
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setWordWrapEnabled(true);

        for (ILineDataSet set : lineChart.getData().getDataSets()) {
            ((LineDataSet) set).setDrawValues(false); // 隐藏点上的数值
            ((LineDataSet) set).setCircleRadius(5f); // 设置点的大小
        }
    }

    private void drawComparisonBarChart(BarChart barChart) {// 柱状图
        barChart.clear();
        //图3
        // 数据，硬编码的值
        List<BarEntry> entries202021 = new ArrayList<>();
        entries202021.add(new BarEntry(0, 38.3f));
        entries202021.add(new BarEntry(1, 23.4f));
        entries202021.add(new BarEntry(2, 32.2f));
        entries202021.add(new BarEntry(3, 20.2f));
        entries202021.add(new BarEntry(4, 1.3f));
        entries202021.add(new BarEntry(5, 0.6f));

        List<BarEntry> entries202122 = new ArrayList<>();
        entries202122.add(new BarEntry(0, 48.2f));
        entries202122.add(new BarEntry(1, 46.5f));
        entries202122.add(new BarEntry(2, 37.1f));
        entries202122.add(new BarEntry(3, 22.8f));
        entries202122.add(new BarEntry(4, 1.6f));
        entries202122.add(new BarEntry(5, 0.5f));

        int lightBlue = Color.rgb(85, 126, 230);//浅蓝

        BarDataSet dataSet202021 = new BarDataSet(entries202021, "2020-21");
        dataSet202021.setColor(lightBlue);

        BarDataSet dataSet202122 = new BarDataSet(entries202122, "2021-22");
        dataSet202122.setColor(Color.BLUE);

        // 柱状图的间距调整
        float groupSpace = 0.1f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.43f; // x2 dataset
        // (0.02 + 0.43) * 2 + 0.1 = 1.00 -> interval per "group"

        BarData data = new BarData(dataSet202021, dataSet202122);
        data.setBarWidth(barWidth);

        // 设置数据
        barChart.setData(data);

        // 指定x轴的标签位置
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(
                Arrays.asList("Over the phone", "Text message", "Email", "Online", "Letter", "Other")
        ));

        // 显示柱状图的不同颜色
        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);

        // 柱状图的X轴设置
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 6);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false); // 不显示右侧Y轴
        xAxis.setLabelRotationAngle(-15); // -25 degrees
        xAxis.setDrawGridLines(false); // Removes grid lines 删除网格线
        // 刷新图表
        barChart.invalidate();
    }

    private void drawAuthoritiesBarChart(BarChart barChart) {//图4
        barChart.clear();
        List<BarEntry> entries202021 = new ArrayList<>();
        entries202021.add(new BarEntry(0, 27.3f));
        entries202021.add(new BarEntry(1, 8.7f));
        entries202021.add(new BarEntry(2, 8.4f));
        entries202021.add(new BarEntry(3, 8.2f));
        entries202021.add(new BarEntry(4, 9.8f));
        entries202021.add(new BarEntry(5, 49.5f));  // For "Any authority(b)"

        List<BarEntry> entries202122 = new ArrayList<>();
        entries202122.add(new BarEntry(0, 32.2f));
        entries202122.add(new BarEntry(1, 11.1f));
        entries202122.add(new BarEntry(2, 11.8f));
        entries202122.add(new BarEntry(3, 13.9f));
        entries202122.add(new BarEntry(4, 9.9f));
        entries202122.add(new BarEntry(5, 57.4f));  // For "Any authority(b)"

        int lightBlue = Color.rgb(85, 126, 230);//浅蓝

        BarDataSet dataSet202021 = new BarDataSet(entries202021, "2020-21");
        dataSet202021.setColor(lightBlue);

        BarDataSet dataSet202122 = new BarDataSet(entries202122, "2021-22");
        dataSet202122.setColor(Color.BLUE);

        // 配置柱状图的间距和宽度
        float groupSpace = 0.2f;
        float barSpace = 0.05f;
        float barWidth = 0.35f;

        BarData data = new BarData(dataSet202021, dataSet202122);
        data.setBarWidth(barWidth);

        barChart.setData(data);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(
                Arrays.asList("Bank or financial institution", "Social media or selling site",
                        "Government organisation or department", "Police", "Other", "Any authority(b)")
        ));

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 6);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setAxisMinimum(0);

        xAxis.setLabelRotationAngle(-15); // -25 degrees
        xAxis.setDrawGridLines(false); // Removes grid lines 删除网格线

        barChart.getAxisLeft().setAxisMinimum(0);
        // 刷新图表
        barChart.invalidate();
    }
}