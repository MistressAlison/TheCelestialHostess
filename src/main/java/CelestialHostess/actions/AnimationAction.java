package CelestialHostess.actions;

import CelestialHostess.TheCelestialHostess;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AnimationAction extends AbstractGameAction {
    public enum Animation {
        ATTACK,
        SKILL,
        HAPPY,
        HURT
    }

    private Animation anim;

    public AnimationAction(Animation anim) {
        this.anim = anim;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player instanceof TheCelestialHostess) {
            ((TheCelestialHostess) AbstractDungeon.player).playAnimation(anim.name().toLowerCase());
            if (anim == Animation.ATTACK) {
                AbstractDungeon.player.useFastAttackAnimation();
            }
        }
        this.isDone = true;
    }
}
