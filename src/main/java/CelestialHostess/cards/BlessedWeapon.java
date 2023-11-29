package CelestialHostess.cards;

import CelestialHostess.actions.DoAction;
import CelestialHostess.actions.DoForEachMiracleAction;
import CelestialHostess.cardmods.FlatDamageMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractEmpowerCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static CelestialHostess.MainModfile.makeID;

public class BlessedWeapon extends AbstractEmpowerCard {
    public final static String ID = makeID(BlessedWeapon.class.getSimpleName());

    public BlessedWeapon() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 11;
        baseMagicNumber = magicNumber = 2;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
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
    public void onEmpower() {
        upgradeMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Inflame.ID;
    }
}