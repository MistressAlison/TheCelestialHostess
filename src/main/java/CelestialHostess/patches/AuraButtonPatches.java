package CelestialHostess.patches;

import CelestialHostess.TheCelestialHostess;
import CelestialHostess.ui.AuraButton;
import CelestialHostess.util.Wiz;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;

public class AuraButtonPatches {
    @SpirePatch2(clz = OverlayMenu.class, method = SpirePatch.CLASS)
    public static class AuraButtonField {
        public static SpireField<AuraButton> button = new SpireField<>(AuraButton::new);
    }

    private static boolean shownThisCombat;

    public static boolean shouldShowButton() {
        return shownThisCombat || Wiz.adp() instanceof TheCelestialHostess;
    }

    public static void cardCheck(AbstractCard card) {
        if (card.hasTag(CustomTags.HOSTESS_HOLY) || card.hasTag(CustomTags.HOSTESS_GIVES_CHARGE)) {
            AuraButtonField.button.get(AbstractDungeon.overlayMenu).show();
            shownThisCombat = true;
        }
    }

    @SpirePatch2(clz = GameActionManager.class, method = "clear")
    public static class ResetShownState {
        @SpirePrefixPatch
        public static void reset() {
            shownThisCombat = false;
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "update")
    public static class UpdateAuraButton {
        @SpirePrefixPatch
        public static void plz(OverlayMenu __instance) {
            AuraButtonField.button.get(__instance).update();
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderAuraButton {
        @SpirePrefixPatch
        public static void plz(OverlayMenu __instance, SpriteBatch sb) {
            AuraButtonField.button.get(__instance).render(sb);
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "showCombatPanels")
    public static class ShowAuraButton {
        @SpirePrefixPatch
        public static void plz(OverlayMenu __instance) {
            if (shouldShowButton()) {
                AuraButtonField.button.get(__instance).show();
                shownThisCombat = true;
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "hideCombatPanels")
    public static class HideAuraButton {
        @SpirePrefixPatch
        public static void plz(OverlayMenu __instance) {
            AuraButtonField.button.get(__instance).hide();
        }
    }

    @SpirePatch2(clz = EndTurnButton.class, method = "enable")
    public static class EnableAuraButton {
        @SpirePrefixPatch
        public static void plz() {
            AuraButtonField.button.get(AbstractDungeon.overlayMenu).enable();
        }
    }

    @SpirePatch2(clz = EndTurnButton.class, method = "disable", paramtypez = {})
    @SpirePatch2(clz = EndTurnButton.class, method = "disable", paramtypez = {boolean.class})
    public static class DisableAuraButton {
        @SpirePrefixPatch
        public static void plz() {
            AuraButtonField.button.get(AbstractDungeon.overlayMenu).disable();
        }
    }
}
