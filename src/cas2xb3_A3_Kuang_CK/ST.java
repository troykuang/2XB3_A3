package cas2xb3_A3_Kuang_CK;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<String, City> {

	 private TreeMap<String, City> st;

	    /**
	     * Initializes an empty symbol table.
	     */
	    public ST() {
	        st = new TreeMap<String, City>();
	    }


	    /**
	     * Returns the value associated with the given key in this symbol table.
	     *
	     * @param  key the key
	     * @return the value associated with the given key if the key is in this symbol table;
	     *         <tt>null</tt> if the key is not in this symbol table
	     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	     */
	    public City get(String cityName) {
	        return st.get(cityName);
	    }

	    /**
	     * Inserts the specified key-value pair into the symbol table, overwriting the old 
	     * value with the new value if the symbol table already contains the specified key.
	     * Deletes the specified key (and its associated value) from this symbol table
	     * if the specified value is <tt>null</tt>.
	     *
	     * @param  key the key
	     * @param  val the value
	     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	     */
	    public void put(String cityName, City city) {
	    	st.put(cityName,city);
	    }

	    /**
	     * Removes the specified key and its associated value from this symbol table     
	     * (if the key is in this symbol table).
	     *
	     * @param  key the key
	     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	     */
	    public void delete(String cityName) { 
	        st.remove(cityName);
	    }

	    /**
	     * Returns true if this symbol table contain the given key.
	     *
	     * @param  key the key
	     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
	     *         <tt>false</tt> otherwise
	     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	     */
	    public boolean contains(java.lang.String cityName) {
	        return st.containsKey(cityName);
	    }

	    /**
	     * Returns the number of key-value pairs in this symbol table.
	     *
	     * @return the number of key-value pairs in this symbol table
	     */
	    public int size() {
	        return st.size();
	    }

	    /**
	     * Returns true if this symbol table is empty.
	     *
	     * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
	     */
	    public boolean isEmpty() {
	        return size() == 0;
	    }

	    /**
	     * Returns all keys in this symbol table.
	     * <p>
	     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
	     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
	     *
	     * @return all keys in this symbol table
	     */
	    public Iterable<String> cityNames() {
	        return st.keySet();
	    }
	    
	    public void  printST(){
	    	for (String currentCityName : cityNames()){
	    		 System.out.println(st.get(currentCityName));
	    	 }
	    }
}
