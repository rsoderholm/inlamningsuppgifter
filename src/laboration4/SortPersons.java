package laboration4;
import java.util.*;

public class SortPersons {
    public static void main(String[] args) {
        Person[] persons = { new Person( "841012-1234", "Martin", "Hansson", 185, 76.3 ),
            new Person( "980914-1111", "Artur", "Lindberg", 152, 37.3 ),
            new Person( "830104-2222", "Eva", "Rutig", 169, 58.3 ),
            new Person( "870909-3333", "Rutger", "Al", 193, 87.4 ),
            new Person( "850307-4444", "Anna", "Stenbäck", 171, 56.9 ),
            new Person( "920637-5555", "Tomas", "Rund", 169, 113.2 )};
        
        Arrays.sort( persons, new IdAscending() );
        for( int i = 0; i < persons.length; i++ )
            System.out.println(persons[ i ]);
    }
}
