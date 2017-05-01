package ch.tofind.reflexia.game;

import ch.tofind.reflexia.database.DatabaseObject;
import ch.tofind.reflexia.utils.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @brief This class represents a game mode and can be stored in a database
 */
public class Mode implements DatabaseObject {

    //! ID of the game mode in the database
    private Integer id;

    //! Path to the XML mode file
    private String uri;

    //! When was the track added for the first time in the database
    private Date dateAdded;

    //! When was the track played for the last time
    private Date datePlayed;

    //! Version control for concurrency
    private Integer version;

    /**
     * @brief Empty constructor for Hibernate
     */
    protected Mode() {

    }

    /**
     * @brief Create a track
     * @param title Title of the track
     * @param artist Artist of the track
     * @param album Album of the track
     * @param length Length (in seconds) of the track
     * @param uri URI of the file
     */
    public Mode(String title, String artist, String album, Integer length, String uri) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.uri = uri;
        this.dateAdded = new Date();
    }

    /**
     * @brief Get the track's title
     * @return The track's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @brief Get the track's artist
     * @return The track's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @brief Get the track's album
     * @return The track's album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @brief Get the track's length
     * @return The track's length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @brief Get the track's URI
     * @return The track's URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * @brief Get the date when the track was added
     * @return The added date
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @brief Get the date when the track was played
     * @return The played date
     */
    public Date getDatePlayed() {
        return datePlayed;
    }

    @Override
    public void update() {
        this.datePlayed = new Date();
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof ch.tofind.commusica.media.Mode)) {
            return false;
        }

        ch.tofind.commusica.media.Mode mode = (ch.tofind.commusica.media.Mode) object;

        return Objects.equals(title, mode.title) &&
               Objects.equals(artist, mode.artist) &&
               Objects.equals(album, mode.album) &&
               length == mode.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, album);
    }

    @Override
    public String toString() {

        String format = Configuration.getInstance().get("DEFAULT_DATE_FORMAT");

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        String dateAddedString = dateAdded == null ? "N/A" : dateFormat.format(dateAdded);
        String datePlayedString = datePlayed == null ? "N/A" : dateFormat.format(datePlayed);

        return "Mode"                           + '\n' + '\t' +
               "Title......: " + title            + '\n' + '\t' +
               "Artist.....: " + artist           + '\n' + '\t' +
               "Album......: " + album            + '\n' + '\t' +
               "Length.....: " + length           + '\n' + '\t' +
               "URI........: " + uri              + '\n' + '\t' +
               "Date added.: " + dateAddedString  + '\n' + '\t' +
               "Date played: " + datePlayedString + '\n';
    }
}
