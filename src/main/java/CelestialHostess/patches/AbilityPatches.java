package CelestialHostess.patches;

import CelestialHostess.cards.abstracts.AbstractAbilityCard;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CtBehavior;

public class AbilityPatches {
    @SpirePatch2(clz = SingleCardViewPopup.class, method = "render")
    public static class FixSCVButton {
        @SpireInsertPatch(locator = Locator.class)
        public static void plz(AbstractCard ___card) {
            if (___card instanceof AbstractAbilityCard) {
                ((AbstractAbilityCard) ___card).expUpgrade = true;
            }
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(AbstractCard.class, "upgrade");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }
}
