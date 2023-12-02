package CelestialHostess.icons;

import CelestialHostess.MainModfile;
import CelestialHostess.cardmods.CorruptMod;
import CelestialHostess.cardmods.FlatDamageMod;
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

    public static class SharpenIcon extends AbstractCustomIcon {
        static AbstractCustomIcon singleton;

        public SharpenIcon() {
            super(MainModfile.makeID("Sharpen"), FlatDamageMod.modIcon);
        }

        public static AbstractCustomIcon get() {
            if (singleton == null) {
                singleton = new SharpenIcon();
            }
            return singleton;
        }
    }

    public static class CorruptIcon extends AbstractCustomIcon {
        static AbstractCustomIcon singleton;

        public CorruptIcon() {
            super(MainModfile.makeID("Corrupt"), CorruptMod.modIcon);
        }

        public static AbstractCustomIcon get() {
            if (singleton == null) {
                singleton = new CorruptIcon();
            }
            return singleton;
        }
    }
}
