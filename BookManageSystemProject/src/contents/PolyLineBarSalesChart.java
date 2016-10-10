package contents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class PolyLineBarSalesChart {

	public JFreeChart getChart() {

		// ������ ����
		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset(); // bar
																		// chart
																		// 1

		// ������ �Է� ( ��, ����, ī�װ� )
		// �׷��� 1
		dataset1.addValue(1.0, "S1", "1��");
		dataset1.addValue(4.0, "S1", "2��");
		dataset1.addValue(3.0, "S1", "3��");
		dataset1.addValue(5.0, "S1", "4��");
		dataset1.addValue(5.0, "S1", "5��");
		dataset1.addValue(7.0, "S1", "6��");
		dataset1.addValue(7.0, "S1", "7��");
		dataset1.addValue(8.0, "S1", "8��");
		dataset1.addValue(0, "S1", "9��");
		dataset1.addValue(0, "S1", "10��");
		dataset1.addValue(0, "S1", "11��");
		dataset1.addValue(0, "S1", "12��");

		// ������ ���� �� ����
		// ������ ����
		final BarRenderer renderer = new BarRenderer();
		final BarRenderer renderer12 = new BarRenderer();
		final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();

		// ���� �ɼ� ����
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
		final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
		Font f = new Font("Gulim", Font.BOLD, 14);
		Font axisF = new Font("Gulim", Font.PLAIN, 14);

		// ������ ����
		// �׷��� 1
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(p_center);
		renderer.setBaseItemLabelFont(f);
		// renderer.setGradientPaintTransformer(new
		// StandardGradientPaintTransformer(
		// GradientPaintTransformType.VERTICAL));
		// renderer.setSeriesPaint(0, new GradientPaint(1.0f, 1.0f, Color.white,
		// 0.0f, 0.0f, Color.blue));
		renderer.setSeriesPaint(0, new Color(0, 162, 255));

		// �׷��� 2
		renderer12.setSeriesPaint(0, new Color(232, 168, 255));
		renderer12.setBaseItemLabelFont(f);
		renderer12.setBasePositiveItemLabelPosition(p_center);
		renderer12.setBaseItemLabelGenerator(generator);
		renderer12.setBaseItemLabelsVisible(true);

		// �׷��� 3
		renderer2.setBaseItemLabelGenerator(generator);
		renderer2.setBaseItemLabelsVisible(true);
		renderer2.setBaseShapesVisible(true);
		renderer2.setDrawOutlines(true);
		renderer2.setUseFillPaint(true);
		renderer2.setBaseFillPaint(Color.WHITE);
		renderer2.setBaseItemLabelFont(f);
		renderer2.setBasePositiveItemLabelPosition(p_below);
		renderer2.setSeriesPaint(0, new Color(219, 121, 22));
		renderer2.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));

		// plot ����
		final CategoryPlot plot = new CategoryPlot();

		// plot �� ������ ����
		plot.setDataset(dataset1);
		plot.setRenderer(renderer);

		// plot �⺻ ����
		plot.setOrientation(PlotOrientation.VERTICAL); // �׷��� ǥ�� ����
		plot.setRangeGridlinesVisible(true); // X�� ���̵� ���� ǥ�ÿ���
		plot.setDomainGridlinesVisible(true); // Y�� ���̵� ���� ǥ�ÿ���

		// ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		// X�� ����
		plot.setDomainAxis(new CategoryAxis()); // X�� ���� ����
		plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ�
																							// ��
																							// ��ġ
																							// ����

		// Y�� ����
		plot.setRangeAxis(new NumberAxis()); // Y�� ���� ����
		plot.getRangeAxis().setTickLabelFont(axisF); // Y�� ���ݶ� ��Ʈ ����

		// ���õ� plot�� �������� chart ����
		final JFreeChart chart = new JFreeChart(plot);
		// chart.setTitle("Overlaid Bar Chart"); // ��Ʈ Ÿ��Ʋ
		// TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new
		// Font("SansSerif", Font.PLAIN, 9));
		// copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		// chart.addSubtitle(copyright); // ��Ʈ ���� Ÿ��Ʋ
		return chart;
	}

}
