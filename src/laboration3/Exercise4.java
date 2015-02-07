package laboration3;

import f3.RealNbr;

public class Exercise4 {
	
	private static void swap( Object[] array, int elem1, int elem2 ) {
        Object temp = array[ elem1 ];
        array[ elem1 ] = array[ elem2 ];
        array[ elem2 ] = temp;
    }

	public static void shuffle( Object[] obj ){
		int pos;
        for( int i = obj.length - 1; i > 0; i-- ) {
            pos = ( int )( Math.random() * ( i + 1 ) );
            swap( obj, i, pos );
        }
	}
	
	public RealNbr[] getRealNbrArray( int n ){
		RealNbr[] arr = new RealNbr[n];
		for( int i=0; i<arr.length; i++ )
            arr[i] = new RealNbr(i);
		shuffle(arr);
		
		return arr;
	}
	
	public int indexOf( RealNbr[] array, RealNbr value) {
		for(int i = 0; i < array.length; i++){
			if( value.equals(array[i])){
				return i;
			}
		}
		return -1;
	}
	
	public int indexOf( Object[] array, Object obj) {
		for( int i = 0;  i<array.length; i++  ) {
            if( obj.equals( array[ i ] ) )
                return i;
        }
        return -1;
	}
	public static void main(String[] args) {
		Integer[] arr = new Integer[5];
		for( int i=0; i<arr.length; i++ ) {
		 arr[i] = new Integer(i);
		}
		Exercise4.shuffle( arr );
		for( Integer elem : arr )
		 System.out.println(elem);

	}
	
	public int binarySearch( long[] array, long value) {
		int pos, min = 0, max = array.length, res = -1;
		while(min <= max && res == -1){
			pos = (min + max) / 2;
			if(value == array[pos]){
				res = pos;
			}else if(value < array[pos]){
				max = pos - 1;
			}else{
				min = pos + 1;
			}
		}
		return res;
	}

}
