package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEmpowerCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static CelestialHostess.MainModfile.makeID;

public class BlessedWeapon extends AbstractEmpowerCard {
    public final static String ID = makeID(BlessedWeapon.class.getSimpleName());

    public BlessedWeapon() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 11;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
        /*addToBot(new DoAction(() -> {
            for (AbstractCard c : Wiz.adp().hand.group) {
                if (c.baseDamage > 0 && c.type == CardType.ATTACK) {
                    c.superFlash(Color.GOLD.cpy());
                    CardModifierManager.addModifier(c, new FlatDamageMod(magicNumber));
                }
            }
        }));*/
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard copy = super.makeStatEquivalentCopy();
        copy.magicNumber = this.magicNumber;
        return copy;
    }

    @Override
    public void onEmpower() {
        upgradeMagicNumber(secondMagic);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Inflame.ID;
    }
}