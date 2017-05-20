/**
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.knowm.xchart.standalone.issues;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;


public class TestForIssue181 {

  public static void main(String[] args) {

    // Create Chart
    XYChart chart = new XYChartBuilder()
        .width(500)
        .height(350)
        .theme(Styler.ChartTheme.Matlab)
        .build();

    // Series
    List<Double> xData = new LinkedList<Double>();
    List<Double> yData = new LinkedList<Double>();

    Random random = new Random();
    int size = 1000;
    for (int i = 0; i < size; i++) {
      xData.add(5 * random.nextGaussian());
      yData.add(5 * random.nextGaussian());
    }
    chart.addSeries("Gaussian Blob", xData, yData);

    XYSeries vertical = chart.addSeries("vertical", new double[]{5, 5}, new double[]{0, 10});
    vertical.setShowInLegend(false);
    vertical.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
    vertical.setLineStyle(SeriesLines.SOLID);
    XYSeries horizontal = chart.addSeries("horizontal", new double[]{0, 10}, new double[]{5, 5});
    horizontal.setShowInLegend(false);
    horizontal.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
    horizontal.setLineStyle(SeriesLines.SOLID);

    // Customize Chart
    Marker[] markers = {SeriesMarkers.SQUARE, SeriesMarkers.SQUARE, SeriesMarkers.DIAMOND, SeriesMarkers.DIAMOND};
    Color[] colors = {Color.GRAY, Color.BLUE, Color.RED, Color.RED};

    chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
    chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
    chart.getStyler().setMarkerSize(5);
    chart.getStyler().setAxisTicksVisible(true);
    chart.getStyler().setXAxisMin(0.0);
    chart.getStyler().setYAxisMin(0.0);
    chart.getStyler().setSeriesMarkers(markers);
    chart.getStyler().setSeriesColors(colors);
    chart.getStyler().setPlotGridLinesVisible(false);
    chart.getStyler().setPlotContentSize(1.0);
    new SwingWrapper<XYChart>(chart).displayChart();
  }
}
