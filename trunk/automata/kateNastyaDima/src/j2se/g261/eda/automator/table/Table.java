/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

/**
 *
 * @author Katerina
 */
public class Table {
    private HashMap<Integer, TableRecord> storage;
    private int index = 0;
    
    public Table(){
        storage = new HashMap<Integer, TableRecord> ();
    }
    
    public void add(int i,TableRecord j){
        if(!storage.containsKey(i)){
            storage.put(i, j);
        }
    }
    
    public void fillTable(){
        HashSet<Character> set = collectCaracterKeys();

        Iterator<Entry<Integer, TableRecord>> a = storage.entrySet().iterator(); 
        while(a.hasNext()){
            TableRecord t = a.next().getValue();
            
            Iterator<Character> b = set.iterator();
            while(b.hasNext()){
                t.add(b.next(), new Vector<Integer>());
            }
        }
        
    }
    
    public void clear(){
        storage.clear();
    }
    
    private HashSet<Character> collectCaracterKeys(){
        HashSet<Character> set = new  HashSet<Character>();
        Iterator<Entry<Integer, TableRecord>> a = storage.entrySet().iterator(); 
        while(a.hasNext()){
            TableRecord t = a.next().getValue();
            set.addAll(t.keySet());
        }
        return set;
        
    }
    
    
}
