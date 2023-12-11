package CelestialHostess.cards;

import CelestialHostess.actions.HolyAction;
import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Strike extends AbstractEasyCard {
    public final static String ID = makeID(Strike.class.getSimpleName());

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
        CardModifierManager.addModifier(this, new HolyMod(-2, 0, 0));
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {
            @Override
            public int deltaDamage() {
                return -2;
            }
        });
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HolyAction(() -> dmgTop(m, AbstractGameAction.AttackEffect.FIRE, true)));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
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
        return Strike_Red.ID;
    }
}