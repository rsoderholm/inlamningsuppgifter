package laboration3;
import java.util.*;

public class Solution {
    /* Uppgift 1 */
    public int indexOf( double[] array, double value ) {
        for( int i = 0;  i<array.length; i++  ) {
            if( value == array[ i ] )
                return i;
        }
        return -1;
    }
    
    /* Uppgift 2 */
    public int indexOf( String[] array, String value ) {
        for( int i = 0;  i<array.length; i++  ) {
            if( value.equals( array[ i ] ) )
                return i;
        }
        return -1;
    }
    
    /* Uppgift 3 */
    public void uppgift3() {
        Laboration3 lab2 = new Laboration3();
        LinearSearch ls = new LinearSearch();
        int[] arr = lab2.randomIntArray(10000);
        int res;
        long stop, start = System.currentTimeMillis();
        for( int i=0; i<=9999; i++ ) {
            res = ls.indexOf(arr, i);
        }
        stop = System.currentTimeMillis();
        System.out.println("Tid: " + (stop-start) + " ms");
    }
    
    
    private static void swap( Object[] array, int elem1, int elem2 ) {
        Object temp = array[ elem1 ];
        array[ elem1 ] = array[ elem2 ];
        array[ elem2 ] = temp;
    }
    
    public static void shuffle( Object[] array ) {
        int pos;
        for( int i = array.length - 1; i > 0; i-- ) {
            pos = ( int )( Math.random() * ( i + 1 ) );
            swap( array, i, pos );
        }
    }
    
    public RealNbr[] getRealNbrArray( int n ) {
        RealNbr[] tal = new RealNbr[n];
        for( int i=0; i<tal.length; i++ )
            tal[i] = new RealNbr(i);
        Solution.shuffle(tal);
        return tal;
    }
    
