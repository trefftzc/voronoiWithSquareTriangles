
import java.util.Set;
import java.awt.Point;
public class QuadTree
{
    private QuadTreeNode root = null;

    public QuadTree()
    {

    }

    public void setRoot(QuadTreeNode q) {
        root = q;
    }

    public QuadTreeNode getRoot() {
	return root;
    }
    
    private int calcClosestSeed(Point p,Set < Point > seeds) {
        double closestDistance = Double.MAX_VALUE;
        int closestSeed = -1;
        int i = 0;
        double x1 = p.getX();
        double y1 = p.getY();

        for (Point s : seeds) {
            double x2 = s.getX();
            double y2 = s.getY();
            double diffx = (x1 - x2)*(x1 - x2);
            double diffy = (y1 - y2)*(y1 - y2);
            if (Math.sqrt(diffx+diffy) < closestDistance) {
                closestDistance = Math.sqrt(diffx+diffy);
                closestSeed = i;
            }
            i++;
        }
        return closestSeed;
    }

    // n is the size of the side of the cathetus of the right triangle
    // n should be a power of 2
    public QuadTreeNode createRoot(int n,Set < Point > seeds) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(n-1,0);
        Point p3 = new Point(0,n-1);
        boolean up = true;
        return createChildNode(p1,p2,p3,up,seeds);
    }
    private boolean itIsASmallTriangle(Point p1,Point p2,Point p3,boolean direction) {
        int p1x = (int) p1.getX();
        int p1y = (int) p1.getY();
        int p2x = (int) p2.getX();
        int p2y = (int) p2.getY();
        int p3x = (int) p3.getX();
        int p3y = (int) p3.getY();

	if (p1x == p2x && p1x == p3x && p1y ==p2y && p2y == p3y) {
		return true;
	}

        if (direction) {
            if ((p1x == p3x)&&(p1x == p2x-1) && (p1y == p2y) && (p1y == p3y-1)) {

                System.out.println("Small Triangle Up: "+p1.toString()+" "+p2.toString()+" "+p3.toString());
                return true;
            }
            else    
                return false;
        }
        else {
            if ((p1x == p2x-1)&&(p2x == p3x) && (p1y == p2y) && (p1y == p3y-1)) {
                System.out.println("Small Triangle Down: "+p1.toString()+" "+p2.toString()+" "+p3.toString());
                return true;
            }
            else    
                return false;
        }

    }

    public QuadTreeNode createChildNode(Point p1,Point p2,Point p3,boolean direction,Set < Point > seeds) {
       
        System.out.println(p1.toString()+" "+p2.toString()+" "+p3.toString());
        int closestSeedTop1 = calcClosestSeed(p1,seeds);
        int closestSeedTop2 = calcClosestSeed(p2,seeds);
        int closestSeedTop3 = calcClosestSeed(p3,seeds);
        if ((closestSeedTop1 == closestSeedTop2 && closestSeedTop1 == closestSeedTop3) ||
            (itIsASmallTriangle(p1,p2,p3,direction)))
        {
            // No need to call recursively
            // This entire triangle is homegenous
            Triangle newTriangle = new Triangle(p1,p2,p3,direction,closestSeedTop1);
            return new QuadTreeNode(null,null,null,null,newTriangle);
        }
        else {
            if (direction) {
                // needs to call recusively on the sub triangles...

                double p1x = p1.getX();
                double p1y = p1.getY();
                double p2x = p2.getX();
                double p2y = p2.getY();
                double p3x = p3.getX();
                double p3y = p3.getY();
                double middleP1P2x = (p1x + p2x) / 2.0;
                double middleP1P3y = (p1y + p3y) / 2.0;
                QuadTreeNode child1 = createChildNode(p1,
                        new Point((int) middleP1P2x,(int) p1y),
                        new Point((int) p1x,(int) middleP1P3y),
                        direction,seeds);
                QuadTreeNode child2 = createChildNode(                       
                        new Point((int) middleP1P2x+1,(int) p1y),
                        p2,
                        new Point((int) middleP1P2x+1,(int) middleP1P3y),
                        direction,seeds); 
                QuadTreeNode child3 = createChildNode(
                        new Point((int)p1x+1,(int) middleP1P3y),
                        new Point((int) middleP1P2x,(int) middleP1P3y),
                        new Point((int) middleP1P2x,(int) p1y+1),
                        !direction,seeds);
                QuadTreeNode child4 = createChildNode(
                        new Point((int) p1x,(int) middleP1P3y+1),
                        new Point((int) middleP1P2x,(int)middleP1P3y+1),
                        p3,
                        direction,seeds); 
                Triangle newTriangle = new Triangle(p1,p2,p3,direction);
                return new QuadTreeNode(child1,child2,child3,child4,newTriangle);
            }
            else {
                double p1x = p1.getX();
                double p1y = p1.getY();
                double p2x = p2.getX();
                double p2y = p2.getY();
                double p3x = p3.getX();
                double p3y = p3.getY();
                double middleP1P2x = (p1x + p2x) / 2.0;
                double middleP1P3y = (p1y + p3y) / 2.0;
                QuadTreeNode child1 = createChildNode(p1,
                        new Point((int) middleP1P2x-1,(int) p1y),
                        new Point((int) middleP1P2x-1,(int) middleP1P3y+1),
                        direction,seeds);
                QuadTreeNode child2 = createChildNode(                       
                        new Point((int) middleP1P2x+1,(int) p1y),
                        p2,
                        new Point((int) p3x,(int) middleP1P3y+1),
                        direction,seeds); 
                QuadTreeNode child3 = createChildNode(
                        new Point((int)middleP1P2x,(int) middleP1P3y),
                        new Point((int) p3x ,(int) middleP1P3y),
                        new Point((int) middleP1P2x,(int) p1y),
                        !direction,seeds);
                QuadTreeNode child4 = createChildNode(
                        new Point((int) middleP1P2x+1,(int) middleP1P3y-1),
                        new Point((int) p3x, (int)middleP1P3y-1),
                        p3,
                        direction,seeds); 
                Triangle newTriangle = new Triangle(p1,p2,p3,direction);
                return new QuadTreeNode(child1,child2,child3,child4,newTriangle);
            }
          
        }

    }

}
