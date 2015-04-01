package ru.ifmo.ctddev.muratov.implementor;

import info.kgeorgiy.java.advanced.implementor.ImplerException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * @author Amir Muratov
 */

public class Implementor implements info.kgeorgiy.java.advanced.implementor.JarImpler, info.kgeorgiy.java.advanced.implementor.Impler {

    /**
     * Names of variables in method/constructor parameters.
     */
    public static final String variableName = "var";

    /**
     * {@link java.io.OutputStreamWriter} which is used in {@link #print} function.
     */
    private OutputStreamWriter osw;

    /**
     * Writes <tt>message</tt> to {@link #osw}.
     * @param message {@link java.lang.String} to write
     * @throws IOException when can't write to {@link #osw}
     */
    private void print(String message) throws IOException {
        osw.write(message);
    }

    /**
     * Writes <tt>message</tt> to {@link java.lang.System#err}.
     * @param message {@link java.lang.String} to write
     * @see java.lang.System#err
     */

    private static void printErr(String message) {
        System.err.print(message + "\n");
    }

    /**
     * Returns component name + "[]" if <tt>c</tt> is array, or class name otherwise.
     * @param c class to get name
     * @return {@link java.lang.String} which contains name of the class
     */

    private String getTypeName(Class c) {
        String result = "";
        if (c.isArray()) {
            result = "[]";
            c = c.getComponentType();
        }
        result = c.getName() + result;
        return result;
    }


    /**
     * Makes implementation of given {@link java.lang.reflect.Method}. Implementation returns default value.
     * Parameters are called according {@link #variableName}. Uses {@link java.lang.StringBuilder} to make string.
     * @param method method to implement
     * @return string, which contains implementation of <tt>method</tt>
     * @see java.lang.reflect.Method
     * @see java.lang.StringBuilder
     */

