## Release Date: `December 8, 1998`

`Java 1.2, also known as Java 2`, represented a significant step forward in Java's evolution

- ### Key Features:

  1.  ### Swing GUI Toolkit:

      Swing provided a new set of GUI components, offering more sophisticated graphical user interface elements than AWT.

      ```java
      import javax.swing.*;

      public class SimpleSwingApp {
          public static void main(String[] args) {
              JFrame frame = new JFrame("Simple Swing Application");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setSize(300, 200);
              JLabel label = new JLabel("Hello, Swing!");
              frame.getContentPane().add(label);
              frame.setVisible(true);
          }
      }

      // This code creates a basic Swing window with a label.
      ```

  2.  ### Java Collections Framework (JCF):

      - ### Interfaces

        - **Collection**: The root interface of the framework.

        - **List**: An ordered collection (also known as a sequence).

        - **Set**: A collection that cannot contain duplicate elements.

        - **Map**: An object that maps keys to values, cannot contain duplicate keys.

      - ### Implementations

        - **ArrayList**: A resizable-array implementation of the List interface.

        - **LinkedList**: A doubly-linked list implementation of the List and Deque interfaces.

        - **Vector**: A legacy collection similar to ArrayList, but synchronized.

        - **Stack**: A legacy collection representing a last-in-first-out (LIFO) stack of objects, extending Vector.

        - **HashSet**: A collection that uses a hash table for storage, implementing the Set interface.

        - **LinkedHashSet**: An ordered version of HashSet that maintains a doubly-linked list across all elements.

        - **TreeSet**: A NavigableSet implementation based on a TreeMap.

        - **HashMap**: A hash table based implementation of the Map interface.

        - **LinkedHashMap**: A hash table and linked list implementation of the Map interface, with predictable iteration order.

        - **TreeMap**: A Red-Black tree based NavigableMap implementation.

        - **Hashtable**: A legacy collection similar to HashMap, but synchronized.

        ### `Vector and Stack`:

        `These are part of the original Java 1.0 release and were retrofitted to integrate with the Collections Framework in Java 1.2.`

  3.  ### Java Plug-in:

      The Java Plug-in was introduced for web browsers, allowing applets to run using the latest JVM.

  4.  ### JIT (Just-In-Time) Compiler:

      Improved the performance of Java applications by `compiling bytecode into native machine code at runtime`.

  5.  ### `strictfp`:

      It is used to restrict the precision and rounding of floating point calculations to ensure portability.

      When strictfp is applied to a class, interface, or method, all floating-point calculations within that scope strictly adhere to the `IEEE 754 standards for floating-point arithmetic`.

      The primary purpose of `strictfp` is to make floating-point calculations `consistent` across `different platforms`. `Before` Java 1.2, floating-point calculations could `vary` slightly from one platform to another due to differences in hardware and operating system architectures.

      - How `strictfp` is Used:
        `strictfp` can be applied to classes, interfaces, or methods.

        ```java
            public strictfp class MyStrictfpClass {
                // All floating-point operations in this class are strict according to IEEE 754
            }

            public class MyClass {
                public strictfp void myStrictfpMethod() {
                    // Floating-point operations in this method are strict
                }
            }
        ```

      `strictfp` is still a part of Java and can be used in modern Java applications.
      However, its usage has become less common for several reasons:

      1.  `Standardization` of Floating-Point Hardware: Modern hardware and `JVM implementations tend to follow IEEE 754 standards` more consistently, making the discrepancies in floating-point calculations less of an issue.

      2.  Some newer JVM implementations `already conform` to the IEEE 754 standard by `default`, reducing the need for explicitly using strictfp.
