package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricCalculator {

	private Map<String, Metric> graph = new HashMap<>();
	private List<Class> jarClass;
	private String jar;
	private Reader jr = new Reader();

	public MetricCalculator(String jarName) throws FileNotFoundException, IOException {

	
		this.jar = jarName;
		this.jarClass = jr.getJar(jar);
		addClass();
		calculateMetric();
		
	}

	// addClass to map
	public void addClass() {
		System.out.println("Adding Classes to Map!\n");
		System.out.println("Classes to be Added:");
		
		for (int i = 0; i < jarClass.size(); i++) {
			graph.put(jarClass.get(i).getName(), new Metric());
			graph.get(jarClass.get(i).getName()).setClassName(jarClass.get(i).getName());
			System.out.println(jarClass.get(i).getName());
						
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
				reflection(queryClass);											
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Object[][] getData(){
		int i = 0;
		Object[][] data = new Object[graph.size()][4];
		for(Metric m : graph.values()){ //for every metric in graph.values
			data[i][0] = m.getClassName();
			data[i][1] = m.getInDegree();
			data[i][2] = m.getOutDegree();
			data[i][3] = m.getStability();
			i++;
		}
			return data;
		
	}

	private void reflection(Class queryClass) {
		
		List<String> classList = new ArrayList<String>();
		int outdegree = 0;

		Class[] interfaces = queryClass.getInterfaces();
		for(Class i : interfaces){
			
			if(graph.containsKey(i.getName())) {
				if(!classList.contains(i.getName())){
					//System.out.println(i.getName());
					classList.add(i.getName());
					outdegree++;
					Metric m = graph.get(i.getName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}

		Constructor[] cons = queryClass.getConstructors(); //Get the set of constructors
		Class[] constructorParams;

		for(Constructor c : cons){

			constructorParams = c.getParameterTypes();
			for(Class param : constructorParams){
				//System.out.println(param.getName());
				if(graph.containsKey(param.getName())){
					//System.out.println(param.getName());
					if(!classList.contains(param.getName())){
						//System.out.println(param.getName());
						classList.add(param.getName());
						outdegree++;
						Metric m = graph.get(param.getName());
						m.setInDegree(m.getInDegree() + 1);
					}
				}
			}
		}

		Field[] fields = queryClass.getDeclaredFields();

		for(Field f : fields)
		{
			Type type = f.getType();
			//System.out.println(type.getTypeName());
			if(graph.containsKey(type.getTypeName())){
				if(!classList.contains(type.getTypeName())){
					//System.out.println(type.getTypeName());
					classList.add(type.getTypeName());
					outdegree++;
					Metric m = graph.get(type.getTypeName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}

		Method[] methods = queryClass.getDeclaredMethods(); //Get the set of methods
		Class[] methodParams;

		for(Method m : methods){

			Class methodReturnType = m.getReturnType();
			//System.out.println(methodReturnType.getName());
			if(graph.containsKey(methodReturnType.getName())){
				if(!classList.contains(methodReturnType.getName())){
					//System.out.println(methodReturnType.getName());
					classList.add(methodReturnType.getName());
					outdegree++;
					Metric mc = graph.get(methodReturnType.getName());
					mc.setInDegree(mc.getInDegree() + 1);
				}
			}

			methodParams = m.getParameterTypes(); //Get method parameters
			for(Class mp : methodParams){
				//System.out.println(mp.getName());
				if(graph.containsKey(mp.getName())){
					if(!classList.contains(mp.getName())){
						//System.out.println(mp.getName());
						classList.add(mp.getName());
						outdegree++;
						Metric bm = graph.get(mp.getName());
						bm.setInDegree(bm.getInDegree() + 1);
					}
				}
			} 
		}
		System.out.println();
		System.out.println("Class: " + queryClass.getName());
		graph.get(queryClass.getName()).setOutDegree(outdegree);
		System.out.println("Indegree: " + graph.get(queryClass.getName()).getInDegree());		
		System.out.println("Outdegree: " + graph.get(queryClass.getName()).getOutDegree());		
		System.out.println("Stability: " + graph.get(queryClass.getName()).getStability());
		
	}

	

}
