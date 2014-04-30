
package com.skiwi.tcg.view.objects;

import com.skiwi.tcg.model.cards.Card;
import com.skiwi.tcg.model.objects.Hand;
import com.skiwi.tcg.view.interfaces.View;

/**
 *
 * @author Frank van Heeswijk
 */
public interface HandView extends View<Hand> {
    public void onCardAdded(final Card card);

    public void onCardPlayed(final int cardIndex);

    public void onCardsSwapped(final int cardIndexOne, final int cardIndexTwo);
}