import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;

public class TestQuadTree
{
    public static void main(String args[])
    {
	Set < Point > seeds = new HashSet< Point > ();
	Point seed1 = new Point(0,0);
	Point seed2 = new Point(0,127);
	Point seed3 = new Point(127,0);
 	Point seed4 = new Point(31,31);
 	Point seed5 = new Point(63,63);
 	Point seed6 = new Point(127,127);
 	Point seed7 = new Point(180,180);
	seeds.add(seed1);
	seeds.add(seed2);
	seeds.add(seed3);
 	seeds.add(seed4);
 	seeds.add(seed5);
 	seeds.add(seed6);
 	seeds.add(seed7);
 	QuadTree qt3 = new QuadTree();
 	SecondQuadTree qt3_2 = new SecondQuadTree();	
 	qt3.setRoot(qt3.createRoot(256,seeds));
 	qt3_2.setRoot(qt3_2.createRoot(256,seeds));
 
 	DrawTriangles drawer = new DrawTriangles(qt3,qt3_2);
	JFrame f = new JFrame();
	f.add(drawer);
	f.setSize(300,300);
	f.setVisible(true);
    }
}
