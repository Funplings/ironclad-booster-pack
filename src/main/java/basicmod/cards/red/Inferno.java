package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.powers.InfernoPower;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class Inferno extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Inferno",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;

    public Inferno() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InfernoPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Inferno();
    }
}
