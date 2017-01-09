package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class MetricCalculator {

	private HashMap<String, Metric> graph = new HashMap<>();
	private List<Class> jarClass;
	private String jar;

	public MetricCalculator(List<Class> cls, String jarName) {

		this.jarClass = cls;
		this.jar = jarName;
		addClass();
		calculateMetric();
		//System.out.println("staaaaaab: " + graph.get(cls).getInDegree());
	}

	// addClass
	public void addClass() {
		for (int i = 0; i < jarClass.size(); i++) {
			graph.put(jarClass.get(i).getName(), new Metric());
			System.out.println(jarClass.get(i).getName());
			//System.out.println("staaaaaab: " + graph.get(i).getInDegree());
		}
		System.out.println("List of packages: " + graph.keySet());
		System.out.println("size/amount: " + graph.size());
		
		
	}

	// calculateMetric
	public void calculateMetric() {
		try {
			File file = new File(jar);
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };

			ClassLoader cl = new URLClassLoader(urls);

			for (String name : graph.keySet()) {

				Class queryClass = Class.forName(name, false, cl);// cl loads
																	// data
				System.out.println("Package: " + name);
				System.out.println();
				System.out.println("class: " + queryClass.getName());
			//	graph.get(queryClass.getName()).setOutDegree(outDegree);
				System.out.println("Get InDegree-----: " + graph.get(queryClass.getName()).getInDegree());
				System.out.println("Get OutDegree-----: " + graph.get(queryClass.getName()).getOutDegree());
				System.out.println("Get Stability-----: " + graph.get(queryClass.getName()).getStability());
				new Reflection(queryClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
