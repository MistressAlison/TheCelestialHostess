package CelestialHostess.cards;

import CelestialHostess.actions.DoAction;
import CelestialHostess.cardmods.FlatDamageMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class CuttingEdge extends AbstractEasyCard {
    public final static String ID = makeID(CuttingEdge.class.getSimpleName());

    public CuttingEdge() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DoAction(() -> {
            for (AbstractCard c : Wiz.adp().hand.group) {
                if (c.baseDamage > 0 && c.type == CardType.ATTACK) {
                    c.superFlash(Color.GOLD.cpy());
                    CardModifierManager.addModifier(c, new FlatDamageMod(magicNumber));
                }
            }
        }));
    }

    @Override
    public void upp() {
        //upgradeDamage(4);
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return PerfectedStrike.ID;
    }
}