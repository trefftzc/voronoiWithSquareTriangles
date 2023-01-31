import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import java.util.*;

public class DrawTriangles extends Canvas {

    public static HashMap < Integer, Color > colorMap = new HashMap < Integer, Color > ();
    private QuadTree qt;
    
    public DrawTriangles(QuadTree qt) {
        colorMap.put(0,Color.BLUE);
        colorMap.put(1,Color.RED);
        colorMap.put(2,Color.GREEN);
	this.qt = qt;
    }
	

    public void paint(Graphics g2) {
	Graphics2D g = (Graphics2D) g2;
	setBackground(Color.WHITE);
	
	QuadTreeNode qtn = qt.getRoot();
	
	drawTriangle(qtn,g);
    }

    public void drawTriangle(QuadTreeNode qtn, Graphics2D g) {
	// A triangle has the attribute uniform if all
	// of its vertices are closer to a particular seed
	// than to any other.
	// No need to visit the subtrees
	Triangle t = qtn.getTriangle();
	if (t.isUniform()) {
	    Point[] vertices = t.getVertices();
	    g.setColor(colorMap.get(t.closestSeed()));
	    g.draw(new Line2D.Double(vertices[0],vertices[1]));
	    g.draw(new Line2D.Double(vertices[1],vertices[2]));
	    g.draw(new Line2D.Double(vertices[2],vertices[0]));
	}
	else {
	    drawTriangle(qtn.getChild1(), g);
	    drawTriangle(qtn.getChild2(), g);
	    drawTriangle(qtn.getChild3(), g);
	    drawTriangle(qtn.getChild4(), g);	    
	}

    }
}
    
				   