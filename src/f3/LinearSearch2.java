package f3;

import java.util.Random;

public class LinearSearch2 {
	private Random random = new Random();
    
/***************** Sökning i osorterad int-array ***********************/
    /* Söker efter värde i int-array */
    public int indexOf( Object[] array, Object value ) {
        int res = -1;
        for( int i=0; ( i<array.length ) && ( res == -1 ); i++ ) {
            if( value.equals( array[ i ] ) ) {
                res = i;
            }
        }
        return res;
    }
    
    /* Skapa int-array med slumpvärden i intervallet [min,max] */
    public int[] randomArray( int n, int min, int max ) {
        int[] res = new int[ n ];
        for( int i = 0; i < n; i++ ) {
        	res[ i ] = random.nextInt(max - min + 1) + min;
        }
        return res;
    }
    
    /* Skriva int-array med bestämt antal värden per rad */    
    public void printArray(Object[] array, int rowCount, int width ) {
        for(int i = 0; i< array.length; i++ ) {
            System.out.printf( "%"+width+"s", array[ i ].toString() );
            if( ( i + 1 ) % rowCount == 0 ) {
                System.out.println();
            }
        }
    }
        
    public void searchArray() {
        int[] temp = randomArray( 100, 10, 99 );
        RealNbr[] numbers = new RealNbr[ temp.length ];
        for(int i=0; i<temp.length; i++) {
        	numbers[i] = new RealNbr( temp[i] );
        }
//        numbers[2] = new RealNbr(10);
        printArray( numbers, 10, 6 );
        for( int i=10; i<=50; i+=5 ) {
            System.out.println( i + ": " + indexOf( numbers, new RealNbr(i) ));
        }
    }    
/***********************************************************************/
   

    public static void main(String[] args) {
        LinearSearch2 ls = new LinearSearch2();
        ls.searchArray();
    }
}