    private String printMethod(Method method) {
        StringBuilder sb = new StringBuilder();
        int m = method.getModifiers() & ~Modifier.ABSTRACT & Modifier.methodModifiers();
        sb.append("\t").append(Modifier.toString(m)).append(" ").append(getTypeName(method.getReturnType()));
        sb.append(" ").append(method.getName()).append("(");
        Class[] parameters = method.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(getTypeName(parameters[i])).append(" ").append(variableName).append(i + 1);
            if (i != parameters.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        Class[] exceptions = method.getExceptionTypes();
        if (exceptions.length != 0) {
            sb.append(" throws ");
            for (int i = 0; i < exceptions.length; i++) {
                sb.append(exceptions[i].getName());
                if (i != exceptions.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(" {");
        if (!method.getReturnType().equals(void.class)) {
            sb.append("\n\t\treturn");
            if (!method.getReturnType().isPrimitive()) {
                sb.append(" null;");
            } else {
                if (method.getReturnType().isAssignableFrom(Boolean.TYPE)) {
                    sb.append(" false;");
                } else {
                    sb.append(" 0;");
                }
            }
        }
        sb.append("\n\t}\n\n");
        return sb.toString();
    }

    /**
     * Makes implementation of given {@link java.lang.reflect.Constructor}. Implementation calls super from given parameters.
     * Parameters are called according {@link #variableName}. Uses {@link java.lang.StringBuilder} to make string.
     * @param constructor constructor to write
     * @param className name of the class for constructor
     * @return string, which contains implementation of <tt>constructor</tt>
     * @see java.lang.reflect.Constructor
     * @see java.lang.StringBuilder
     */

    private String printConstructor(Constructor constructor, String className) {
        StringBuilder sb = new StringBuilder();
        int m = constructor.getModifiers() & Modifier.constructorModifiers();
        sb.append("\t").append(Modifier.toString(m)).append(" ").append(className).append("(");
        Class[] parameters = constructor.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(getTypeName(parameters[i])).append(" ").append(variableName).append(i + 1);
            if (i != parameters.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        Class[] exceptions = constructor.getExceptionTypes();
        if (exceptions.length != 0) {
            sb.append(" throws ");
            for (int i = 0; i < exceptions.length; i++) {
                sb.append(exceptions[i].getName());
                if (i != exceptions.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(" {\n\t\tsuper(");
        for (int i = 0; i < parameters.length; i++) {
            sb.append(variableName).append(i + 1);
            if (i != parameters.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(");\n\t}\n\n");
        return sb.toString();
    }


    /**
     * Collects all methods that have {@link java.lang.Class} to the <tt>list</tt>.
     * @param c {@link java.lang.Class} to find methods
     * @param list {@link java.util.List} to collect methods.
     * @see java.util.List
     */

    private void dfsMethods(Class c, List<Method> list) {
        if (c == null) {
            return;
        }
        list.addAll(Arrays.asList(c.getDeclaredMethods()));
        dfsMethods(c.getSuperclass(), list);
        for (Class i : c.getInterfaces()) {
            dfsMethods(i, list);
        }
    }

    /**
     * Returns {@link java.lang.String} associated with given method.
     * @param m {@link java.lang.reflect.Method} to be associated.
     * @return {@link java.lang.String} associated with <tt>m</tt>.
     * @see java.lang.reflect.Method
     */

    private String methodToString(Method m) {
        String result = m.getName();
        for (Type t : m.getParameterTypes()) {
            result += "#" + t.getTypeName();
        }
        return result;
    }

    /**
     * Checks if given {@link java.lang.Class} has public or protected or default constructors.
     * @param token {@link java.lang.Class} to check
     * @return true if given {@link java.lang.Class} has public or protected or default {@link java.lang.reflect.Constructor}, false otherwise.
     */

    private boolean hasPubOrProtOrDefConstructors(Class<?> token) {
        boolean result = false;
        for (Constructor c : token.getDeclaredConstructors()) {
            if (!Modifier.isPrivate(c.getModifiers())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Creates a class that implements or extends given class/interface.
     * Output file is created in the folder that corresponds to the package of
     * given class/interface. Output file contains java class, that implements
     * or extends given class or interface and compiles without errors.
     * @param token class or interface will be implemented
     * @param root directory to create class implementation
     * @throws ImplerException if class can't be implemented or error while writing occurred
     * @see info.kgeorgiy.java.advanced.implementor.ImplerException
     */

    @Override
    public void implement(Class token, File root) throws ImplerException {
        if (Modifier.isFinal(token.getModifiers()) || (!token.isInterface() && !hasPubOrProtOrDefConstructors(token))) {
            throw new ImplerException();
        }
        String rootPath;
        if (root == null) {
            rootPath = System.getProperty("user.dir");
        } else {
            rootPath = root.getPath();
        }
        String path = rootPath + File.separator + token.getPackage().getName().replace('.', File.separatorChar);
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
            for (Constructor constructor : token.getDeclaredConstructors()) {
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
            throw new ImplerException();
        }
    }

    /**
     * Creates class that implements given class/interface.
     * If the first argument is "-jar", it puts implemented class into jar-file.
     * Otherwise create .java file in the given directory.
     * @param args arguments from the command line
     * @see #implement
     * @see #implementJar
     */

    public static void main(String[] args) {
        if (args == null || args.length == 0 || args[0] == null || (args.length != 1 && !(args.length == 3 && args[1] != null && args[2] != null))) {
            printErr("Usage: Implementor <Class/Interface name> or Implementor -jar <Class/Interface name> <Output file>");
            return;
        }
        boolean jar = false;
        String className;
        String outputFile = "";
        if (args[0].equals("-jar")) {
            jar = true;
            className = args[1];
            outputFile = args[2];
        } else {
            className = args[0];
        }
        try {
            Class c = Class.forName(className);
            if (jar) {
                new Implementor().implementJar(c, new File(outputFile));
            } else {
                new Implementor().implement(c, null);
            }
        } catch (ClassNotFoundException e) {
            printErr("Class not found");
        } catch (ImplerException ignored) {
            printErr("Can't implement class");
        }
    }


    /**
     * Creates jar-file which contains class that implements given class/interface.
     * Creates directory and generates implementation of the class {@code c} using {@link #implement}.
     * Then compile this class and create jar-file.
     * @param token  class to implement
     * @param jarFile file to print to
     * @throws ImplerException if class can't be implemented or error while writing occurred
     * @see info.kgeorgiy.java.advanced.implementor.ImplerException
     * @see #implement
     */

    @Override
    public void implementJar(Class<?> token, File jarFile) throws ImplerException {
        implement(token, null);
        String file = token.getPackage().getName().replace('.', File.separatorChar) + File.separator + token.getSimpleName() + "Impl";
        String javaFile = file + ".java";
        String classFile = file + ".class";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        List<String> args = new ArrayList<>();
        args.add(javaFile);
        args.add("-cp");
        args.add(System.getProperty("java.class.path"));
        final int exitCode = compiler.run(null, null, null, args.toArray(new String[args.size()]));
        if (exitCode != 0) {
            printErr("Compilation error, returned value: " + exitCode);
            throw new ImplerException();
        }

        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        try (FileOutputStream fos = new FileOutputStream(jarFile);
             FileInputStream fis = new FileInputStream(classFile);
             JarOutputStream jarOutputStream = new JarOutputStream(fos, manifest)) {
            JarEntry entry = new JarEntry(classFile);
            entry.setTime(jarFile.lastModified());
            jarOutputStream.putNextEntry(new JarEntry(entry));
            byte[] buffer = new byte[8192];
            while (true) {
                int len = fis.read(buffer);
                if (len == -1) {
                    break;
                }
                jarOutputStream.write(buffer, 0, len);
            }
            jarOutputStream.closeEntry();
        } catch (IOException e) {
            throw new ImplerException();
        }
    }
}