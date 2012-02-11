package il.faut.mettre.un.packag;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import il.faut.mettre.un.packag.*;

public class Application {

	
	public static void main( String[] argv ) throws InstantiationException, IllegalAccessException {

		Class<?> c = null;
		try {
			c = Class.forName("il.faut.mettre.un.packag.Mademoiselle");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Class<?> cAlt = null;
		try {
			cAlt = Class.forName("il.faut.mettre.un.packag.Monsieur");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Object o = c.newInstance();
		
		Object objTest = c.newInstance();
		 
		
		Monsieur mon = new Monsieur("SonNom", "14 rue machinechouette", 23, true);
		Object mad = conversion_t( mon, Mademoiselle.class );
		
		System.out.println( "Objet de sortie : "+((Mademoiselle) mad).getNom()+", "+ ((Mademoiselle) mad).getAge() +" ans - "+((Mademoiselle) mad).getAdresse() );
			
		proxy_t();
	}
	
	
	/* Pour cloner un Objet source dans une classe Cible 
	 * Ici, on se limite a copier les attributs communs aux deux objets. 
	 */
	public static Object conversion_t( Object source, Class<?> targetClass ) throws InstantiationException, IllegalAccessException {

		Class<?> sourceClass = source.getClass();
		Field[] sFields = sourceClass.getDeclaredFields();
		
		Object oOut = targetClass.newInstance();
		
		/* On parcourt chaque attribut */
		for ( int i=0; i < sFields.length; i++ ) {
			
			try {
				// On cherche si le Field existe dans la source ET dans la distination
				Field f = targetClass.getDeclaredField( sFields[i].getName() ); 
				/* Si l'attribut n'existe pas dans la Classe cible, une exception est declenchee et ca break
				 * pour l'attribut en cours (donc on passe direct a l'attribut suivant
				 */
				
				System.out.println("Trying "+f.getName());
				
				//f.set(oOut, sFields[i].get( source )); 
				// Mettre dans l'objet de sortie le contenu du champs de meme nom venant de la classe source.
				/* --> Ne fonctionne pas pour les attributs privees, donc il faut utiliser les methodes setters et getters ! */

				
				char[] stringArray = f.getName().toCharArray();
				stringArray[0] = Character.toUpperCase(stringArray[0]);
				String fNameMaj = new String(stringArray);
				/* Ca c'etait juste pour transformer la premiere lettre de l'attribut en Maj xD */

				Method mSet = targetClass.getDeclaredMethod("set"+fNameMaj, f.getType());
				Method mGet = sourceClass.getDeclaredMethod("get"+fNameMaj);
				/* Comme on est super malin, on regarde si des setters/getters existent pour l'attribut en cours */
				
				System.out.println("Found "+mSet.getName()+" and "+mGet.getName());
				/* A ce point-ci, le programme doit etre content. */
				
	
				mSet.invoke( oOut, mGet.invoke(source) );
				/* On invoque la methode Setter du l'objet cible en passant en argument
				 * le resultat de la methode Getter de la source.
				 * Les getters et les setters ont des nommages clairement etablis par 
				 * des conventions, donc si les noms ne respectent pas la convention, bah tant pis !
				 */
				
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Ignored : "+sFields[i].getName());
				
			} catch (NoSuchMethodException e) {
				System.out.println("Getter/Setter not found for "+sFields[i].getName());
				//e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {

			}
			
		}
		
		return oOut;
	}

	
	public static Object conversion( Object source, Class<?> targetClass ) throws InstantiationException, IllegalAccessException {
		
		Class<?> classeSource = source.getClass();
		System.out.println("Classe : "+classeSource.getName());
		
		// Pas pareil que "getFields()" qui recupere tous, sauf ce qui est explicitement declare dans la classe
		Field[] fieldSource = classeSource.getDeclaredFields(); 
				
		Method[] methodSource = classeSource.getDeclaredMethods();
		
		/*
		System.out.println("Fields : ");
		for (int i=0; i< fieldSource.length; i++) {
			System.out.println(fieldSource[i]);
		}
		
		System.out.println("Methods : ");
		for (int i=0; i< methodSource.length; i++) {
			System.out.println(methodSource[i]);
		}
		*/
		
		System.out.println("Analyse completed");
		
		Field[] fieldCible = targetClass.getDeclaredFields();
//		Method[] methodCible = targetClass.getDeclaredMethods();
		
		Field[] fieldIntersect = new Field[fieldSource.length];
		int ind=0;
		
		for(int i=0; i<fieldSource.length; i++){
			String fSrc = fieldSource[i].getName();
			for(int j=0; j<fieldCible.length; j++){
				String fTgt = fieldCible[j].getName();
//				System.out.println(fSrc.substring(fSrc.lastIndexOf(".")+1)+"\t-\t"+fTgt.substring(fTgt.lastIndexOf(".")+1));
				if(fSrc.substring(fSrc.lastIndexOf(".")+1) == fTgt.substring(fTgt.lastIndexOf(".")+1)){
					fieldIntersect[ind++]=fieldCible[i];
				}
			}
		}
		
//		Method[] methodIntersect = new Method[methodSource.length];
//		ind=0;
//		
//		for(int i=0; i<methodSource.length; i++){
//			String mSrc = methodSource[i].getName();
//			for(int j=0; j<methodCible.length; j++){
//				String mTgt = methodCible[j].getName();
//				System.out.println(mSrc.substring(mSrc.lastIndexOf(".")+1)+"\t-\t"+mTgt.substring(mTgt.lastIndexOf(".")+1));
//				if(methodSource[i] == methodCible[j]){
//					methodIntersect[ind++]=methodCible[i];
//				}
//			}
//		}
		
		for(int i=0; i<fieldIntersect.length; i++){
			System.out.println(fieldIntersect[i]);
		}
		
//		for(int i=0; i<methodIntersect.length; i++){
//			System.out.println(methodIntersect[i]);
//		}
				
		Object converted = targetClass.newInstance();
		System.out.println( converted+" - "+source);
		System.out.println( ((Mademoiselle) source).getTourDePoitrine());
		
		try {
			Class[] types = new Class[]{ String.class, String.class, int.class, boolean.class };
			Constructor ct = targetClass.getConstructor( types );
			//System.out.println( ct[1].toString() );
			
			Object objTest2 = ct.newInstance("nomTest", "AdresseTest", 23, true );
			System.out.println( "Tentative d'instance avec constructeur : "+((IPersonne)objTest2).getNom() );
			
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return converted;
		
	}
	
	public static void proxy_t() {
		Monsieur m = new Monsieur();
		
		 IPersonne p = (IPersonne) Handler_Personne.newInstance( m );
		 p.setNom("Trolilol !");
		 p.equals( m );
		
		 System.out.println(p.getNom());
	}
	

}
