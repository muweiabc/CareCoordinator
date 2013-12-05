package cis573.carecoor;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalActivity;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TrackActivity extends BannerActivity {
	private GraphicalView graph;
	private LinearLayout trackLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_track);
		setBannerTitle(R.string.track);
		trackLayout=(LinearLayout)findViewById(R.id.track_layout);
		
		graph=ChartFactory.getLineChartView(this, getDemoDataset(), getDemoRenderer());
		graph.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		trackLayout.addView(graph);
		
	}


	private XYMultipleSeriesDataset getDemoDataset() {
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    final int nr = 7;
	    Random r = new Random();
	    
	      XYSeries series = new XYSeries("Medicine Compliance");
	      for (int k = 0; k < nr; k++) {
	        series.add((double)k,(double)k/10+0.1);
	      }
	      dataset.addSeries(series);
	    
	    return dataset;
	  }

 private XYMultipleSeriesRenderer getDemoRenderer() {
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    //////////////////
	    renderer.setMargins(new int[] {120, 100,80, 50});
	    renderer.setMarginsColor(Color.LTGRAY);
	    //renderer.setBackgroundColor(Color.CYAN);
	    ////////////////////////////////
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setChartTitle("Weekly Compliance");
	    renderer.setChartTitleTextSize(40);
	    renderer.setXTitle("Recent Days");
	    renderer.setYTitle("Compliance Percentage");
	    
	    /////////////////////////// 
	    renderer.setLabelsTextSize(30);
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setAxisTitleTextSize(30);
	    //renderer.addXTextLabel(3, "what");
	    //renderer.setLegendTextSize(20);
	    renderer.setPointSize(5f);
	    renderer.setShowGrid(true);
	    renderer.setGridColor(Color.CYAN);
	    ///////////////////////
	    renderer.setXLabelsColor(Color.RED);
	    renderer.setYLabelsColor(0,Color.RED);
	    renderer.setXLabels(7);
	    renderer.setYLabels(6);
	    renderer.setYLabelsPadding((float)30);
	   /////////////////////////////////
	    renderer.setShowLegend(false);
	    renderer.setYAxisMin(0.0);
	    renderer.setYAxisMax(1.0);
	    renderer.setXAxisMin(0);
	    renderer.setXAxisMax(6);
	    
	    
	    XYSeriesRenderer r = new XYSeriesRenderer();
	    r.setColor(Color.BLUE);
	    r.setPointStyle(PointStyle.CIRCLE);
	    //r.setFillBelowLine(true);
	    //r.setFillBelowLineColor(Color.WHITE);
	    r.setFillPoints(true);
	    
	    renderer.addSeriesRenderer(r);
	    /*
	    r = new XYSeriesRenderer();
	    r.setPointStyle(PointStyle.SQUARE);
	    r.setColor(Color.GREEN);
	    r.setFillPoints(true);
	    renderer.addSeriesRenderer(r);
	    renderer.setAxesColor(Color.DKGRAY);
	    renderer.setLabelsColor(Color.LTGRAY);*/
	    return renderer;
	  }
}
