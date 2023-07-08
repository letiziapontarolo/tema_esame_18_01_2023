package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private Graph<Location, DefaultWeightedEdge> grafo;
	private NYCDao dao;
	private Map<String, Location> idMap;
	private List<Arco> archi;
	
	public Model() {
		
		dao = new NYCDao();
	}
	
	public List<String> listaProvider() {
		return this.dao.listaProvider();
	}
	
	public double distanza(double lat1, double lon1, double lat2, double lon2) {
		
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			
			dist = dist * 1.609344;
			return dist;
		
		
	}
	}
	
	public void creaGrafo(String provider, double soglia) {
		
		grafo = new SimpleWeightedGraph<Location, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		idMap = new HashMap<String, Location>();
		this.dao.creaVertici(idMap, provider);
		Graphs.addAllVertices(this.grafo, idMap.values());

		
		for (Location l1 : idMap.values()) {
			for (Location l2 : idMap.values()) 
				if (l1.getName().compareTo(l2.getName())>0) {
					double distanza = distanza(l1.getLat(), l1.getLon(), l2.getLat(), l2.getLon());
					if (distanza <= soglia) {
						Graphs.addEdgeWithVertices(this.grafo, l1, l2, distanza);
						
					}
				}
			
		}
		
	}
	
	public String analisiVicini() {
		
		String result = "";
		
		List<Location> listaLocation = new ArrayList<Location>();
		int max = 0;
		for (Location l : idMap.values()) {
			if (Graphs.neighborListOf(this.grafo, l).size() > max) {
				max = Graphs.neighborListOf(this.grafo, l).size();
			}
		}
		
		for (Location ll : idMap.values()) {
			if (Graphs.neighborListOf(this.grafo, ll).size() == max) {
				listaLocation.add(ll);
				
			}
		}
		
		Collections.sort(listaLocation, new Comparator<Location>() {
			 @Override
			 public int compare(Location l1, Location l2)
			 {
			 return l1.getName().compareTo(l2.getName());
			 }});

		
		for (Location lll : listaLocation) {
			result = result + lll.getName() + ", #vicini = " + max + "\n";
		}
		
		return result;
		
		
	}
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
		}
		 public int numeroArchi() {
		return this.grafo.edgeSet().size();
		}
	
	
}
