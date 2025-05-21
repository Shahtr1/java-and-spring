`Throwable` is the top-level class
It has two main subclasses:

1.  `Exception` → for normal error handling
2.  `Error` → for serious system errors (JVM-level issues)

```scss
Object
 └── Throwable
      ├── Error (JVM/system errors)
      └── Exception (application-level errors)
           ├── RuntimeException (unchecked)
           └── Other Exceptions (checked)
```

| Type                           | Example                                            |
| ------------------------------ | -------------------------------------------------- |
| `Exception` (checked)          | `IOException`, `SQLException`                      |
| `RuntimeException` (unchecked) | `NullPointerException`, `IllegalArgumentException` |
| `Error`                        | `OutOfMemoryError`, `StackOverflowError`           |
