package com.debjeet.location.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ReportUtilImpl implements ReportUtil {

	@Override
	public void generatePieChart(String path, List<Object[]> data) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		
		for (Object[] obj : data) {
			pieDataset.setValue(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		JFreeChart jFreeChart = ChartFactory.createPieChart3D("Location Report", pieDataset);
		
		try {
			ChartUtilities.saveChartAsJPEG(new File(path+"/pieChart.jpeg"), jFreeChart, 300, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
