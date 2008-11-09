package j2se.g261.eda.automator.minimization;


import java.util.Vector;
import j2se.g261.eda.automator.minimization.MinGraph;
import java.util.HashMap;
import j2se.g261.eda.automator.minimization.Edge;

/**
*
* @author dmitriy
*/
public class Minimisation {
	
	private MinGraph edgeGraph;
	private Vector<Couple> different;
	private Vector<Character> uniq;
	private HashMap<Integer, Vector<Character>> storage;
	

	public Minimisation(MinGraph g){
		edgeGraph = g;
		different = new Vector<Couple>();
		uniq = new Vector<Character>();
		storage = new HashMap<Integer, Vector<Character>>();
	}
		
    private void addToStorage(int c, char i) {
        if (storage.containsKey(c)) {
            Vector<Character> v = storage.get(c);
            if (!v.contains(i)) {
                v.add(i);
            }
        } else {
            Vector<Character> v = new Vector<Character>();
            v.add(i);
            storage.put(c, v);
        }
    }
	
    
    private Vector<Character> difference(Vector<Character> a,Vector<Character> b){
    
    	Vector<Character> diff = new Vector<Character>();
    	for(char name : a){
    		
	    	if(!b.contains(name)){
	    		diff.add(name);
	    	}
	    	
    	}
    	return diff;
    }
	
	private void addAbsorbingState(){
		 for(Edge e : edgeGraph.getAll()){
			 
			 addToStorage(e.getIncoming(), e.getName());
			 
			 if (!uniq.contains(e.getName())){     
				 uniq.add(e.getName());
			 }
			 
		 }
			 int states = storage.size();
			 for(int j = 0; j < states+1; j++){
				 if (j == 1) j++;
				 Vector<Character> diff = new Vector<Character>();
				 
				 if(storage.get(j).size() == 0){
					 diff = uniq;
				 }
				 else{
				 diff = difference(uniq,storage.get(j));
				 }			 
				 int size = diff.size();
				 for(int k = 0; k < size; k++){
					 edgeGraph.add(new Edge(diff.get(k), j, states+1));
				 }
			 }
			 int size = uniq.size();
			 for(int m = 0; m < size; m++){
				 char name = uniq.get(m);
				 edgeGraph.add(new Edge(name, states+1, states+1));
			 } 
	}
	
	
	
	
	private Vector<Couple> findEquals(Couple c , Vector<Couple> toDelite){
		
		for(Couple p : different){
			if ((p.getFirst() == c.getSecond() || p.getSecond() == c.getSecond()) &&
					(p.getFirst() != c.getFirst() && p.getSecond() != c.getSecond())){
				if(!toDelite.contains(p)) toDelite.add(p);
			}
		}
		return toDelite;
	} 
	
	public MinGraph minimize(){
		Vector<Couple> diff = new Vector<Couple>();
		Vector<Couple> toDelite = new Vector<Couple>();
		addAbsorbingState();
		int num = storage.size() + 2;
		
		for(int i = 0; i < num; i++){
			if (i == 1) i++;
			Couple c = new Couple(i,1);
			diff.add(c);	
		}	
		
		for(int j = 0; j < num; j++){
				for (int k = 0; k < num; k++){
					if(j != 1 && k != 1 && j < k) different.add(new Couple(j,k));
				}
			}
		
		helpToMinimize(diff);
		
		
		for(Couple c : different){
			System.out.println(c.getFirst() + "=" + c.getSecond());
			toDelite = findEquals(c,toDelite);
		}
		
		for(Couple c : toDelite){
			different.remove(c);
		}
		
		
		for(Couple c : different){
			Vector<Edge> listFirst = new Vector<Edge>();
			Vector<Edge> listSecond = new Vector<Edge>();
			listFirst = edgeGraph.findIncomingEdge(c.getSecond());
			listSecond = edgeGraph.findOutgoingEdge(c.getSecond());
			
			for(Edge i : listFirst){
				edgeGraph.getAll().remove(i);
			}
			
			for(Edge i : listFirst){
				Edge e = new Edge (i.getName(),i.getIncoming(),c.getFirst());
				if(i.getIncoming() == c.getSecond()) continue;
				if(!edgeGraph.getAll().contains(e)) edgeGraph.getAll().add(e);
			}
			
			for(Edge j : listSecond){
				edgeGraph.getAll().remove(j);
			}
			
			for(Edge j : listSecond){
				Edge e = new Edge (j.getName(),c.getFirst(),j.getOutgoing());
				if(j.getOutgoing() == c.getSecond()) continue;
				if(!edgeGraph.getAll().contains(e)) edgeGraph.getAll().add(e);
			}		
			}
		
		Vector<Edge> list = edgeGraph.findIncomingEdge(storage.size()+1);
		
		for(Edge h : list){
			edgeGraph.getAll().remove(h);
		}	
		
		return edgeGraph;
		}
		
	
	
	private void helpToMinimize(Vector<Couple> tmp){
		
		while(true){
			
			Vector<Edge> listFirst = new Vector<Edge>();
			Vector<Edge> listSecond = new Vector<Edge>();
			Vector<Couple> newDiff = new Vector<Couple>();
			
			for(Couple t : tmp){
			listFirst = edgeGraph.findIncomingEdge(t.getFirst());
			listSecond = edgeGraph.findIncomingEdge(t.getSecond());
			
			for(Edge j : listFirst){
				for (Edge k : listSecond){
					if(j.getName() == k.getName()){
						
						if(j.getIncoming() < k.getIncoming()){
							Couple c =	new Couple(j.getIncoming(),k.getIncoming());
							if(different.contains(c)) newDiff.add(c);
							different.remove(c);
							}
			
						else{
						Couple c = new Couple(k.getIncoming(), j.getIncoming());
						if(different.contains(c)) newDiff.add(c);
						different.remove(c);
						}				
					}		
				}
			}
		}
			if(newDiff.isEmpty()) break;
		    tmp = newDiff;
		}	
		
	}

	
	

}


 


