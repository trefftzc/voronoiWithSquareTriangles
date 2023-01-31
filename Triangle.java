import java.awt.Point;

public class Triangle
{
    private Point [] vertices = new Point[3];
    private boolean up;
    // If all the vertices are closest to the same seed
    // This triangle acquires the color of the closest seed
    private boolean uniform;
    private int closestSeed;

    public Triangle(Point p1,Point p2,Point p3,boolean up) {
	vertices[0] = p1;
	vertices[1] = p2;
	vertices[2] = p3;
	this.up = up;
	uniform = false;
	closestSeed = -1;
    }

    public Triangle(Point p1,Point p2,Point p3,boolean up,int closestSeed) {
	vertices[0] = p1;
	vertices[1] = p2;
	vertices[2] = p3;
	this.up = up;
	uniform = true;
	this.closestSeed = closestSeed;
    }

    public Point[] getVertices() {
	return vertices;
    }

    public boolean up() {
	return up;
    }

    public String toString() {
	return "Triangle"+vertices[0].toString()+" "+
	    vertices[1].toString()+" "+
	    vertices[2].toString();
    }

    public int closestSeed() {
	return closestSeed;
    }

    public boolean isUniform() {
	return uniform;
    }
}
