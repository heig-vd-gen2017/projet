package protocol;

import errors.FullGameException;
import errors.UsernameUsedException;
import game.Spirte;

import java.io.IOException;

/**
 * Interface that declare all the functions that the gamer has to implement for the game protocol.
 *
 * @author Luca Sivillica
 * @version 1.0
 */
public interface GamerProtocol extends Protocol {

    /**
     * This function allows to join the game by sending the gamer username.
     *
     * @throws UsernameUsedException if the username sent is already used by one of the players
     * @throws FullGameException     if the game is full (too many players)
     * @throws IOException           if there are problems with sockets or files
     */
    void joinGame(String username) throws UsernameUsedException, FullGameException, IOException;

    /**
     * This function send the message which informs the server that the datas about the game has been updated.
     */
    void updatedDataGame();

    /**
     * This function send the Spirte object on which the player clicked.
     *
     * @param spirte the spirte object sent
     */
    void sendClickedSpirte(Spirte spirte);

    /**
     * This function allows to quit the game.
     */
    void quitGame();

}
