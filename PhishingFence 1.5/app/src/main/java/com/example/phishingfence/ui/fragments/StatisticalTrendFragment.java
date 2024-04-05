package com.example.phishingfence.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.phishingfence.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import android.graphics.Color;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.animation.Easing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticalTrendFragment extends Fragment
{
    private ImageView btnBack;
    private OnStatisticalTrendFragmentInteractionListener mOnStatisticalTrendFragmentInteractionListener;
    private Spinner yearsSpinner;
    private TextView description;
    private BarChart barChart;
    private PieChart pieChart;

    private LineChart lineChart;

    public StatisticalTrendFragment()
    {
        // Required empty public constructor
    }

    public interface OnStatisticalTrendFragmentInteractionListener
    {
        void onBackClick();
    }

    public void setOnStatisticalTrendFragmentInteractionListener(OnStatisticalTrendFragmentInteractionListener onStatisticalTrendFragmentInteractionListener)
    {
        this.mOnStatisticalTrendFragmentInteractionListener = onStatisticalTrendFragmentInteractionListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_statistical_trend, container, false);

        //初始化控件
        this.btnBack = rootview.findViewById(R.id.img_back);
        this.yearsSpinner = rootview.findViewById(R.id.sp_option);
        this.description = rootview.findViewById(R.id.text_description);
        this.barChart = (BarChart) rootview.findViewById(R.id.barChart);
        this.pieChart = (PieChart) rootview.findViewById(R.id.pieChart);


        this.lineChart = (LineChart) rootview.findViewById(R.id.lineChart); // 确保你的布局文件中有这个ID

        setupClickListeners();
        setSpinner();

        //setupLineChart(rootview);

        return rootview;
    }

    private void setupClickListeners()
    {
        setupBackButtonListener();
    }

    private void setupBackButtonListener()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnStatisticalTrendFragmentInteractionListener!=null)
                {
                    mOnStatisticalTrendFragmentInteractionListener.onBackClick();
                }
            }
        });
    }

    private void setSpinner()
    {
        setSpinnerOption();
        setSpinnerListener();
        updateChartsAndDescription(0);
    }

    private void setSpinnerListener()
    {
        // 设置 Spinner 监听器
        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 基于选项 position 更新图表和文字说明
                updateChartsAndDescription(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setSpinnerOption() {
        // 使用新的chart_options字符串数组
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.chart_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearsSpinner.setAdapter(adapter);
    }

    private void updateChartsAndDescription(int position) {

        List<String> labelsPieChart = Arrays.asList("China","India","US","South Korea","Malaysia","Nepal","Brazil","Japan","Thailand","Indonesia");
        List<Float> valuesPieChart = Arrays.asList(88963f,50125f,2162f,6164f,9554f,30716f,6450f,2779f,9316f,10507f); // 饼图数据

        // 清除所有图表数据
        barChart.clear();
        pieChart.clear();
        lineChart.clear();

        switch (position) {
            case 0: // 饼图1
                drawPieChart(labelsPieChart, valuesPieChart);// 调用绘制饼图的方法
                description.setText("Total Population by Nationality");
                pieChart.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
                lineChart.setVisibility(View.GONE);

                break;
            case 1: // 线图2
                drawMultipleLineChart();//多个线的线图

                description.setText("Data Trends Over Months");
                lineChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(View.GONE);
                barChart.setVisibility(View.GONE);

                break;
            case 2: // 柱状图3
                drawComparisonBarChart(barChart);

                description.setText("Scam exposure rate(a), by mode of exposure(b), 2020-21 to 2021-22(c)");
                barChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(View.GONE);
                lineChart.setVisibility(View.GONE);
                break;
            case 3: // 柱状图4
                drawAuthoritiesBarChart(barChart);

                description.setText("Scams, Authorities most serious incident was reported to(a)(b)(c), 2020-21 to 2021-22");
                barChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(View.GONE);
                lineChart.setVisibility(View.GONE);
                break;
            default:
                description.setText("nothing");
                barChart.setVisibility(View.GONE);
                pieChart.setVisibility(View.GONE);
                lineChart.setVisibility(View.GONE);
                break;
        }
    }


    private void drawPieChart(List<String> labels, List<Float> values)
    {
        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            // 根据传入的标签和值创建PieEntry（扇形）
            entries.add(new PieEntry(values.get(i), labels.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Label"); // Label为整个饼图的描述


        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // 设置数据集的颜色方案

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

        //饼图中心文本-不需要
//        pieChart.setCenterText("Pie Chart"); // 设置饼图中心的文本
//        pieChart.setCenterTextSize(8f); // 设置饼图中心文本的大小
        pieChart.setDrawHoleEnabled(false);//删除饼图中心空白
        setupPieChartLegend();//启用自动换行图例


        pieChart.animateY(1400, Easing.EaseInOutQuad); // 设置饼图的加载动画

        // 刷新图表以显示数据
        pieChart.invalidate();
    }

    private void drawMultipleLineChart() {// 绘制复合折线图
        List<ILineDataSet> dataSets = new ArrayList<>();

        List<String> labelsLineChart = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

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
        lineChart.invalidate(); // 刷新图表
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

    // 柱状图
    private void drawComparisonBarChart(BarChart barChart) {
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

        barChart.invalidate();
    }


    //自定义图例，以便当条目过多而无法容纳在一行中时，它会显示在多行中
    private void setupPieChartLegend() {
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setWordWrapEnabled(true); // Enable word wrapping
        pieChart.invalidate(); // Refresh the chart to apply changes
    }

}

