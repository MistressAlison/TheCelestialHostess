package CelestialHostess.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static CelestialHostess.MainModfile.makeID;

public class PurgeMod extends AbstractCardModifier {
    public static String ID = makeID(PurgeMod.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[0];
    }

    public boolean shouldApply(AbstractCard card) {
        return !PurgeField.purge.get(card);
    }

    public void onInitialApplication(AbstractCard card) {
        PurgeField.purge.set(card, true);
    }

    public AbstractCardModifier makeCopy() {
        return new PurgeMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
