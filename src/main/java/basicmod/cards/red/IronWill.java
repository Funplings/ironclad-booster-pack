package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class IronWill extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "IronWill",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 1;

    public IronWill() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int exhaustCount = AbstractDungeon.player.exhaustPile.group.size();
        addToBot(new GainBlockAction(p, exhaustCount * this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new IronWill();
    }
}
