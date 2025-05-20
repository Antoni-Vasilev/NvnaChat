package bg.nvna.nvnachat;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class Extensions {

    public static Date convertCurrentDateToUTC(Date currentDate) {
        Instant instant = currentDate.toInstant();
        ZonedDateTime utcDateTime = instant.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        return Date.from(utcDateTime.toInstant());
    }
}
