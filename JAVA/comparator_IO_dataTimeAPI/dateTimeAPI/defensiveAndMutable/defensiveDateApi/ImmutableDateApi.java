package comparator_IO_dataTimeAPI.dateTimeAPI.defensiveAndMutable.defensiveDateApi;

import java.util.Date;

public class ImmutableDateApi {
    private Date creationDate;

    public Date getCreationDate() {
        return new Date(creationDate.getTime());
    }

}
