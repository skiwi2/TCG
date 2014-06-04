
package com.skiwi.gui;

import java.util.Objects;
import javafx.application.Platform;

/**
 *
 * @author Frank van Heeswijk
 */
public class GUIHelper {
    private GUIHelper() {
        throw new UnsupportedOperationException();
    }
    
    public static void runSafe(final Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable");
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        }
        else {
            Platform.runLater(runnable);
        }
    }
}
