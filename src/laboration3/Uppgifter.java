package laboration3;

public class Uppgifter {

	public int indexOf( double[] array, double value) {
    	for(int i = 0; i<array.length; i++){
    		if(array[i] == value){
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    public int indexOf( String[] array, String value ) {
    	for(int i = 0; i<array.length; i++){
    		if(array[i].equals(value)){
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    public static void main(String[] args)
    {
    	Laboration3 app = new Laboration3();
		int[] array = app.randomIntArray(10000);
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++)
		{
			BinarySearch.binarySearch(array, i);
		}
		
		long stopp = System.currentTimeMillis();
		System.out.print(stopp-start);
	}
}
