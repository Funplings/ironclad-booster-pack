package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class BlindRage extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BlindRage",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int DAMAGE = 1;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = 1;

    public BlindRage() {
        super(cardInfo);
        setDamage(DAMAGE);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new MakeTempCardInHandAction(new Dazed(), MAGIC_NUMBER));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlindRage();
    }
}