    public int indexOf( RealNbr[] array, RealNbr value) {
        for( int i = 0;  i<array.length; i++  ) {
            if( value.equals( array[ i ] ) )
                return i;
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
    
    public void uppgift8() {
        Laboration3 lab2 = new Laboration3();
        ArrayList<Integer> arr1 = lab2.fillInteger( 100000, 10000, 50000 );
        ArrayList<RealNbr> arr2 = new ArrayList<RealNbr>();
        for(Integer i : arr1) { // eller for(int i=0; i<arr1.size(); i++) { ...arr1.get(i).intValue() ...}
            arr2.add( new RealNbr( i.intValue() ) );
        }
        Collections.sort(arr2);
        for( int i=10000; i<=50000; i+=10000 ) {
            System.out.println( i + ": " + Collections.binarySearch(arr2, new RealNbr(i)) );
        }
    }
    
    /* Uppgift 9 */
    public void uppgift9() {
        Laboration3 lab2 = new Laboration3();
        BinarySearch bs = new BinarySearch();
        int[] arr = lab2.randomIntArray(10000);
        Arrays.sort(arr);
        int res;
        long stop, start = System.currentTimeMillis();
        for( int i=0; i<=9999; i++ ) {
            res = bs.binarySearch(arr, i);
            System.out.println(res);
        }
        stop = System.currentTimeMillis();
        System.out.println("Tid: " + (stop-start) + " ms");
    }
    
    /* Uppgift 10 */
    public int binarySearch( long[] array , long value ) {
        int res = -1, min = 0, max = array.length - 1, pos;
        while( ( min <= max ) && ( res == -1 ) ) {
            pos = (min + max) / 2;
            if( value == array[ pos ] )
                res = pos;
            else if( value < array[ pos ])
                max = pos - 1;
            else
                min = pos + 1;
        }
        return res;
    }
    
    /* Uppgift 11 */
    public int binarySearch( String[] array , String value ) {
        int res = -1, compare , min = 0, max = array.length - 1, pos;
        while( ( min <= max ) && ( res == -1 ) ) {
            pos = (min + max) / 2;
            compare = value.compareTo( array[ pos ] );
            if( compare == 0 )
                res = pos;
            else if( compare < 0 )
                max = pos - 1;
            else
                min = pos + 1;
        }
        return res;        
    }
    
    /* Uppgift 12 */
    public int binarySearch( Object[] array, Object key ) {
        int res = -1, compare, min = 0, max = array.length - 1, pos;
        Comparable comp = (Comparable)key;
        while( ( min <= max ) && ( res == -1 ) ) {
            pos = (min + max) / 2;
            compare = comp.compareTo( array[ pos ] );
            if( compare == 0 )
                res = pos;
            else if( compare < 0 )
                max = pos - 1;
            else
                min = pos + 1;
        }
        return res;
    }

    /* Uppgift 13 */
    public int binarySearch( List list, Object key ) {
        int res = -1, compare, min = 0, max = list.size() - 1, pos;
        Comparable comp = (Comparable)key;
        while( ( min <= max ) && ( res == -1 ) ) {
            pos = (min + max) / 2;
            compare = comp.compareTo( list.get( pos ) );
            if( compare == 0 )
                res = pos;
            else if( compare < 0 )
                max = pos - 1;
            else
                min = pos + 1;
        }
        return res;
    }
    
    /* Uppgift 16 */
    public int binarySearch( Object[] array, Object value, Comparator comp) {
        int res = -1, compare, min = 0, max = array.length - 1, pos;
         while( ( min <= max ) && ( res == -1 ) ) {
            pos = (min + max) / 2;
            compare = comp.compare( value, array[ pos ] );
            if( compare == 0 )
                res = pos;
            else if( compare < 0 )
                max = pos - 1;
            else
                min = pos + 1;
        }
        return res;
    }
    
    /* Uppgift 17 */
    public Object[] getArray( int n, ObjectCreator creator ) {
        Object[] res = new Object[n];
        for(int i=0; i<res.length; i++) {
            res[i] = creator.nextObject();
        }
        return res;
    }
    
    public static void main(String[] args) {
        Solution prog = new Solution();
        Person[] personer = { new Person("Tommy", "Davidsson", 21), new Person("Sven", "Davidsson", 21),
                              new Person("Rut", "Einarsson", 21), new Person("Inga", "Al", 24),
                              new Person("Rolf", "Davidsson", 21)};
        Arrays.sort( personer, new FamilyNameFirstName() );
        for( Person p : personer )
            System.out.println(p);
        System.out.println( prog.binarySearch(personer, new Person("Inga", "Al", 24), new FamilyNameFirstName() ));
        System.out.println( prog.binarySearch(personer, new Person("Tommy", "Davidsson", 24), new FamilyNameFirstName()));
Object[] perm = prog.getArray( 8, new Permutationer( "OLA" ) );
for( int i = 0; i < perm.length; i++ )
    System.out.println( perm[ i ] );
        
//        RealNbr[] arr = prog.getRealNbrArray(10);
//        for(RealNbr tal : arr ) {
//            System.out.println(tal.getValue());
//        }
    }
}

/* Uppgift 14 */
class FamilyName implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        String fName1 = p1.getFamilyName();
        String fName2 = p2.getFamilyName();
        return fName1.compareTo(fName2);
    }
}

/* Uppgift 15 */
class FamilyNameFirstName implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        int res = p1.getFamilyName().compareTo(p2.getFamilyName());
        if (res == 0) {
            res = p1.getFirstName().compareTo(p2.getFirstName());
        }
        return res;
    }
}

interface ObjectCreator {
    public Object nextObject();
}

class RandomNumbers implements ObjectCreator {
    private int min, max;
    
    public RandomNumbers( int min, int max ) {
        this.min = min;
        this.max = max;
    }
    public Object nextObject() {
        int random = (int)( Math.random() * ( max - min + 1 ) ) + min;
        return new Integer(random);
    }
}

/* Uppgift 18 */
class Permutationer implements ObjectCreator {
    private Laboration3 lab2 = new Laboration3();
    private ArrayList<String> word;  
    int index = -1;
    
    public Permutationer( String str ) {
        word = lab2.permute(str);
    }

    public Object nextObject() {
        index = ( index + 1 ) % word.size();
        return word.get( index );
    }
}

