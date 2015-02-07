package laboration1;

public class Uppgifter {
	
	public static void print(int min, int max) {
		if(min<=max){
			System.out.print(min + " ");
			print(min + 1, max);
		}else{
			System.out.println();
		}
	}
	
	public static void everySecondReverse(String txt, int pos) {
		if(pos>=0 && pos<txt.length()){
			System.out.print(txt.charAt(pos));
			everySecondReverse(txt, pos - 2);
		}else{
			System.out.println();
		}
	}

	public static String reverse( String str ) {
		if(str.length()>1)
			return reverse(str.substring(1)) + str.charAt(0);
		else
			return str;
		
	}
	
	public static void mystery6(int a) {
		 if( a >= 0) {
		 System.out.println("a=" + a);
		 mystery6(a-4);
		 mystery6(a-3);
		 }
		}
	
	public static void main(String[] args) {
		// Uppgift 1
		print( 10, 15 );
		print( 15, 10 );
		print( -3, 4 );
		
		System.out.println();
		
		// Uppgift 2
		everySecondReverse( "Student", 6 ); // Resultat: teuS
		everySecondReverse( "Lärare", 3 ); // Resultat: aä
		everySecondReverse( "Förälder", 17 ); // Resultat:
		everySecondReverse( "Barn", -2 ); // Resultat: 
		
		 // Uppgift 13
		System.out.println( reverse( "Student" ) ); // Resultat: tnedutS
		
		// Uppgift 17
		mystery6(10);
	}

}
