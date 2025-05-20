package comparator_IO_dataTimeAPI.dateTimeAPI.defensiveAndMutable.defensiveDateApi;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ImmutableDateApi mutableDateApi = new ImmutableDateApi();
        Date date = mutableDateApi.getCreationDate();
        date.setTime(0L); // should not be allowed
    }
}
