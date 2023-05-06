package basicmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;

import static basicmod.IroncladBoosterPack.makeID;

public class TheBestDefensePower extends BasePower {

    public static final String POWER_ID = makeID("TheBestDefensePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TheBestDefensePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == AbstractDungeon.player && Objects.equals(power.ID, "Vulnerable")) {
            addToBot(new GainBlockAction(source, amount));
        }
    }
}
