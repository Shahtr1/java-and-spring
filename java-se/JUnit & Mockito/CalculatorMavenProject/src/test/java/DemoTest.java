import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
public class DemoTest {

    @Test
    void testDemo(){
        System.out.println("Demo test");
    }
}
