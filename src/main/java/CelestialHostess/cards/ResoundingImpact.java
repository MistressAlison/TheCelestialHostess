package CelestialHostess.cards;

import CelestialHostess.actions.HolyAction;
import CelestialHostess.cardmods.FlatDamageMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class ResoundingImpact extends AbstractEasyCard {
    public final static String ID = makeID(ResoundingImpact.class.getSimpleName());

    public ResoundingImpact() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 6;
        tags.add(CustomTags.HOSTESS_IF_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new HolyAction(() -> {
            superFlash(Color.GOLD.cpy());
            CardModifierManager.addModifier(this, new FlatDamageMod(magicNumber));
        }));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override
    public String cardArtCopy() {
        return Bash.ID;
    }
}