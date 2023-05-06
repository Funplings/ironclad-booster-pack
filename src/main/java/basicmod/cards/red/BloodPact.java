package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class BloodPact extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BloodPact",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int DAMAGE = 3;
    private static final int BLOCK = 15;
    private static final int UPG_BLOCK = 3;

    public BloodPact() {
        super(cardInfo);
        setDamage(DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, this.damage));
        addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodPact();
    }
}
