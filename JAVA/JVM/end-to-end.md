```java
public class App {
    static int version = 1;

    public static void main(String[] args) {
        User user = new User("Clay");
        user.sayHello();
    }
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + name);
    }
}

```

## STAGE 1: Compilation

```bash
javac App.java
```

- `javac` compiles source into `.class` files (`App.class`, `User.class`)
- These files contain bytecode, not machine code

At this point:

- No stack
- No heap
- No Metaspace used
- Just disk-level .class files

## STAGE 2: Program Execution Begins

```bash
java App
```

Now the JVM process starts, and the real magic begins.

## STAGE 3: Class Loading (Class Loader Subsystem)

- App.class is loaded by the Application ClassLoader
- User.class is loaded next
- Bytecode is verified
- Classes are prepared and initialized

| Memory Area               | What happens                                           |
| ------------------------- | ------------------------------------------------------ |
| **Metaspace**             | Stores class metadata for `App` and `User`             |
|                           | Includes: class name, fields, method info, static vars |
| **Method Area** (logical) | Stores `main()`, `sayHello()`, `User()` bytecode       |
| **Runtime Constant Pool** | Loads constants like `"Hello"`, `"Clay"`               |

## STAGE 4: Stack & Heap Setup

The JVM spawns the main thread, which creates:
| Memory Area | What happens |
| -------------------------------- | -------------------------------------------------------- |
| **Java Stack (for main thread)** | Stack frame for `main()` created |
| **Heap** | The `"Clay"` string and `User` object are allocated here |

### Breakdown of Execution in main():

1.  `User user = new User("Clay");`

    - `"Clay"` → heap (if not already interned)
    - `new User(...)` → object created in the heap
    - Reference to `user` → stored in local variable of `main()`’s stack frame

2.  `user.sayHello();`

    - New stack frame created for `sayHello()`
    - Accesses `this.name` → points to `"Clay"` in the heap
    - Calls `System.out.println(...)` → accesses shared `System.out` (static field in Metaspace)

## Execution Engine Kicks In

**Interpreter:**

- Begins interpreting bytecode instructions (`ILOAD`, `INVOKEVIRTUAL`, etc.)
- Uses the `Operand Stack` inside each stack frame to do computation

**JIT (Just-In-Time Compiler):**

- After many iterations (in bigger apps), methods like `sayHello()` may be marked hot
- JIT compiles them into native machine code for faster execution

## Program Counter Register

Each thread:

- Has a PC Register
- Keeps track of the next bytecode instruction to execute
- Updates after every instruction

## Native Method Stack (if needed)

- If a method like `System.out.println()` uses a native call (e.g., to `printf` in libc), it uses the **Native Method Stack**
- This area is separate from the Java Stack and lives in native memory

## Garbage Collection in the Heap

After execution:

- `user` object becomes unreachable
- `"Clay"` may be GC'd (if not interned)
- GC (like G1 or Parallel GC) runs and reclaims memory
- Static variables in Metaspace like `App.version` live until the class is unloaded.

```pgsql
[.java]          → javac → [.class]          → Class Loader
 (source code)             (bytecode)          ↓
                                               ↓
                                    ┌────────────────────────────┐
                                    │   ⛓ Metaspace              │
                                    │ - Class metadata           │
                                    │ - Static vars              │
                                    │ - Methods info/bytecode    │
                                    └────────────────────────────┘
                                               ↓
                                  ┌───────────────────────────────┐
                                  │   💾 Heap                     │
                                  │ - Objects ("Clay", user)      │
                                  └───────────────────────────────┘
                                               ↓
                                ┌────────────────────────────────────┐
                                │   🧮 Java Stack (per thread)        │
                                │ - Frames for main(), sayHello()    │
                                │ - Local vars, operand stack        │
                                └────────────────────────────────────┘
                                               ↓
                                ┌────────────────────────────────────┐
                                │   🔁 Execution Engine               │
                                │ - Interpreter                      │
                                │ - JIT Compiler (hot paths)         │
                                └────────────────────────────────────┘
                                               ↓
                                ┌────────────────────────────────────┐
                                │   🧭 PC Register                    │
                                │ - Next instruction to execute      │
                                └────────────────────────────────────┘
                                               ↓
                                ┌────────────────────────────────────┐
                                │   ⚙ Native Method Stack            │
                                │ - Used by System/native calls      │
                                └────────────────────────────────────┘

```
