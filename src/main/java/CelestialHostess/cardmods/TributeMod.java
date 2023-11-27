package CelestialHostess.cardmods;

import CelestialHostess.util.FormatHelper;
import CelestialHostess.util.TextureScaler;
import CelestialHostess.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static CelestialHostess.MainModfile.makeID;


public class TributeMod extends AbstractCardModifier {
    public static final String ID = makeID(TributeMod.class.getSimpleName());
    public static final String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;
    public static final Texture modIcon = TextureScaler.rescale(AbstractPower.atlas.findRegion("128/attackBurn"), 64, 64);
    private static final Color renderColor = new Color(1, 1, 1, 1);
    public int amount;

    public TributeMod(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        ArrayList<AbstractCardModifier> mods = CardModifierManager.getModifiers(card, ID);
        if (!mods.isEmpty()) {
            TributeMod mod = (TributeMod) mods.get(0);
            mod.amount += amount;
            return false;
        }
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.att(new ExhaustAction(amount, false, false, false));
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return Wiz.adp().hand.group.stream().filter(c -> c != card).count() >= amount;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return FormatHelper.insertBeforeText(rawDescription, TEXT[0]);
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        renderColor.a = card.transparency;
        ExtraIcons.icon(modIcon).text(String.valueOf(amount)).textOffsetX(3).drawColor(renderColor).render(card);
    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        renderColor.a = card.transparency;
        ExtraIcons.icon(modIcon).text(String.valueOf(amount)).textOffsetX(6).drawColor(renderColor).render(card);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new TributeMod(amount);
    }
}
