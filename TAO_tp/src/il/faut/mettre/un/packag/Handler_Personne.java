package il.faut.mettre.un.packag;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.lang.annotation.*;

public class Handler_Personne implements InvocationHandler {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface NotSelfArgument{};

    private Object obj;

    public static Object newInstance(Object obj) {
	return java.lang.reflect.Proxy.newProxyInstance(
	    obj.getClass().getClassLoader(),
	    obj.getClass().getInterfaces(),
	    new Handler_Personne(obj));
    }

    private Handler_Personne(Object obj) {
	this.obj = obj;
    }

    public Object invoke(Object proxy, Method m, Object[] args)
	throws Throwable
    {
        Object result;
	try {
	    System.out.println("before method " + m.getName());
	    
	    if ( m.isAnnotationPresent( NotSelfArgument.class) ) {

	    	System.out.println( m.getName() +" is NotSelfArg ");
	    	
	    	for ( int i=0; i<args.length; i++ ) {
	    		if ( args[i] == this.obj ) {
	    			System.out.println("Blocked car un des parametres est l'objet lui-meme");
	    			//return null;
	    		}
	    	}
	    	
	    }
	    
	    result = m.invoke(obj, args);
        } catch (InvocationTargetException e) {
	    throw e.getTargetException();
        } catch (Exception e) {
	    throw new RuntimeException("unexpected invocation exception: " +
				       e.getMessage());
	} finally {
	    System.out.println("after method " + m.getName());
	}
	return result;
    }

	public void pSetNom( String nom ) {
		System.out.println("Methode appellee");
	}
	
}
