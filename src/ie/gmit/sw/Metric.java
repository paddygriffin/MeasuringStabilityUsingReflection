package ie.gmit.sw;

/**
 * @author Patrick griffin G00314635
 *
 */
public class Metric {

	private int inDegree;// other classes that use this class
	private int outDegree;// different classes used by a specific class(metric)
	private String className; // name of class

	/**
	 * @return class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return
	 */
	public int getInDegree() {
		return inDegree;
	}

	/**
	 * @param inDegree
	 */
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	/**
	 * @return Returns the outDegree as an int.
	 */
	public int getOutDegree() {
		return outDegree;
	}

	/**
	 * @param outDegree
	 */
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	/**
	 * Uses the inDegree and outDegree to calculate the stability.
	 *
	 * @return Returns the positional stability as a float
	 */
	public double getStability() {
		float stability = 1f;

		if (getOutDegree() > 0) {
			stability = ((float) getOutDegree() / ((float) getInDegree() + (float) getOutDegree()));
		} else {
			stability = 0f;
		}
		return Math.round(stability * 100d) / 100d;
	}
}
