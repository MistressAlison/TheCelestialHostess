package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.tokens.FrostBlessing;
import CelestialHostess.util.KeywordManager;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.blue.ColdSnap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class FrostShard extends AbstractEasyCard {
    public final static String ID = makeID(FrostShard.class.getSimpleName());

    public FrostShard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new FrostBlessing();
        addCustomKeyword(KeywordManager.HOLY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.makeInHand(new FrostBlessing(), magicNumber);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        //upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return ColdSnap.ID;
    }
}