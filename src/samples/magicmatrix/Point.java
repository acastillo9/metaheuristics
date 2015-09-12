package samples;

/**
 *
 * @author andres
 */
public class Point {

    private int x;
    private int y;

    public Point() {
    	x = -1;
    	y = -1;
    }

    public Point(int x, int y) {
    	this.x = x;
    	this.y = y;
    }

    public int getX() {
    	return x;
    }
    public void setX(int x) {
    	this.x = x;
    }

    public int getY() {
    	return y;
    }
    public void setY(int y) {
    	this.y = y;
    }

    @Override
	public boolean equals(Object obj) {
		if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final Point other = (Point) obj;
	    if ((this.x == -1) ? (other.x != -1) : !(this.x == other.x)) {
	        return false;
	    }
	    if ((this.y == -1) ? (other.y != -1) : !(this.y == other.y)) {
	        return false;
	    }
	    return true;
	}


	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + this.x;
	    hash = 53 * hash + this.y;
	    return hash;
	}

	public String toString() {
		return "Point: x:"+x+", y:"+y;
	}
    
}