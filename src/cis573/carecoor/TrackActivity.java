package cis573.carecoor;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import cis573.carecoor.data.ScheduleCenter;
import cis573.carecoor.data.ScheduleCenter.Conformity;

public class TrackActivity extends BannerActivity {
	private GraphicalView graph;
	private LinearLayout trackLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_track);
		setBannerTitle(R.string.track);
		trackLayout=(LinearLayout)findViewById(R.id.track_layout);
		graph = ChartFactory.getTimeChartView(this, getConformityDataset(), getConformityRenderer(),
				"MM/dd");
		graph.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		trackLayout.addView(graph);
	}
	
	private XYMultipleSeriesDataset getConformityDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		TimeSeries series = new TimeSeries("Conformity");
		Map<Date, Conformity> map = ScheduleCenter.getOverallConformity(TrackActivity.this);
		if(map!=null){
			Iterator<Entry<Date, Conformity>> iter = map.entrySet().iterator();
			Entry<Date, Conformity> entry;
			while(iter.hasNext()) {
				entry = iter.next();
				series.add(entry.getKey(), entry.getValue().getConformity() * 100);
			}
		}
		dataset.addSeries(series);
		return dataset;
	}
	
	private XYMultipleSeriesRenderer getConformityRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(100);
		renderer.setPanEnabled(true, false);
		
		
	    renderer.setMargins(new int[] {130, 130,110, 60});
	    renderer.setMarginsColor(Color.rgb(230, 237, 236));

	    ////////////////////////////////
	    renderer.setLabelsColor(Color.DKGRAY);
	    renderer.setChartTitle("\n\n\n\n\n\n Weekly Compliance");
	    renderer.setChartTitleTextSize(40);
	    renderer.setXTitle("\n\n\n\n\n\n Recent Days");
	    renderer.setYTitle("\n\n\n Compliance Percentage");

	    /////////////////////////// 
	    renderer.setLabelsTextSize(25);
	    renderer.setAxesColor(Color.BLACK);
	    renderer.setAxisTitleTextSize(35);

	    renderer.setPointSize(5f);
	    renderer.setShowGrid(true);
	    renderer.setGridColor(Color.CYAN);
	    ///////////////////////
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0,Color.BLACK);
	    renderer.setXLabels(7);
	    renderer.setYLabels(6);
	    renderer.setYLabelsPadding((float)30);
	   /////////////////////////////////
	    renderer.setShowLegend(false);
	    
	    XYSeriesRenderer r = new XYSeriesRenderer();
		
		r.setColor(Color.BLUE);
	    r.setPointStyle(PointStyle.CIRCLE);
	    r.setFillPoints(true);
	    //r.setLineWidth(6);
	    //r.setPointStrokeWidth(20);
	    
	    renderer.addSeriesRenderer(r);
		return renderer;
	}

}
