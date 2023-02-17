import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import java.util.*;

public class DrawTriangles extends Canvas {

    public static HashMap < Integer, Color > colorMap = new HashMap < Integer, Color > ();
    private QuadTree qt;
    private SecondQuadTree qt2;
    
    public DrawTriangles(QuadTree qt,SecondQuadTree qt2) {
        colorMap.put(0,Color.BLUE);
        colorMap.put(1,Color.RED);
        colorMap.put(2,Color.GREEN);
        colorMap.put(3,Color.YELLOW);
        colorMap.put(4,Color.ORANGE);
        colorMap.put(5,Color.MAGENTA);
        colorMap.put(6,Color.GRAY);
	this.qt = qt;
	this.qt2 = qt2;
    }
	

    public void paint(Graphics g2) {
	Graphics2D g = (Graphics2D) g2;
	setBackground(Color.WHITE);
	QuadTreeNode qtn = qt.getRoot();
	QuadTreeNode qtn2 = qt2.getRoot();
	
	drawTriangle(qtn,g);
	drawTriangle(qtn2,g);
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
	    /*
	    g.draw(new Line2D.Double(vertices[0],vertices[1]));
	    g.draw(new Line2D.Double(vertices[1],vertices[2]));
	    g.draw(new Line2D.Double(vertices[2],vertices[0]));	
	    */
	    int [] x = {vertices[0].x,vertices[1].x,vertices[2].x};
	    int [] y = {vertices[0].y,vertices[1].y,vertices[2].y};
 	    g.drawPolygon(x,y,3);
            g.fillPolygon(x,y,3);
	}
	else {
	    drawTriangle(qtn.getChild1(), g);
	    drawTriangle(qtn.getChild2(), g);
	    drawTriangle(qtn.getChild3(), g);
	    drawTriangle(qtn.getChild4(), g);	    
	}

    }
}
    
				   
