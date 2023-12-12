package CelestialHostess.cards;

import CelestialHostess.actions.DoIfAction;
import CelestialHostess.actions.HolyAction;
import CelestialHostess.cardmods.FlatDamageMod;
import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
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
        CardModifierManager.addModifier(this, new HolyMod());
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {});
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new HolyAction(() -> {
            for (AbstractCard c : Wiz.adp().hand.group) {
                if (c.baseDamage > 0 && c.type == CardType.ATTACK) {
                    c.superFlash(Color.GOLD.cpy());
                    CardModifierManager.addModifier(c, new FlatDamageMod(magicNumber));
                }
            }
        }));
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DoIfAction(() -> !Wiz.auraActive(), () -> {
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
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return PerfectedStrike.ID;
    }
}