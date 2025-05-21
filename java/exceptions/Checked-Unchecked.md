# Checked vs Unchecked Exceptions

## 1. Checked Exceptions

- Inherit from `Exception` (but not from `RuntimeException`)
- Must be declared in method signatures using `throws`, or handled with `try-catch`
- Checked at compile time

Examples:

- `IOException`
- `SQLException`
- `FileNotFoundException`

## 2. Unchecked Exceptions

- Inherit from RuntimeException
- Do not require explicit handling
- Checked at runtime

```java
int x = 10 / 0;  // ArithmeticException (unchecked)
```

Examples:

- `NullPointerException`
- `ArrayIndexOutOfBoundsException`
- `IllegalArgumentException`
