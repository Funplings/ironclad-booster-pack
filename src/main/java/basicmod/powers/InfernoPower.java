package basicmod.powers;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static basicmod.IroncladBoosterPack.makeID;

public class InfernoPower extends BasePower {

    public static final String POWER_ID = makeID("InfernoPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public InfernoPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ExhaustAction(this.amount, false,true, true));
    }
}
