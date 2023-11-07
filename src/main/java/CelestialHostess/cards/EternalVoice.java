package CelestialHostess.cards;

import CelestialHostess.actions.HolyAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;

import static CelestialHostess.MainModfile.makeID;

public class EternalVoice extends AbstractEasyCard {
    public final static String ID = makeID(EternalVoice.class.getSimpleName());

    public EternalVoice() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 3;
        isMultiDamage = true;
        tags.add(CustomTags.HOSTESS_IF_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));
        addToBot(new VFXAction(p, new IntimidateEffect(p.hb.cX, p.hb.cY), 1.0F));
        allDmg(AbstractGameAction.AttackEffect.FIRE, true);
        addToBot(new HolyAction(() -> allDmg(AbstractGameAction.AttackEffect.FIRE)));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return PiercingWail.ID;
    }
}