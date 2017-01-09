package ie.gmit.sw;

public class Metric {
	
	private int inDegree;
	private int outDegree;
	private String className;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	
	public float getStability(){
		float stability = 1f;
		if(outDegree <= 0){
			stability = ((float)outDegree / (float)inDegree + (float)outDegree);
		}

		return stability;
	}	
}
