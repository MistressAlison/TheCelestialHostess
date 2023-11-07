package CelestialHostess.cards;

import CelestialHostess.actions.HolyAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.SimmeringFury;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Punishment extends AbstractEasyCard {
    public final static String ID = makeID(Punishment.class.getSimpleName());

    public Punishment() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.HOSTESS_IF_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new HolyAction(() -> addToTop(new DrawCardAction(magicNumber))));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return SimmeringFury.ID;
    }
}