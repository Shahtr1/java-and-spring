package comparator_IO_dataTimeAPI.dateTimeAPI.defensiveAndMutable.mutableDateApi;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        MutableDateApi mutableDateApi = new MutableDateApi();
        Date date = mutableDateApi.getCreationDate();
        date.setTime(0L); // should not be allowed
    }
}
