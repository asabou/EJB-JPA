package com.tpjad.ejbjpa.groceries.utils;

import com.tpjad.ejbjpa.groceries.interfaces.IGroceryServiceR;
import com.tpjad.ejbjpa.groceries.interfaces.ILoginServiceR;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class ContextLookupUtils {
    public static ILoginServiceR lookupLoginServiceJNDIEJBWF() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);
        return (ILoginServiceR)
                context.lookup("ejb:/com.tpjad.ejbjpa.groceries.server/LoginBean!com.tpjad.ejbjpa.groceries.interfaces.ILoginServiceR?stateful");
    }

    public static ILoginServiceR lookupLoginServiceJNDIWF() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);
        return (ILoginServiceR)
                context.lookup("com.tpjad.ejbjpa.groceries.server/LoginBean!com.tpjad.ejbjpa.groceries.interfaces.ILoginServiceR");
    }

    public static IGroceryServiceR lookupGroceryServiceJNDIWF() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);
        return (IGroceryServiceR) context.lookup("com.tpjad.ejbjpa.groceries.server/GroceryBean!com.tpjad.ejbjpa.groceries.interfaces" +
                ".IGroceryServiceR");
    }

    public static IGroceryServiceR lookupGroceryServiceJNDIEJBWF() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);
        return (IGroceryServiceR) context.lookup("ejb:/com.tpjad.ejbjpa.groceries.server/GroceryBean!com.tpjad.ejbjpa.groceries.interfaces" +
                ".IGroceryServiceR");
    }

    public static ILoginServiceR lookupLoginServiceJNDIGF() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        final Context context = new InitialContext(jndiProperties);
        return (ILoginServiceR)
                context.lookup("java:global/com.tpjad.ejbjpa.groceries.server/LoginBean!com.tpjad.ejbjpa.groceries.interfaces.ILoginServiceR");
    }

    public static IGroceryServiceR lookupGroceryServiceJNDIGF() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        final Context context = new InitialContext(jndiProperties);
        return (IGroceryServiceR) context.lookup("java:global/com.tpjad.ejbjpa.groceries.server/GroceryBean!com.tpjad.ejbjpa.groceries.interfaces" +
                ".IGroceryServiceR");
    }
}
