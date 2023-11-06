package com.example.as_prm_thien.Admin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_prm_thien.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private BarChart barChart;
    private PieChart pieChart;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize the BarChart
        barChart = view.findViewById(R.id.barChart);

        // Create sample data
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 50)); // Replace with your actual data for each month
        entries.add(new BarEntry(1, 75));
        entries.add(new BarEntry(2, 60));
        // Add more entries for each month

        BarDataSet barDataSet = new BarDataSet(entries, "Sales Data");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        // Customize the appearance of the chart
        barDataSet.setColors(getResources().getColor(R.color.primaryTextColor)); // Set color
        Description description = new Description();
        description.setText(""); // Set chart description
        barChart.setDescription(description);

        // Customize the X-axis (months)
        final String[] months = {"Jan", "Feb", "Mar"}; // Replace with your actual month labels
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(months));
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setLabelCount(months.length);

        // Enable zoom and touch gestures (optional)
        barChart.setPinchZoom(true);
        barChart.setDoubleTapToZoomEnabled(true);

        // Refresh the chart
        barChart.invalidate();

        pieChart = view.findViewById(R.id.pieChart);

        // Create data entries for the pie chart
        ArrayList<PieEntry> entries2 = new ArrayList<>();
        entries2.add(new PieEntry(30f, "Shoes A"));
        entries2.add(new PieEntry(25f, "Shoes B"));
        entries2.add(new PieEntry(45f, "Shoes C"));

        PieDataSet dataSet = new PieDataSet(entries2, "Top Seller Shoes");
        dataSet.setColors(Color.rgb(255, 140, 0), Color.rgb(0, 128, 0), Color.rgb(0, 0, 128));

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);

        // Refresh the chart
        pieChart.invalidate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}