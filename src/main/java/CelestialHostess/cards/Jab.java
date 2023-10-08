package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.SneakyStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Jab extends AbstractEasyCard {
    public final static String ID = makeID(Jab.class.getSimpleName());

    public Jab() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 6;
        tags.add(CustomTags.HOSTESS_COMBO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == CardType.ATTACK && Wiz.getAdjacentCards(this).contains(c)) {
            if (m != null) {
                addToBot(new NewQueueCardAction(this, m, true, true));
            } else {
                addToBot(new NewQueueCardAction(this, true, true, true));
            }
        }
    }

    @Override
    public String cardArtCopy() {
        return SneakyStrike.ID;
    }
}