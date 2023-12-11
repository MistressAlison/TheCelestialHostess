package CelestialHostess.cards;

import CelestialHostess.actions.HolyAction;
import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.purple.FollowUp;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static CelestialHostess.MainModfile.makeID;

public class CrossCut extends AbstractEasyCard {
    public final static String ID = makeID(CrossCut.class.getSimpleName());

    public CrossCut() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 8;
        //baseMagicNumber = magicNumber = 1;
        isMultiDamage = true;
        CardModifierManager.addModifier(this, new HolyMod());
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {});
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        Wiz.atb(new HolyAction(() -> Wiz.makeInHand(new Miracle(), 1)));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return FollowUp.ID;
    }
}