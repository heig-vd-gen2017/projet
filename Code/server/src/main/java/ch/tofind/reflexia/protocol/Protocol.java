package ch.tofind.reflexia.protocol;

/**
 * Created by luca on 26.04.17.
 */
public interface Protocol {
    public static final String CMD_JOIN          = "JOIN";
    public static final String CMD_USERNAME_USED = "USERNAME_USED";
    public static final String CMD_FULL_GAME     = "FULL_GAME";
    public static final String CMD_JOINED        = "JOINED";
    public static final String CMD_UPDATE        = "UPDATE";
    public static final String CMD_UPDATED       = "UPDATED";
    public static final String CMD_BEGIN_GAME    = "BEGIN_GAME";
    public static final String CMD_SEND          = "SEND";
    public static final String CMD_END_OF_GAME   = "END_OF_GAME";

    public static final int    DEFAULT_PORT    = 7777;
    public static final String DEFAULT_CHARSET = "UTF-8";
}
