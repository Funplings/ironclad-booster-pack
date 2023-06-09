package basicmod.cards.archived;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static basicmod.IroncladBoosterPack.makeID;

public class FinishingMove extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FinishingMove",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;

    public FinishingMove() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower power: m.powers) {
            if (power.name.equals("Vulnerable")) {
                for (int i = 0; i < power.amount; i++) {
                    addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    addToBot(new RemoveSpecificPowerAction(m, p, "Vulnerable"));
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinishingMove();
    }
}
