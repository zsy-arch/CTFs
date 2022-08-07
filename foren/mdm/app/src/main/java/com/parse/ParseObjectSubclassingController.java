package com.parse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseObjectSubclassingController {
    private final Object mutex = new Object();
    private final Map<String, Constructor<? extends ParseObject>> registeredSubclasses = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getClassName(Class<? extends ParseObject> clazz) {
        ParseClassName info = (ParseClassName) clazz.getAnnotation(ParseClassName.class);
        if (info != null) {
            return info.value();
        }
        throw new IllegalArgumentException("No ParseClassName annotation provided on " + clazz);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSubclassValid(String className, Class<? extends ParseObject> clazz) {
        Constructor<? extends ParseObject> constructor;
        synchronized (this.mutex) {
            constructor = this.registeredSubclasses.get(className);
        }
        return constructor == null ? clazz == ParseObject.class : constructor.getDeclaringClass() == clazz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerSubclass(Class<? extends ParseObject> clazz) {
        if (!ParseObject.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Cannot register a type that is not a subclass of ParseObject");
        }
        String className = getClassName(clazz);
        synchronized (this.mutex) {
            Constructor<? extends ParseObject> previousConstructor = this.registeredSubclasses.get(className);
            if (previousConstructor != null) {
                Class<? extends ParseObject> previousClass = previousConstructor.getDeclaringClass();
                if (!clazz.isAssignableFrom(previousClass)) {
                    if (!previousClass.isAssignableFrom(clazz)) {
                        throw new IllegalArgumentException("Tried to register both " + previousClass.getName() + " and " + clazz.getName() + " as the ParseObject subclass of " + className + ". Cannot determine the right class to use because neither inherits from the other.");
                    }
                } else {
                    return;
                }
            }
            try {
                this.registeredSubclasses.put(className, getConstructor(clazz));
                if (previousConstructor == null) {
                    return;
                }
                if (className.equals(getClassName(ParseUser.class))) {
                    ParseUser.getCurrentUserController().clearFromMemory();
                } else if (className.equals(getClassName(ParseInstallation.class))) {
                    ParseInstallation.getCurrentInstallationController().clearFromMemory();
                }
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Default constructor for " + clazz + " is not accessible.");
            } catch (NoSuchMethodException e2) {
                throw new IllegalArgumentException("Cannot register a type that does not implement the default constructor!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unregisterSubclass(Class<? extends ParseObject> clazz) {
        String className = getClassName(clazz);
        synchronized (this.mutex) {
            this.registeredSubclasses.remove(className);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseObject newInstance(String className) {
        Constructor<? extends ParseObject> constructor;
        synchronized (this.mutex) {
            constructor = this.registeredSubclasses.get(className);
        }
        try {
            return constructor != null ? (ParseObject) constructor.newInstance(new Object[0]) : new ParseObject(className);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Failed to create instance of subclass.", e2);
        }
    }

    private static Constructor<? extends ParseObject> getConstructor(Class<? extends ParseObject> clazz) throws NoSuchMethodException, IllegalAccessException {
        Constructor<? extends ParseObject> constructor = clazz.getDeclaredConstructor(new Class[0]);
        if (constructor == null) {
            throw new NoSuchMethodException();
        }
        int modifiers = constructor.getModifiers();
        if (Modifier.isPublic(modifiers) || (clazz.getPackage().getName().equals("com.parse") && !Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers))) {
            return constructor;
        }
        throw new IllegalAccessException();
    }
}
