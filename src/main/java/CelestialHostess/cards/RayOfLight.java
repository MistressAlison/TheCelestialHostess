package CelestialHostess.cards;

import CelestialHostess.cardmods.TributeMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.EnterCardGroupPatches;
import CelestialHostess.util.Wiz;
import CelestialHostess.vfx.BigExplosionVFX;
import CelestialHostess.vfx.SpotlightEnemyEffect;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InversionBeamEffect;

import static CelestialHostess.MainModfile.makeID;

public class RayOfLight extends AbstractEasyCard implements EnterCardGroupPatches.OnEnterCardGroupCard {
    public final static String ID = makeID(RayOfLight.class.getSimpleName());

    public RayOfLight() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 24;
        CardModifierManager.addModifier(this, new TributeMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new SpotlightEnemyEffect(m)));
            addToBot(new VFXAction(new InversionBeamEffect(m.hb.cX)));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
            addToBot(new BigExplosionVFX(m));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }

    @Override
    public String cardArtCopy() {
        return Wish.ID;
    }

    @Override
    public void onEnter(CardGroup g) {
        if (g == Wiz.adp().hand) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (c instanceof Miracle) {
                    setCostForTurn(this.costForTurn - 1);
                }
            }
        }
    }

    public void triggerOnCardPlayed(AbstractCard c) {
        if (c instanceof Miracle) {
            if (Wiz.adp().hand.contains(this)) {
                setCostForTurn(this.costForTurn - 1);
            }
        }
    }
}