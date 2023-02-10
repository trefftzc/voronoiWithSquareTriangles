import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;

public class TestQuadTree2
{
    public static void main(String args[])
    {
	QuadTree qt = new QuadTree();
	Set < Point > seeds = new HashSet< Point > ();
	Point seed1 = new Point(41,146);
        Point seed2 = new Point(337,472);
	Point seed3 = new Point(375,408);
	Point seed4 = new Point(250,255);	
	seeds.add(seed1);
	seeds.add(seed2);
	seeds.add(seed3);
	seeds.add(seed4);
	qt.setRoot(qt.createRoot(512,seeds));
	DrawTriangles drawer = new DrawTriangles(qt);
	JFrame f = new JFrame();
	f.add(drawer);
	f.setSize(512,512);
	f.setVisible(true);
    }
}
