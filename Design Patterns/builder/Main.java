package builder;

public class Main {
    public static void main(String[] args) {
        Burger burger = new Burger.BurgerBuilder()
                .mayonnaise(true)
                .onion(true)
                .egg(true)
                .extraCheese(true)
                .size("LARGE")
                .build();

        Meal meal = new MealDirector(new VegMealBuilder()).prepareMeal();

    }
}
