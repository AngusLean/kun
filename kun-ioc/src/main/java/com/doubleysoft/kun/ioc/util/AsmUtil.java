package com.doubleysoft.kun.ioc.util;

import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cupofish@gmail.com
 * 4/18/19 22:49
 */
public class AsmUtil {

    /**
     * Cached method names
     */
    private static final Map<Method, String[]>      METHOD_NAMES_POOL          = new ConcurrentHashMap<>(64);
    private static final Map<Constructor, String[]> CONSTRUCTOR_MAP_NAMES_POOL = new ConcurrentHashMap<>(64);

    public static String[] getMethodParamNames(final Method m) {
        if (METHOD_NAMES_POOL.containsKey(m)) {
            return METHOD_NAMES_POOL.get(m);
        }
        int            paramLength = m.getParameterTypes().length;
        final String[] paramNames  = new String[paramLength];

        if (paramLength > 0 && m.getParameters()[0].isNamePresent()) {
            for (int i = 0; i < paramLength; i++) {
                paramNames[i] = m.getParameters()[i].getName();
            }
            METHOD_NAMES_POOL.put(m, paramNames);
            return paramNames;
        }

        final String n = m.getDeclaringClass().getName();
        ClassReader  cr;
        try {
            cr = new ClassReader(n);
        } catch (IOException e) {
            return null;
        }
        asmGetParamNames(m, paramNames, cr);
        METHOD_NAMES_POOL.put(m, paramNames);
        return paramNames;
    }

    public static String[] getMethodParamNames(final Constructor m) {
        if (CONSTRUCTOR_MAP_NAMES_POOL.containsKey(m)) {
            return CONSTRUCTOR_MAP_NAMES_POOL.get(m);
        }
        int            paramLength = m.getParameterTypes().length;
        final String[] paramNames  = new String[paramLength];

        if (paramLength > 0 && m.getParameters()[0].isNamePresent()) {
            for (int i = 0; i < paramLength; i++) {
                paramNames[i] = m.getParameters()[i].getName();
            }
            CONSTRUCTOR_MAP_NAMES_POOL.put(m, paramNames);
            return paramNames;
        }

        final String n = m.getDeclaringClass().getName();
        ClassReader  cr;
        try {
            cr = new ClassReader(n);
        } catch (IOException e) {
            return null;
        }
        asmGetParamNames(m, paramNames, cr);
        CONSTRUCTOR_MAP_NAMES_POOL.put(m, paramNames);
        return paramNames;
    }

    private static void asmGetParamNames(Executable m, String[] paramNames, ClassReader cr) {
        cr.accept(new ClassVisitor(Opcodes.ASM5) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
                final Type[] args = Type.getArgumentTypes(desc);
                // The method name is the same and the number of parameters is the same
                if (!name.equals(m.getName()) || !sameType(args, m.getParameterTypes())) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }
                MethodVisitor v = super.visitMethod(access, name, desc, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM5, v) {
                    @Override
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                        int i = index - 1;
                        // if it is a static method, the first is the parameter
                        // if it's not a static method, the first one is "this" and then the parameter of the method
                        if (Modifier.isStatic(m.getModifiers())) {
                            i = index;
                        }
                        if (i >= 0 && i < paramNames.length) {
                            paramNames[i] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start, end, index);
                    }
                };
            }
        }, 0);
    }


    private static boolean sameType(Type[] types, Class<?>[] classes) {
        if (types.length != classes.length) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(classes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }
}
