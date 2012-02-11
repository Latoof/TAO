package il.faut.mettre.un.packag;
import il.faut.mettre.un.packag.Handler_Personne.NotSelfArgument;


public interface IPersonne {

		@NotSelfArgument
		public String getNom();
		
		//@Retention(RetentionPolicy.RUNTIME)
		public void setNom( String nom );

		
		public int getAge();
		public void setAge( int age );

		
		public String getAdresse();
		public void setAdresse( String adr );
		
		@NotSelfArgument
		public boolean equals( Object obj );
	
}
