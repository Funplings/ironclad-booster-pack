package basicmod.cards.red;

import basicmod.cards.BaseCard;
import basicmod.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static basicmod.IroncladBoosterPack.makeID;

public class BattleReady extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BattleReady",
            3,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            CardColor.RED
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int MAGIC_NUMBER = 1;

    private static final int UPG_MAGIC_NUMBER = 1;


    public BattleReady() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        this.setCostUpgrade(2);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c: AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK) {
                addToBot(new ReduceCostAction(c.uuid, 999));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BattleReady();
    }
}
