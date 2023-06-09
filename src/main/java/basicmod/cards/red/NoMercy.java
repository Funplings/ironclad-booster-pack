package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class NoMercy extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "NoMercy",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int DAMAGE = 0;

    private static final int MAGIC_NUMBER = 5;
    private static final int UPG_MAGIC_NUMBER = 1;
    public NoMercy() {
        super(cardInfo);
        setDamage(DAMAGE);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
//        int attackCount = attacksPlayedThisCombat();
//        this.baseDamage = attackCount * this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int attackCount = attacksPlayedThisCombat();
        this.baseDamage = attackCount * this.magicNumber;
        calculateCardDamage(m);
        if (this.damage < 20) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        int attackCount = attacksPlayedThisCombat();
        if (c.type == CardType.ATTACK) {
            attackCount++;
        }
        this.baseDamage = attackCount * this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        int attackCount = attacksPlayedThisCombat();
        this.baseDamage = attackCount * this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int attackCount = attacksPlayedThisCombat();
        this.baseDamage = attackCount * this.magicNumber;
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        int attackCount = attacksPlayedThisCombat();
        this.baseDamage = attackCount * this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    private int attacksPlayedThisCombat() {
        int attackCount = 0;
        for (AbstractCard card: AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (!card.equals(this) && card.type == CardType.ATTACK) {
                attackCount++;
            }
        }
        return attackCount;
    }

    @Override
    public AbstractCard makeCopy() {
        return new NoMercy();
    }
}
