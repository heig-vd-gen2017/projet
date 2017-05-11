package ch.tofind.reflexia.core;

public final class ApplicationProtocol {

    //! SUCCESS\n[DATA]\nEND_OF_COMMAND
    public static final String SUCCESS = "SUCCESS";

    //! ERROR\n[DATA]\nEND_OF_COMMAND
    public static final String ERROR = "ERROR";


    public static final String TRACK_REQUEST = "TRACK_REQUEST";
    public static final String TRACK_ACCEPTED = "TRACK_ACCEPTED";
    public static final String TRACK_REFUSED = "TRACK_REFUSED";
    public static final String SEND_TRACK = "SEND_TRACK";
    public static final String TRACK_SAVED = "TRACK_SAVED";

    public static final String PLAYLIST_UPDATE = "PLAYLIST_UPDATE";

}
