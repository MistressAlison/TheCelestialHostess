package CelestialHostess.icons;

import CelestialHostess.MainModfile;
import CelestialHostess.cardmods.TributeMod;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

public class IconContainer {
    public static class TributeIcon extends AbstractCustomIcon {
        static AbstractCustomIcon singleton;

        public TributeIcon() {
            super(MainModfile.makeID("Tribute"), TributeMod.modIcon);
        }

        public static AbstractCustomIcon get() {
            if (singleton == null) {
                singleton = new TributeIcon();
            }
            return singleton;
        }
    }
}
