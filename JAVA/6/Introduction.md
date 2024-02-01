## Release Date: `December 11, 2006`

## Key Features:

## 1. Scripting Language Support (JSR 223):

`Java 6` introduced a framework for embedding scripting languages into Java applications. This allowed for the use of languages like `JavaScript`, `Groovy`, and others within Java programs.

```java
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class ScriptingExample {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.eval("function f() { return 1; }; f() + 1;")); // Outputs 2
    }
}
```

## 2. Improvements in JVM Performance and Monitoring:

Enhanced performance, garbage collection, and diagnostic features.

Introduction of tools like `jconsole` and `visualvm` for better monitoring and analysis of JVM performance.

## 3. Enhancements in Java Database Connectivity (JDBC) 4.0:

JDBC 4.0 made it easier to work with database drivers and improved exception handling.

```java
try (Connection conn = DriverManager.getConnection(url, username, password)) {
    // Work with the database
} catch (SQLException e) {
    e.printStackTrace();
}

```

## 4. Java Compiler API (JSR 199):

This API allowed Java applications to compile Java source files programmatically.

```java
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
int result = compiler.run(null, null, null, "MyClass.java");

```

## 5. Swing and AWT Enhancements:

Introduction of the `SwingWorker` class for better handling of long-running tasks in Swing applications.
