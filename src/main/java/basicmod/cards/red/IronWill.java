package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
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

    public IronWill() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, getBlockAmount()));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = getBlockAmount();
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    private int getBlockAmount() {
        int exhaustCount = AbstractDungeon.player.exhaustPile.group.size();
        int blockAmount = exhaustCount * this.magicNumber;
        if (this.upgraded) blockAmount += 3;
        return blockAmount;
    }

    @Override
    public AbstractCard makeCopy() {
        return new IronWill();
    }
}
