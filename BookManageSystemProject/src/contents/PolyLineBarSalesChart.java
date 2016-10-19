package contents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

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

import dto.SalesDTO;

public class PolyLineBarSalesChart {

	public JFreeChart getChart(ArrayList<Object[]> arrayList) {

		// ������ ����
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // bar
																		// chart
																		// 1

		// ������ �Է� ( ��, ����, ī�װ� )
		// �׷��� 1
		
		for(int i = 0;i<arrayList.size();i++){
			Object[] data = arrayList.get(i);
			dataset.addValue((int) data[1],"�ϸ���" ,""+data[0] );
		}
		
		
		// ������ ���� �� ����
		// ������ ����
		final BarRenderer renderer = new BarRenderer();

		// ���� �ɼ� ����
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
		Font f = new Font("Gulim", Font.BOLD, 14);
		Font axisF = new Font("Gulim", Font.PLAIN, 10);

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

		// plot ����
		final CategoryPlot plot = new CategoryPlot();

		// plot �� ������ ����
		plot.setDataset(dataset);
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
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // ī�װ�
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
