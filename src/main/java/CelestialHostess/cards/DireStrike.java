package CelestialHostess.cards;

import CelestialHostess.actions.DoAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.WildStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class DireStrike extends AbstractEasyCard {
    public final static String ID = makeID(DireStrike.class.getSimpleName());

    public DireStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new DoAction(() -> {
            if (m != null && m.getIntentBaseDmg() >= 0) {
                dmgTop(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            }
        }));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return WildStrike.ID;
    }
}