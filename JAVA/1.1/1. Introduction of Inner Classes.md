## Release Date: `February 19, 1997`

## Key Features:

- ### Inner Classes:

  Inner classes are classes defined within another class, allowing for `closer grouping of related classes` and access to the outer class's members.

  ```java
  public class OuterClass {
      private int outerValue = 100;

      class InnerClass {
          void display() {
              System.out.println("Outer Value: " + outerValue);
          }
      }

      public void showInner() {
          InnerClass inner = new InnerClass();
          inner.display();
      }

      public static void main(String[] args) {
          OuterClass outer = new OuterClass();
          outer.showInner();
      }
  }

  // This code demonstrates an inner class accessing the outer class's private variable.
  ```

- ### JavaBeans:

  JavaBeans are `reusable software components` for Java that can be manipulated visually in a builder tool.

  ```java
  public class MyBean {
      private String name;

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }
  }

  // This is a simple bean with a property name and its getter and setter methods.

  ```

- ### JDBC (Java Database Connectivity):

  JDBC API provided a method for Java applications to interact with databases.

  ```java
    import java.sql.*;

    public class JdbcExample {
        public static void main(String[] args) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase", "yourUsername", "yourPassword");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM yourTable");
                while (rs.next()) {
                    System.out.println(rs.getString("columnName"));
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // The Class.forName() method is not always necessary with newer JDBC drivers, as they are auto-registered. However, including it makes your code more portable.
  ```

- ### Reflection (Introspection):

  Reflection allowed programs to analyze and modify their behavior at runtime.

  ```java
    import java.lang.reflect.Method;

    public class ReflectionExample {
        public static void main(String[] args) {
            try {
                Method method = ReflectionExample.class.getMethod("main", String[].class);
                System.out.println("Method name: " + method.getName());
                for (Class<?> paramType : method.getParameterTypes()) {
                    System.out.println("Parameter type: " + paramType.getName());
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /* output:
        method name: public static void Main.main(java.lang.String[])
        Parameter type: [Ljava.lang.String;
    */

    // This code uses reflection to get information about the main method.

  ```
