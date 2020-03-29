package com.baconfire.authserver.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class TokenUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getFormattedDate(LocalDateTime date) {
        return DATE_TIME_FORMATTER.format(date);
    }
}
