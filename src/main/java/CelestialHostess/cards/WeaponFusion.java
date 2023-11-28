package CelestialHostess.cards;

import CelestialHostess.actions.BetterSelectCardsInHandAction;
import CelestialHostess.cardmods.FlatDamageMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.ConjureBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class WeaponFusion extends AbstractEasyCard {
    public final static String ID = makeID(WeaponFusion.class.getSimpleName());

    public WeaponFusion() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Strike();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(magicNumber, ExhaustAction.TEXT[0], true, true, c -> c.type == CardType.ATTACK, l -> {
            AbstractCard card = new Strike();
            card.baseDamage = 0;
            for (AbstractCard c : l) {
                Wiz.adp().hand.moveToExhaustPile(c);
                card.baseDamage += c.baseDamage;
                for (AbstractCardModifier mod : CardModifierManager.getModifiers(c, FlatDamageMod.ID)) {
                    CardModifierManager.addModifier(card, mod.makeCopy());
                }
            }
            Wiz.makeInHand(card);
            l.clear();
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return ConjureBlade.ID;
    }
}