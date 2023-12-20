package CelestialHostess.cards;

import CelestialHostess.actions.DoIfAction;
import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.powers.ChargePreservationPower;
import CelestialHostess.powers.interfaces.AuraTriggerPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static CelestialHostess.MainModfile.makeID;

public class ImbuedStrike extends AbstractEasyCard {
    public final static String ID = makeID(ImbuedStrike.class.getSimpleName());

    public ImbuedStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 1;
        tags.add(CardTags.STRIKE);
        CardModifierManager.addModifier(this, new HolyMod());
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {});
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new DoIfAction(Wiz::auraActive,
                () -> Wiz.applyToSelfTop(new ChargePreservationPower(p, magicNumber)),
                () -> {
                    for (AbstractPower pow : Wiz.adp().powers) {
                        if (pow instanceof AuraTriggerPower) {
                            ((AuraTriggerPower) pow).onActivateAura();
                        }
                    }
                }));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return SeverSoul.ID;
    }
}