package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.powers.BloodLossPower;
import basicmod.powers.BloodLustPower;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class BloodLust extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BloodLust",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int MAGIC_NUMBER = 3;

    public BloodLust() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BloodLustPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new BloodLossPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodLust();
    }
}
