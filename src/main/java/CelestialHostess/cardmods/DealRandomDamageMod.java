package CelestialHostess.cardmods;

import CardAugments.util.CalcHelper;
import CelestialHostess.MainModfile;
import CelestialHostess.actions.InfusionTriggerAction;
import CelestialHostess.cards.cardvars.DynvarInterfaceManager;
import CelestialHostess.util.TextureScaler;
import CelestialHostess.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DealRandomDamageMod extends AbstractInfusion {
    public static final String ID = MainModfile.makeID(DealRandomDamageMod.class.getSimpleName());
    public static final String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;
    public static final Texture ICON = TextureScaler.rescale(AbstractPower.atlas.findRegion("128/noPain"), 64, 64);

    static {
        DynvarInterfaceManager.registerDynvarCarrier(ID);
    }

    public DealRandomDamageMod(int baseAmount) {
        super(ID, InfusionType.DAMAGE_RANDOM, baseAmount, TEXT[0], ICON);
    }

    public DealRandomDamageMod(int baseAmount, int relicStatsVal) {
        super(ID, InfusionType.DAMAGE_RANDOM, baseAmount, TEXT[0], ICON);
        this.relicStatsVal = relicStatsVal;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                if (mon!= null) {
                    int damage = CalcHelper.calculateCardDamage(baseVal, mon);
                    Wiz.att(new DamageAction(target, new DamageInfo(Wiz.adp(), damage, DamageInfo.DamageType.NORMAL), AttackEffect.FIRE));
                    Wiz.att(new InfusionTriggerAction(DealRandomDamageMod.this, damage, baseVal == relicStatsVal ? damage : damage - CelestialHostess.util.CalcHelper.calculateCardDamage(baseVal - relicStatsVal, mon)));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DealRandomDamageMod(baseVal, relicStatsVal);
    }
}
