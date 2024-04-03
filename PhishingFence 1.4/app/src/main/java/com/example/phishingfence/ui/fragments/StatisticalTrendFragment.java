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
import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
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

        setupClickListeners();
        setSpinner();

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

    private void setSpinnerOption()
    {
        // 创建一个ArrayAdapter使用默认的spinner布局和语言数组
        ArrayAdapter<CharSequence> yearsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.years, android.R.layout.simple_spinner_item);

        // 指定下拉列表的默认布局
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearsSpinner.setAdapter(yearsAdapter);
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

    private void updateChartsAndDescription(int position)
    {
        List<String> labelsBarChart = Arrays.asList("Jun","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
        List<String> labelsPieChart = Arrays.asList("China","India","US","South Korea","Malaysia","Nepal","Brazil","Japan","Thailand","Indonesia");
        if(position == 0) {//2003
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 1) {//2004
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 2) {//2005
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 3) {//2006
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 4) {//2007
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 5) {//2008
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 6) {//2009
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 7) {//2010
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 8) {//2011
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 9) {//2012
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 10) {//2013
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 11) {//2014
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 12) {//2015
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 13) {//2016
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 14) {//2017
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 15) {//2018
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 16) {//2019
            List<Float> valuesBarChart = Arrays.asList(90260f,0183900f,72150f,44500f,34260f,46040f,143840f,54960f,45300f,50830f,38260f,38690f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 17) {//2020
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else if (position == 18) {//2021
            List<Float> valuesBarChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(0f,0f,0f,0f,0f,0f,0f,0f,0f,0f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        } else {//2022
            List<Float> valuesBarChart = Arrays.asList(28030f,49310f,28180f,21130f,24750f,29480f,71220f,40650f,35560f,28690f,33060f,35840f); // 柱状图数据
            List<Float> valuesPieChart = Arrays.asList(88963f,50125f,2162f,6164f,9554f,30716f,6450f,2779f,9316f,10507f); // 饼图数据
            drawBarChart(labelsBarChart, valuesBarChart);
            barChart.setVisibility(View.VISIBLE);
            drawPieChart(labelsPieChart, valuesPieChart);
            pieChart.setVisibility(View.VISIBLE);
            description.setText("Here is the despription of charts.");
        }
    }

    private void drawBarChart(List<String> labels, List<Float> values)
    {
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < values.size(); i++)
        {
            // 使用值创建BarEntry，其中i作为X轴坐标，values.get(i)作为Y轴的高度
            entries.add(new BarEntry(i, values.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Label");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // 设置数据集的颜色
        dataSet.setValueTextSize(5f); // 设置值的文本大小

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false); // 不显示描述文本

        // 自定义X轴标签
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 将X轴标签置于底部
        xAxis.setGranularity(1f); // 最小间隔为1
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        barChart.invalidate(); // 刷新图表以显示数据
    }

    private void drawPieChart(List<String> labels, List<Float> values)
    {
        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            // 根据传入的标签和值创建PieEntry（扇形）
            entries.add(new PieEntry(values.get(i), labels.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Label"); // Label为整个饼图的描述
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // 设置数据集的颜色方案
        dataSet.setValueTextSize(6f); // 设置扇形上值的文本大小
        dataSet.setValueTextColor(Color.WHITE); // 设置扇形上值的文本颜色

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setUsePercentValues(true); // 设置饼图显示百分比
        pieChart.getDescription().setEnabled(false); // 不显示描述文本
        pieChart.setEntryLabelTextSize(6f); // 设置扇形标签的文本大小
        pieChart.setEntryLabelColor(Color.BLACK); // 设置扇形标签的文本颜色
        pieChart.setCenterText("Pie Chart"); // 设置饼图中心的文本
        pieChart.setCenterTextSize(8f); // 设置饼图中心文本的大小

        pieChart.animateY(1400, Easing.EaseInOutQuad); // 设置饼图的加载动画

        // 刷新图表以显示数据
        pieChart.invalidate();
    }
}