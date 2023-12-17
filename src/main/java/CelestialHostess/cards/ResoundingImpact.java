package CelestialHostess.cards;

import CelestialHostess.actions.DoAction;
import CelestialHostess.cardmods.FlatDamageMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class ResoundingImpact extends AbstractEasyCard {
    public final static String ID = makeID(ResoundingImpact.class.getSimpleName());

    public ResoundingImpact() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DoAction(() -> {
            superFlash(Color.GOLD.cpy());
            CardModifierManager.addModifier(this, new FlatDamageMod(magicNumber));
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(3);
    }

    @Override
    public String cardArtCopy() {
        return Bash.ID;
    }
}