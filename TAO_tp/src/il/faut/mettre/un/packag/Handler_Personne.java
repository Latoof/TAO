package il.faut.mettre.un.packag;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.lang.annotation.*;

public class Handler_Personne implements InvocationHandler {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface NotSelfArgument{};
	/* Syntaxes liees aux Annotations, rien a voir 
	 * avec cette classe Handler_Personne
	 */

    private Object obj;
    
    /* Methode de l'Interface "InvocationHandler" qui permet d'instancier
     * dynamiquement un objet (voir methode "t_proxy" de Application.java) 
     */
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
	    
	    /* Je suis pas parvenu a faire grand chose avec les Annotations
	     * mais le principe est qu'ici on peut faire des verifs, et ajustements
	     * selon plusieurs parametres, avant que la methode soit appelee.
	    if ( m.isAnnotationPresent( NotSelfArgument.class) ) {

	    	System.out.println( m.getName() +" is NotSelfArg ");
	    	
	    	for ( int i=0; i<args.length; i++ ) {
	    		if ( args[i] == this.obj ) {
	    			System.out.println("Blocked car un des parametres est l'objet lui-meme");
	    			//return null;
	    		}
	    	}
	    	
	    }
	    */
	    
	    result = m.invoke(obj, args);
	    /* Invocation effective de la methode */
	    
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

}
