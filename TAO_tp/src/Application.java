
public class Application {

	
	public static void main( String[] argv ) throws InstantiationException, IllegalAccessException {
		Mademoiselle ma;
		Class<?> c = null;
		try {
			c = Class.forName("Mademoiselle");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Object o = c.newInstance();
		
		Class<?> c2 = o.getClass();
		/* ... */
		
		
		
		

	}
	
}
