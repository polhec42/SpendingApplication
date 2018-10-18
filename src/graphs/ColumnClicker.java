package graphs;

import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import basic.GraphsPlosca;


public class ColumnClicker {
	
	private ArrayList<Shape> histogramColumns;
	private GraphsPlosca graphsPlosca;
	
	public ColumnClicker(GraphsPlosca graphsPlosca) {
		this.graphsPlosca = graphsPlosca;
		this.histogramColumns = this.graphsPlosca.getHistogramColumns();	
	}
	
	public void isClicked(ArrayList<Shape> histogramColumns) {
		this.histogramColumns = histogramColumns;
		
		this.graphsPlosca.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				for(Shape shape : histogramColumns) {
					if(shape.contains(e.getPoint())) {
						System.out.println("True");
					}
				}
			}
		});
	}
	
}
