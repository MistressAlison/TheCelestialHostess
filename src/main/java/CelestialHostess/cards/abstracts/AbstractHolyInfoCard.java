package CelestialHostess.cards.abstracts;

import CelestialHostess.MainModfile;
import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.util.ProfessorHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public abstract class AbstractHolyInfoCard extends AbstractEasyCard {
    public static final String ID = MainModfile.makeID(AbstractHolyInfoCard.class.getSimpleName());
    public static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private final AbstractCard refCard;

    public AbstractHolyInfoCard(AbstractCard refCard, String desc) {
        super(refCard.cardID, -2, refCard.type, refCard.rarity, CardTarget.NONE);
        this.name = this.originalName = String.format(STRINGS.NAME, refCard.originalName);
        this.rawDescription = desc;
        initializeDescription();
        initializeTitle();
        this.refCard = refCard;
        syncStats();
        ProfessorHelper.tagNotIce(this);
    }

    public int deltaDamage() {
        return 0;
    }

    public int deltaBlock() {
        return 0;
    }

    public int deltaMagic() {
        return 0;
    }

    private void syncStats() {
        baseDamage = refCard.baseDamage + deltaDamage();
        isDamageModified = refCard.isDamageModified;
        baseBlock = refCard.baseBlock + deltaBlock();
        isBlockModified = refCard.isBlockModified;
        baseMagicNumber = refCard.baseMagicNumber + deltaMagic();
        magicNumber = refCard.magicNumber + deltaMagic();
        isMagicNumberModified = refCard.isMagicNumberModified;
        if (refCard instanceof AbstractEasyCard) {
            baseSecondDamage = ((AbstractEasyCard) refCard).baseSecondDamage + deltaDamage();
            isSecondDamageModified = ((AbstractEasyCard) refCard).isSecondDamageModified;
            baseSecondBlock = ((AbstractEasyCard) refCard).baseSecondBlock + deltaBlock();
            isSecondBlockModified = ((AbstractEasyCard) refCard).isSecondBlockModified;
            baseSecondMagic = ((AbstractEasyCard) refCard).baseSecondMagic + deltaMagic();
            secondMagic = ((AbstractEasyCard) refCard).secondMagic + deltaMagic();
            isSecondMagicModified = ((AbstractEasyCard) refCard).isSecondMagicModified;
        }
    }

    @Override
    public List<String> getCardDescriptors() {
        return HolyMod.descriptors;
    }

    @Override
    public String cardArtCopy() {
        if (refCard instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) refCard).cardArtCopy();
        }
        return VoidCard.ID;
    }

    @Override
    public void upp() {
        syncStats();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
}
