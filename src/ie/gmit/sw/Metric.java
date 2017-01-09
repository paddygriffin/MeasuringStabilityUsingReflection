package ie.gmit.sw;

public class Metric {
	
	private int inDegree;
	private int outDegree;
	
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegre) {
		this.outDegree = outDegre;
	}
	
	public float getStability(){
		float stability = 1f;
		if(outDegree <= 0){
			stability = ((float)outDegree / (float)inDegree + (float)outDegree);
		}

		return stability;
	}	
}
