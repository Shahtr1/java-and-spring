package decorator;

public class BasePizza implements Pizza {
    @Override
    public String bake() {
        return "BasePizza";
    }
}
