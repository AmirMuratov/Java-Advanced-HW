package ru.ifmo.ctddev.muratov.implementor;

import info.kgeorgiy.java.advanced.implementor.ImplerException;


import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Implementor implements info.kgeorgiy.java.advanced.implementor.Impler {

    public static final String variableName = "var";
    private OutputStreamWriter osw;

    private void print(String message) throws IOException {
        osw.write(message);
    }

    private void printErr(String message) {
        System.err.print(message + "\n");
    }

    private String getTypeName(Class c) {
        String result = "";
        if (c.isArray()) {
            result = "[]";
            c = c.getComponentType();
        }
        result = c.getName() + result;
        return result;
    }

    private String printMethod(Method method) {
        String result = "";
        int m = method.getModifiers() & ~Modifier.ABSTRACT & Modifier.methodModifiers();
        result += "\t" + Modifier.toString(m) + " " + getTypeName(method.getReturnType());
        result += " " + method.getName() + "(";
        Class[] parameters = method.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            result += getTypeName(parameters[i]) + " " + variableName + (i + 1);
            if (i != parameters.length - 1) {
                result += ", ";
            }
        }
        result += ")";
        Class[] exceptions = method.getExceptionTypes();
        if (exceptions.length != 0) {
            result += " throws ";
            for (int i = 0; i < exceptions.length; i++) {
                result += exceptions[i].getName();
                if (i != exceptions.length - 1) {
                    result += ", ";
                }
            }
        }
        result += " {";
        if (!method.getReturnType().equals(void.class)) {
            result += "\n\t\treturn";
            if (!method.getReturnType().isPrimitive()) {
                result += " null;";
            } else {
                if (method.getReturnType().isAssignableFrom(Boolean.TYPE)) {
                    result += " false;";
                } else {
                    result += " 0;";
                }
            }
        }
        result += "\n\t}\n\n";
        return result;
    }


    private String printConstructor(Constructor constructor, String className) {
        String result = "";
        int m = constructor.getModifiers() & Modifier.constructorModifiers();
        result += "\t" + Modifier.toString(m) + " " + className + "(";
        Class[] parameters = constructor.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            result += getTypeName(parameters[i]) + " " + variableName + (i + 1);
            if (i != parameters.length - 1) {
                result += ", ";
            }
        }
        result += ")";
        Class[] exceptions = constructor.getExceptionTypes();
        if (exceptions.length != 0) {
            result += " throws ";
            for (int i = 0; i < exceptions.length; i++) {
                result += exceptions[i].getName();
                if (i != exceptions.length - 1) {
                    result += ", ";
                }
            }
        }
        result += " {\n\t\tsuper(";
        for (int i = 0; i < parameters.length; i++) {
            result += variableName + (i + 1);
            if (i != parameters.length - 1) {
                result += ", ";
            }
        }
        result += ");\n\t}\n\n";
        return result;
    }

    private void dfsMethods(Class c, List list) {
        if (c == null) {
            return;
        }
        list.addAll(Arrays.asList(c.getDeclaredMethods()));
        dfsMethods(c.getSuperclass(), list);
        for (Class i : c.getInterfaces()) {
            dfsMethods(i, list);
        }
    }

    private String methodToString(Method m) {
        String result = m.getName();
        for (Type t : m.getParameterTypes()) {
            result += "$" + t.getTypeName();
        }
        return result;
    }


    private boolean hasPublicOrProtectedConstructors(Class token) {
        boolean result = false;
        for (Constructor c : token.getDeclaredConstructors()) {
            if (!Modifier.isPrivate(c.getModifiers())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void implement(Class<?> token, File root) throws ImplerException {
        if (Modifier.isFinal(token.getModifiers()) || (!token.isInterface() && !hasPublicOrProtectedConstructors(token))) {
            throw  new ImplerException();
        }
        String rootPath = "";
        if (root == null) {
            rootPath = System.getProperty("user.dir");
        } else {
            rootPath = root.getPath();
        }
        String path = rootPath  + File.separator + token.getPackage().getName().replace('.', File.separatorChar);
        File rootFile = new File(path);
        if (!rootFile.mkdirs() && !rootFile.exists()) {
            throw new ImplerException();
        }
        try (
                FileOutputStream outputStream = new FileOutputStream(path + File.separator + token.getSimpleName() + "Impl.java");
                OutputStreamWriter writer = new OutputStreamWriter(outputStream)
        ) {
            osw = writer;
            String className = token.getSimpleName() + "Impl";
            print("package " + token.getPackage().getName() + ";\n\npublic class " + className);
            if (token.isInterface()) {
                print(" implements ");
            } else {
                print(" extends ");
            }
            print(token.getName() + " {\n\n\n");
            Constructor[] cons = token.getDeclaredConstructors();
            for (Constructor constructor : cons) {
                if (!Modifier.isPrivate(constructor.getModifiers())) {
                    print(printConstructor(constructor, className));
                }
            }

            List<Method> methods = new ArrayList<>();
            dfsMethods(token, methods);
            Map<String, Boolean> map = new HashMap<>();
            for (Method method : methods) {
                boolean b = Modifier.isAbstract(method.getModifiers());
                String s = methodToString(method);
                if (map.containsKey(s)) {
                    b &= map.get(s);
                }
                map.put(s, b);
            }
            for (Method method : methods) {
                if (map.get(methodToString(method))) {
                    print(printMethod(method));
                    map.put(methodToString(method), false);
                }
            }
            print("}");
        } catch (IOException e) {
            throw  new ImplerException();
        }
    }

    public void run(String[] args) {
        args = new String[]{"java.util.NavigableSet"};
        if (args == null || args.length != 1 || args[0] == null) {
            printErr("Usage: Implementor <Class/Interface name>");
            return;
        }
        Class c = null;
        try {
            c = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            printErr("Class not found");
        }
        try {
            implement(c, null);
        } catch (ImplerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Implementor().run(args);
    }

}
