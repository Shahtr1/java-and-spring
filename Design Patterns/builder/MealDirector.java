package builder;

public class MealDirector {
    private final MealBuilder mealBuilder;

    public MealDirector(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal prepareMeal() {
        mealBuilder.addBread();
        mealBuilder.addBiryani();
        mealBuilder.addCurry();
        mealBuilder.addColdDrink();
        return mealBuilder.build();
    }
}
