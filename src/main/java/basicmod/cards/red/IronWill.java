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
            CardRarity.COMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    public IronWill() {
        super(cardInfo);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
                addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IronWill();
    }
}
