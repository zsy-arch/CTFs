package org.slf4j;

import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

/* loaded from: classes2.dex */
public final class LoggerFactory {
    static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
    static final int FAILED_INITIALIZATION = 2;
    static final String JAVA_VENDOR_PROPERTY = "java.vendor.url";
    static final String LOGGER_NAME_MISMATCH_URL = "http://www.slf4j.org/codes.html#loggerNameMismatch";
    static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
    static final int NOP_FALLBACK_INITIALIZATION = 4;
    static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
    static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
    static final int ONGOING_INITIALIZATION = 1;
    static final String REPLAY_URL = "http://www.slf4j.org/codes.html#replay";
    static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
    static final int SUCCESSFUL_INITIALIZATION = 3;
    static final int UNINITIALIZED = 0;
    static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
    static volatile int INITIALIZATION_STATE = 0;
    static SubstituteLoggerFactory SUBST_FACTORY = new SubstituteLoggerFactory();
    static NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
    static final String DETECT_LOGGER_NAME_MISMATCH_PROPERTY = "slf4j.detectLoggerNameMismatch";
    static boolean DETECT_LOGGER_NAME_MISMATCH = Util.safeGetBooleanSystemProperty(DETECT_LOGGER_NAME_MISMATCH_PROPERTY);
    private static final String[] API_COMPATIBILITY_LIST = {"1.6", "1.7"};
    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";

    private LoggerFactory() {
    }

    static void reset() {
        INITIALIZATION_STATE = 0;
    }

    private static final void performInitialization() {
        bind();
        if (INITIALIZATION_STATE == 3) {
            versionSanityCheck();
        }
    }

    private static boolean messageContainsOrgSlf4jImplStaticLoggerBinder(String msg) {
        if (msg == null) {
            return false;
        }
        return msg.contains("org/slf4j/impl/StaticLoggerBinder") || msg.contains("org.slf4j.impl.StaticLoggerBinder");
    }

    private static final void bind() {
        Set<URL> staticLoggerBinderPathSet = null;
        try {
            if (!isAndroid()) {
                staticLoggerBinderPathSet = findPossibleStaticLoggerBinderPathSet();
                reportMultipleBindingAmbiguity(staticLoggerBinderPathSet);
            }
            StaticLoggerBinder.getSingleton();
            INITIALIZATION_STATE = 3;
            reportActualBinding(staticLoggerBinderPathSet);
            fixSubstituteLoggers();
            replayEvents();
            SUBST_FACTORY.clear();
        } catch (Exception e) {
            failedBinding(e);
            throw new IllegalStateException("Unexpected initialization failure", e);
        } catch (NoClassDefFoundError ncde) {
            if (messageContainsOrgSlf4jImplStaticLoggerBinder(ncde.getMessage())) {
                INITIALIZATION_STATE = 4;
                Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                Util.report("Defaulting to no-operation (NOP) logger implementation");
                Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                return;
            }
            failedBinding(ncde);
            throw ncde;
        } catch (NoSuchMethodError nsme) {
            String msg = nsme.getMessage();
            if (msg != null && msg.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                INITIALIZATION_STATE = 2;
                Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                Util.report("Your binding is version 1.5.5 or earlier.");
                Util.report("Upgrade your binding to version 1.6.x.");
            }
            throw nsme;
        }
    }

    private static void fixSubstituteLoggers() {
        synchronized (SUBST_FACTORY) {
            SUBST_FACTORY.postInitialization();
            for (SubstituteLogger substLogger : SUBST_FACTORY.getLoggers()) {
                substLogger.setDelegate(getLogger(substLogger.getName()));
            }
        }
    }

    static void failedBinding(Throwable t) {
        INITIALIZATION_STATE = 2;
        Util.report("Failed to instantiate SLF4J LoggerFactory", t);
    }

    private static void replayEvents() {
        LinkedBlockingQueue<SubstituteLoggingEvent> queue = SUBST_FACTORY.getEventQueue();
        int queueSize = queue.size();
        int count = 0;
        List<SubstituteLoggingEvent> eventList = new ArrayList<>(128);
        while (queue.drainTo(eventList, 128) != 0) {
            for (SubstituteLoggingEvent event : eventList) {
                replaySingleEvent(event);
                int count2 = count + 1;
                if (count == 0) {
                    emitReplayOrSubstituionWarning(event, queueSize);
                }
                count = count2;
            }
            eventList.clear();
        }
    }

    private static void emitReplayOrSubstituionWarning(SubstituteLoggingEvent event, int queueSize) {
        if (event.getLogger().isDelegateEventAware()) {
            emitReplayWarning(queueSize);
        } else if (!event.getLogger().isDelegateNOP()) {
            emitSubstitutionWarning();
        }
    }

