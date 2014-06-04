
package com.skiwi.gui.simple.console;

import com.skiwi.eventbus.EventBus;
import com.skiwi.eventbus.SimpleEventBus;
import com.skiwi.gui.GUIHelper;
import com.skiwi.gui.simple.Data;
import com.skiwi.tcg.model.games.Game;
import com.skiwi.tcg.model.players.Player;
import com.skiwi.tcg.model.players.PlayerAction;
import com.skiwi.tcg.model.players.PlayerConfiguration;
import com.skiwi.tcg.model.players.PlayerConfigurationBuilder;
import com.skiwi.tcg.model.players.TurnAction;
import com.skiwi.tcg.model.players.playeractions.AttackMonsterAction;
import com.skiwi.tcg.model.players.playeractions.DrawCardAction;
import com.skiwi.tcg.model.players.playeractions.FuseMonsterAction;
import com.skiwi.tcg.model.players.playeractions.PutMonsterOnFieldAction;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Frank van Heeswijk
 */
public class ConsoleController implements Initializable {
    @FXML
    private TextArea textArea;
    
    @FXML
    private TextField textField;
    
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Platform.runLater(textField::requestFocus);
        Thread thread = new Thread(this::init);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleTextFieldOnKeyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ENTER:
                textArea.appendText(textField.getText() + System.lineSeparator());
                messages.add(textField.getText());
                textField.clear();
                break;
            default:
                break;
        }
    }
    
    private void init() {
        PlayerConfiguration playerConfiguration = new PlayerConfigurationBuilder()
                .hitpoints(100)
                .turnAction(new GameTurnAction())
                .handCapacity(5)
                .fieldMonsterCapacity(5)
                .deckCards(Data.CARDS)
                .build();
        Player playerSelf = Player.createFromConfiguration(playerConfiguration, "Self");
        Player playerOpponent = Player.createFromConfiguration(playerConfiguration, "Opponent");
        
        Game game = new Game();
        game.setSelf(playerSelf);
        game.setOpponent(playerOpponent);
        
        EventBus eventBus = new SimpleEventBus();
        eventBus.registerListener(Object.class, handAddedEvent -> refreshAllStats(game));
        
        game.getPlayers().forEach(player -> player.getHand().setEventBus(eventBus));
        game.play();
    }
    
    private void refreshAllStats(final Game game) {
        clear();
        println("Active player: " + game.getActivePlayer().get().getName());
        println();
        game.getPlayers().forEach(this::showPlayerStats);
    }
    
    private void showPlayerStats(final Player player) {
        println("Player: " + player);
        println("Hand: " + player.getHand());
        println("Field: " + player.getField());
        println();
    }
    
    private void clear() {
        GUIHelper.runSafe(textArea::clear);
    }
    
    private void println() {
        GUIHelper.runSafe(() -> textArea.appendText(System.lineSeparator()));
    }
    
    private void println(final String text) {
        GUIHelper.runSafe(() -> textArea.appendText(text + System.lineSeparator()));
    }
    
    private class GameTurnAction implements TurnAction {
        @Override
        public void performTurn(final Player player) {
            try {
                Objects.requireNonNull(player, "player");
                new DrawCardAction().performAction(player);
                String command;
                do {
                    String message = messages.take();
                    String[] commands = message.split(" ");
                    command = commands[0];
                    switch (command) {
                        case "put":
                            if (!validateCommand(message, 2)) {
                                println("Invalid syntax for the put command");
                                break;
                            }
                            int handCardIndex = Integer.parseInt(commands[1]);
                            int fieldCardIndex = Integer.parseInt(commands[2]);
                            PutMonsterOnFieldAction putMonsterOnFieldAction = new PutMonsterOnFieldAction(handCardIndex, fieldCardIndex);
                            tryAction(putMonsterOnFieldAction, player);
                            break;
                        case "attack":
                            if (!validateCommand(message, 2)) {
                                println("Invalid syntax for the attack command");
                                break;
                            }
                            int selfMonsterIndex = Integer.parseInt(commands[1]);
                            int opponentMonsterIndex = Integer.parseInt(commands[2]);
                            AttackMonsterAction attackMonsterAction = new AttackMonsterAction(selfMonsterIndex, opponentMonsterIndex, player.getOpponent());
                            tryAction(attackMonsterAction, player);
                            break;
                        case "fuse":
                            if (!validateCommand(message, 3)) {
                                println("Invalid syntax for the fuse command");
                                break;
                            }
                            int fusionCardIndex = Integer.parseInt(commands[1]);
                            int baseMonsterCardIndex = Integer.parseInt(commands[2]);
                            int fuserMonsterCardIndex = Integer.parseInt(commands[3]);
                            FuseMonsterAction fuseMonsterAction = new FuseMonsterAction(fusionCardIndex, baseMonsterCardIndex, fuserMonsterCardIndex);
                            tryAction(fuseMonsterAction, player);
                            break;
                        case "help":
                            println("These commands are available:");
                            println("put <cardIndex> <fieldIndex> - puts a monster on the field");
                            println("attack <monsterIndex> <targetMonsterIndex> - attacks a monster from the enemy");
                            println("fuse <cardIndex> <monsterIndex1> <monsterIndex2> - fuses two monsters of your side");
                            println("end - ends your turn");
                            println("exit - exits the application");
                            break;
                        case "end":
                            break;
                        case "exit":
                            Platform.exit();
                            break;
                        default:
                            println("Unrecognized command");
                            break;
                    }
                } while (!command.equals("end"));
            } catch (InterruptedException ex) {
                Thread.interrupted();
            }
        }

        private void tryAction(final PlayerAction playerAction, final Player player) {
            if (!playerAction.isActionAllowed(player)) {
                println("Action not allowed");
                return;
            }
            playerAction.performAction(player);
        }

        private boolean validateCommand(final String command, final int arguments) {
            Objects.requireNonNull(command, "command");
            String[] commands = command.split(" ");
            if (commands.length != arguments + 1) {
                return false;
            }
            for (int i = 1; i <= arguments; i++) {
                try {
                    Integer.parseInt(commands[i]);
                } catch (NumberFormatException ex) {
                    return false;
                }
            }
            return true;
        }
    }
}
