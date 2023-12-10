package CelestialHostess.patches;

import CelestialHostess.ui.AuraButton;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AuraButtonPatches {
    @SpirePatch2(clz = OverlayMenu.class, method = SpirePatch.CLASS)
    public static class AuraButtonField {
        public static SpireField<AuraButton> button = new SpireField<>(AuraButton::new);
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
            AuraButtonField.button.get(__instance).show();
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "hideCombatPanels")
    public static class HideAuraButton {
        @SpirePrefixPatch
        public static void plz(OverlayMenu __instance) {
            AuraButtonField.button.get(__instance).hide();
        }
    }

    @SpirePatch2(clz = EnableEndTurnButtonAction.class, method = "update")
    public static class EnableAuraButton {
        @SpirePrefixPatch
        public static void plz() {
            AuraButtonField.button.get(AbstractDungeon.overlayMenu).enable();
        }
    }
}
