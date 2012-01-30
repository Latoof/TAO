import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Application {

	
	public static void main( String[] argv ) throws InstantiationException, IllegalAccessException {

		Class<?> c = null;
		try {
			c = Class.forName("Mademoiselle");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Object o = c.newInstance();
		
		Class<?> c2 = o.getClass();
		System.out.println(c2.toString());

	
		Class<?> c3 = null; 
		Mademoiselle mad = new Mademoiselle();
		conversion( mad, c3 );
			
	}

	
	public static Object conversion( Object source, Class<?> targetClass ) {
		
		Class<?> cSource = source.getClass();
		System.out.println(cSource.getName());
		Field[] fSource = cSource.getDeclaredFields(); // Pas pareil que "getFields()"
		Annotation[] fAnnot = cSource.getAnnotations();
		Method[] fMethod = cSource.getMethods();
		
		for (int i=0; i< fSource.length; i++) {
			System.out.println(fSource[i]);
		}
		
		for (int i=0; i< fAnnot.length; i++) {
			System.out.println(fAnnot[i]);
		}
		
		System.out.println("Methods : ");
		for (int i=0; i< fMethod.length; i++) {
			System.out.println(fMethod[i]);
		}
		System.out.println("Finished");
		
	return targetClass;
		
	}
}
