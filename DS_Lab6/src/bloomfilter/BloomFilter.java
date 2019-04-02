package bloomfilter;

import java.util.BitSet;

public class BloomFilter {

	//determine the size of bit array
	private int size = 2<<24/* = m*/;
	//determine the number of hash function (different seeds)
	private int[] seeds = {1,2,3,4,5,6,7,8,9,10};/* = new int[]{...}*/

	private BitSet bits = new BitSet(size);

	public BloomFilter() {}

	//add an element to Bloom Filter
	public void add(String str) {
		//init();
		for(int  i = 0 ; i < seeds.length;i++){
			Integer a = hash(seeds[i],str);
			bits.set(a,true);
		}
	}

	//query whether Bloom Filter contains the element
	public boolean query(String str) {
		for(int i = 0;i < seeds.length;i++){
			int s = hash(seeds[i],str);
			if(!bits.get(s))
				return false;
		}
		return true;
	}

	//Your hash function
	private int hash(int seed, String str) {
		int k = 0;
		for(int i = 0; i < str.length(); i++) {
			k = k*seed + str.charAt(i)+seed;
		}
		return (size-1)&k;
	}
}