    private static void replaySingleEvent(SubstituteLoggingEvent event) {
        if (event != null) {
            SubstituteLogger substLogger = event.getLogger();
            String loggerName = substLogger.getName();
            if (substLogger.isDelegateNull()) {
                throw new IllegalStateException("Delegate logger cannot be null at this state.");
            } else if (substLogger.isDelegateNOP()) {
            } else {
                if (substLogger.isDelegateEventAware()) {
                    substLogger.log(event);
                } else {
                    Util.report(loggerName);
                }
            }
        }
    }

    private static void emitSubstitutionWarning() {
        Util.report("The following set of substitute loggers may have been accessed");
        Util.report("during the initialization phase. Logging calls during this");
        Util.report("phase were not honored. However, subsequent logging calls to these");
        Util.report("loggers will work as normally expected.");
        Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void emitReplayWarning(int eventCount) {
        Util.report("A number (" + eventCount + ") of logging calls during the initialization phase have been intercepted and are");
        Util.report("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        Util.report("See also http://www.slf4j.org/codes.html#replay");
    }

    private static final void versionSanityCheck() {
        try {
            String requested = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean match = false;
            for (String aAPI_COMPATIBILITY_LIST : API_COMPATIBILITY_LIST) {
                if (requested.startsWith(aAPI_COMPATIBILITY_LIST)) {
                    match = true;
                }
            }
            if (!match) {
                Util.report("The requested version " + requested + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
                Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError e) {
        } catch (Throwable e2) {
            Util.report("Unexpected problem occured during version sanity check", e2);
        }
    }

    static Set<URL> findPossibleStaticLoggerBinderPathSet() {
        Enumeration<URL> paths;
        Set<URL> staticLoggerBinderPathSet = new LinkedHashSet<>();
        try {
            ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
            if (loggerFactoryClassLoader == null) {
                paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
            } else {
                paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
            }
            while (paths.hasMoreElements()) {
                staticLoggerBinderPathSet.add(paths.nextElement());
            }
        } catch (IOException ioe) {
            Util.report("Error getting resources from path", ioe);
        }
        return staticLoggerBinderPathSet;
    }

    private static boolean isAmbiguousStaticLoggerBinderPathSet(Set<URL> binderPathSet) {
        return binderPathSet.size() > 1;
    }

    private static void reportMultipleBindingAmbiguity(Set<URL> binderPathSet) {
        if (isAmbiguousStaticLoggerBinderPathSet(binderPathSet)) {
            Util.report("Class path contains multiple SLF4J bindings.");
            Iterator i$ = binderPathSet.iterator();
            while (i$.hasNext()) {
                Util.report("Found binding in [" + i$.next() + "]");
            }
            Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    private static boolean isAndroid() {
        String vendor = Util.safeGetSystemProperty(JAVA_VENDOR_PROPERTY);
        if (vendor == null) {
            return false;
        }
        return vendor.toLowerCase().contains(f.a);
    }

    private static void reportActualBinding(Set<URL> binderPathSet) {
        if (binderPathSet != null && isAmbiguousStaticLoggerBinderPathSet(binderPathSet)) {
            Util.report("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
        }
    }

    public static Logger getLogger(String name) {
        return getILoggerFactory().getLogger(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        Class<?> autoComputedCallingClass;
        Logger logger = getLogger(clazz.getName());
        if (DETECT_LOGGER_NAME_MISMATCH && (autoComputedCallingClass = Util.getCallingClass()) != null && nonMatchingClasses(clazz, autoComputedCallingClass)) {
            Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", logger.getName(), autoComputedCallingClass.getName()));
            Util.report("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        }
        return logger;
    }

    private static boolean nonMatchingClasses(Class<?> clazz, Class<?> autoComputedCallingClass) {
        return !autoComputedCallingClass.isAssignableFrom(clazz);
    }

    public static ILoggerFactory getILoggerFactory() {
        if (INITIALIZATION_STATE == 0) {
            synchronized (LoggerFactory.class) {
                if (INITIALIZATION_STATE == 0) {
                    INITIALIZATION_STATE = 1;
                    performInitialization();
                }
            }
        }
        switch (INITIALIZATION_STATE) {
            case 1:
                return SUBST_FACTORY;
            case 2:
                throw new IllegalStateException(UNSUCCESSFUL_INIT_MSG);
            case 3:
                return StaticLoggerBinder.getSingleton().getLoggerFactory();
            case 4:
                return NOP_FALLBACK_FACTORY;
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }
}
