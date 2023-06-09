package basicmod.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static basicmod.IroncladBoosterPack.makeID;

public class RestoreHealthPower extends BasePower {

    public static final String POWER_ID = makeID("RestoreHealthPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RestoreHealthPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0)
            p.heal(this.amount);
    }
}
