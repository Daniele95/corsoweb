import java.util.HashMap;
import java.util.Map;

public class Mappa {
	
	/*
	public static void main(String[] args) {
	
		String[] arr = {"a", "b", "a", "c", "b","a","a","v","v"};
		
		Map<String, Integer> map = new HashMap<>();
		
		
		for (int i = 0 ; i<arr.length ; i++) {
			if( map.get(arr[i]) == null ) {
				map.put(arr[i], 1);
				System.out.println(map.get(arr[i]));
			}
			if( map.get(arr[i]) != null )
				map.put(arr[i], map.get(arr[i]) + 1);
		}
		System.out.println(map);
		
	}
	*/
	
	public Map<String,Integer> wordCount(String[] strings) {
		Map<String,Integer> result = new HashMap<>();
		for(String s:strings) {
			Integer counter = result.get(s);
			if (counter == null) {
				counter = 1;
			} else {
				counter++;
			}
			result.put(s, counter);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		Map<String,Integer> vehicles = new HashMap<String,Integer>();
	}
		
	/*
	
	
	"abcdefghi456456abcdefghi"
	
	"abcdefghiabcdefghi"
	
	partiamo dalla metà della stringa
	controlliamo: "abcdefghi456" è uguale a "456abcdefghi"?
		se si ? return abcdefghi456
	se no?abcdefghi45
	
	
	
	
	
*/
	
	
	
}