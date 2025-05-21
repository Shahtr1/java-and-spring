## Step 1: Extend Exception or RuntimeException

Checked Custom Exception:

```java
public class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}
```

Unchecked Custom Exception:

```java
public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}

```

## Step 2: Throw It in Code

```java
if (data == null) {
    throw new MyCheckedException("Data must not be null!");
}
```
