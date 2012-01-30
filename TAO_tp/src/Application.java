
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
		
		
		
		

	}

	
	public static Object conversion( Object source, Class<?> targetClass ) {
		return targetClass;
		
	}
}
