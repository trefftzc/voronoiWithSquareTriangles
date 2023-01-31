public class QuadTreeNode
{
    private QuadTreeNode child1;
    private QuadTreeNode child2;
    private QuadTreeNode child3;
    private QuadTreeNode child4;

    private Triangle t;
    

    public QuadTreeNode(QuadTreeNode c1,
			QuadTreeNode c2,
			QuadTreeNode c3,
			QuadTreeNode c4,
			Triangle t)
    {
        child1 = c1;
        child2 = c2;
        child3 = c3;
        child4 = c4;
        this.t = t;
    }

    public void setChild1(QuadTreeNode child) {
        child1 = child;
    }
    public void setChild2(QuadTreeNode child) {
        child2 = child;
    }
    public void setChild3(QuadTreeNode child) {
        child3 = child;
    }
    public void setChild4(QuadTreeNode child) {
	child4 = child;
    }

    public QuadTreeNode getChild1() {
	return child1;
    }
    public QuadTreeNode getChild2() {
	return child2;
    }
    public QuadTreeNode getChild3() {
	return child3;
    }
    public QuadTreeNode getChild4() {
	return child4;
    }

    public Triangle getTriangle() {
	return t;
    }

    public String toString() {
	return "QuadTreeNode: "+t.toString();
    }
}
