import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;

public class TestQuadTree
{
    public static void main(String args[])
    {
	QuadTree qt = new QuadTree();
	Set < Point > seeds = new HashSet< Point > ();
	Point seed1 = new Point(0,0);
	seeds.add(seed1);
	qt.setRoot(qt.createRoot(16,seeds));
	
	QuadTree qt2 = new QuadTree();
	Set < Point > seeds2 = new HashSet < Point > ();
	seeds2.add(seed1);
	Point seed2 = new Point(0,127);
	Point seed3 = new Point(127,0);
	seeds2.add(seed2);
	seeds2.add(seed3);
	qt2.setRoot(qt.createRoot(128,seeds2));

	DrawTriangles drawer = new DrawTriangles(qt2);
	JFrame f = new JFrame();
	f.add(drawer);
	f.setSize(256,256);
	f.setVisible(true);
    }
}
