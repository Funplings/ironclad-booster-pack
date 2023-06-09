package basicmod.cards.archived;

import basicmod.cards.BaseCard;
import basicmod.powers.TheBestDefensePower;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class TheBestDefense extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "TheBestDefense",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;

    public TheBestDefense() {
        super(cardInfo);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TheBestDefensePower(p, this.block)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheBestDefense();
    }
}
