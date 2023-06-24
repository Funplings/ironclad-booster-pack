package basicmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SetCostToZeroAction extends AbstractGameAction {
    private AbstractCard card;

    public SetCostToZeroAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if (this.card != null) this.card.modifyCostForCombat(-1 * this.card.cost);
        this.isDone = true;
    }
}
