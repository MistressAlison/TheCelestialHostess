package CelestialHostess.cardmods;

import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.KeywordManager;
import CelestialHostess.util.Wiz;
import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static CelestialHostess.MainModfile.makeID;

public class HolyMod extends AbstractCardModifier {
    public static String ID = makeID(HolyMod.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;
    public static String NAME = CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    public static final ArrayList<String> descriptors = new ArrayList<>();
    public static final ArrayList<TooltipInfo> tips = new ArrayList<>();
    private int dd, db, dm;

    static {
        descriptors.add(NAME);
        tips.add(new TooltipInfo(BaseMod.getKeywordTitle(KeywordManager.HOLY), BaseMod.getKeywordDescription(KeywordManager.HOLY)));
    }

    //Handles Stat Mods, Descriptor, Tooltip, Tag, and Glow (via CardGlowManager due to tag)
    public HolyMod() {
        this(0, 0, 0);
    }

    public HolyMod(int dd, int db, int dm) {
        priority = -1;
        this.dd = dd;
        this.db = db;
        this.dm = dm;
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if (Wiz.auraActive()) {
            damage += dd;
        }
        return damage;
    }

    @Override
    public float modifyBaseBlock(float block, AbstractCard card) {
        if (Wiz.auraActive()) {
            block += db;
        }
        return block;
    }

    @Override
    public float modifyBaseMagic(float magic, AbstractCard card) {
        if (Wiz.auraActive()) {
            magic += dm;
        }
        return magic;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return tips;
    }

    @Override
    public List<String> extraDescriptors(AbstractCard card) {
        return descriptors;
    }

    public void onInitialApplication(AbstractCard card) {
        card.tags.add(CustomTags.HOSTESS_HOLY);
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    public AbstractCardModifier makeCopy() {
        return new HolyMod(dd, db, dm);
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
