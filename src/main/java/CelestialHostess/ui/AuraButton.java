package CelestialHostess.ui;

import CelestialHostess.MainModfile;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.powers.*;
import CelestialHostess.powers.interfaces.AuraTriggerPower;
import CelestialHostess.util.Wiz;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.EndTurnGlowEffect;
import com.megacrit.cardcrawl.vfx.EndTurnLongPressBarFlashEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class AuraButton {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MainModfile.makeID(AuraButton.class.getSimpleName()));
    public static final String[] TIP_TEXT = uiStrings.EXTRA_TEXT;
    public static final String[] BUTTON_TEXT = uiStrings.TEXT;
    private String label;
    public static final String ACTIVATE_TEXT = BUTTON_TEXT[0];
    public static final String ALREADY_ACTIVE_TEXT = BUTTON_TEXT[1];
    private static final Color DISABLED_COLOR = new Color(0.7F, 0.7F, 0.7F, 1.0F);
    private static final float SHOW_X = 198.0F * Settings.xScale;//1640.0F * Settings.xScale;
    private static final float SHOW_Y = 320.0F * Settings.yScale;
    private static final float HIDE_X = SHOW_X - 500.0F * Settings.xScale;
    private float current_x;
    private float current_y;
    private float target_x;
    private boolean isHidden;
    public boolean enabled;
    private boolean isDisabled;
    private boolean canTrigger;
    private Color textColor;
    private final ArrayList<EndTurnGlowEffect> glowList;
    private static final float GLOW_INTERVAL = 1.2F;
    private float glowTimer;
    public boolean isGlowing;
    private Hitbox hb;
    private float holdProgress;
    private static final float HOLD_DUR = 0.4F;
    private Color holdBarColor;

    public AuraButton() {
        this.label = BUTTON_TEXT[0];// 40
        this.current_x = HIDE_X;// 46
        this.current_y = SHOW_Y;
        this.target_x = this.current_x;// 47
        this.isHidden = true;// 48
        this.enabled = false;// 49
        this.isDisabled = false;// 50
        this.canTrigger = false;
        this.glowList = new ArrayList<>();// 54
        this.glowTimer = 0.0F;// 56
        this.isGlowing = false;// 57
        this.hb = new Hitbox(0.0F, 0.0F, 230.0F * Settings.scale, 110.0F * Settings.scale);// 60
        this.holdProgress = 0.0F;// 63
        this.holdBarColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);// 65
    }

    public void update() {
        this.enabled = canTrigger && EnergyPanel.totalCount >= 1;
        this.glow();// 68
        this.updateHoldProgress();// 69
        if (this.current_x != this.target_x) {// 71
            this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);// 72
            if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {// 73
                this.current_x = this.target_x;// 74
            }
        }

        this.hb.move(this.current_x, this.current_y);// 78
        if (this.enabled) {
            this.isDisabled = AbstractDungeon.isScreenUp || AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode;// 84

            if (AbstractDungeon.player.hoveredCard == null) {// 87
                this.hb.update();// 88
            }

            if (!Settings.USE_LONG_PRESS && InputHelper.justClickedLeft && this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {// 91
                this.hb.clickStarted = true;// 93
                CardCrawlGame.sound.play("UI_CLICK_1");// 94
            }

            if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {// 97
                if (this.hb.justHovered && AbstractDungeon.player.hoveredCard == null) {// 99
                    CardCrawlGame.sound.play("UI_HOVER");// 100
                    // 101

                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        if (c.hasTag(CustomTags.HOSTESS_HOLY)) {// 102
                            c.superFlash(Color.GOLD.cpy());// 103
                        }
                    }
                }
            }
        }

        if (this.holdProgress == HOLD_DUR && !this.isDisabled && !AbstractDungeon.isScreenUp) {
            this.trigger();
            this.holdProgress = 0.0F;
            AbstractDungeon.effectsQueue.add(new EndTurnLongPressBarFlashEffect());
        }

        if ((!Settings.USE_LONG_PRESS) && (this.hb.clicked && !this.isDisabled && this.enabled)) {
            this.hb.clicked = false;
            if (!AbstractDungeon.isScreenUp) {
                this.trigger();
            }
        }
    }

    private void updateHoldProgress() {
        if (!Settings.USE_LONG_PRESS && !InputHelper.isMouseDown) {// 129
            this.holdProgress -= Gdx.graphics.getDeltaTime();// 131
            if (this.holdProgress < 0.0F) {// 132
                this.holdProgress = 0.0F;// 133
            }

        } else {
            if ((this.hb.hovered && InputHelper.isMouseDown) && !this.isDisabled && this.enabled) {// 138 139
                this.holdProgress += Gdx.graphics.getDeltaTime();// 140
                if (this.holdProgress > HOLD_DUR) {// 141
                    this.holdProgress = HOLD_DUR;// 142
                }
            } else {
                this.holdProgress -= Gdx.graphics.getDeltaTime();// 145
                if (this.holdProgress < 0.0F) {// 146
                    this.holdProgress = 0.0F;// 147
                }
            }

        }
    }// 135 150

    public void enable() {
        enabled = true;
        updateText(ACTIVATE_TEXT);
        canTrigger = true;
    }

    public void disable() {
        enabled = false;
        hb.hovered = false;
        isGlowing = false;
        canTrigger = false;
    }

    public void trigger() {
        enabled = false;
        hb.hovered = false;
        isGlowing = false;
        canTrigger = false;

        updateText(ALREADY_ACTIVE_TEXT);

        AbstractDungeon.player.loseEnergy(1);
        Wiz.applyToSelf(new DivineForcePower(Wiz.adp()));
        AbstractPower preserve = Wiz.adp().getPower(ChargePreservationPower.POWER_ID);
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof AuraTriggerPower) {
                ((AuraTriggerPower) p).onActivateAura();
                if (preserve == null) {
                    Wiz.atb(new RemoveSpecificPowerAction(Wiz.adp(), Wiz.adp(), p));
                }
            }
        }
        if (preserve != null) {
            Wiz.atb(new ReducePowerAction(Wiz.adp(), Wiz.adp(), preserve, 1));
        }
    }

    public void updateText(String msg) {
        label = msg;
    }

    private void glow() {
        if (this.isGlowing && !this.isHidden) {// 195
            if (this.glowTimer < 0.0F) {// 196
                this.glowList.add(new EndTurnGlowEffect());// 197
                this.glowTimer = GLOW_INTERVAL;// 198
            } else {
                this.glowTimer -= Gdx.graphics.getDeltaTime();// 200
            }
        }

        Iterator<EndTurnGlowEffect> i = this.glowList.iterator();// 205
        while(i.hasNext()) {
            AbstractGameEffect e = i.next();// 206
            e.update();// 207
            if (e.isDone) {// 208
                i.remove();// 209
            }
        }

    }// 212

    public void hide() {
        if (!this.isHidden) {// 215
            this.target_x = HIDE_X;// 216
            this.isHidden = true;// 217
        }

    }// 219

    public void show() {
        if (this.isHidden) {// 222
            this.target_x = SHOW_X;// 223
            this.isHidden = false;// 224
            if (this.isGlowing) {// 225
                this.glowTimer = -1.0F;// 226
            }
        }

    }// 229

    public void render(SpriteBatch sb) {
        if (!Settings.hideEndTurn) {// 232
            float tmpY = this.current_y;// 233
            this.renderHoldEndTurn(sb);// 234
            if (!this.isDisabled && this.enabled) {// 237
                if (this.hb.hovered) {// 244
                    this.textColor = Color.CYAN;// 248
                } else if (this.isGlowing) {// 251
                    this.textColor = Settings.GOLD_COLOR;// 252
                } else {
                    this.textColor = Settings.CREAM_COLOR;// 254
                }

                if (this.hb.hovered && !AbstractDungeon.isScreenUp && !Settings.isTouchScreen) {// 258
                    String body = TIP_TEXT[1];
                    boolean hasFire = Wiz.adp().hasPower(FireChargePower.POWER_ID);
                    boolean hasIce = Wiz.adp().hasPower(IceChargePower.POWER_ID);
                    boolean hasWind = Wiz.adp().hasPower(WindChargePower.POWER_ID);
                    boolean hasLight = Wiz.adp().hasPower(LightChargePower.POWER_ID);
                    float dy = 275f;
                    if (hasFire || hasIce || hasWind || hasLight) {
                        body += TIP_TEXT[2];
                        dy += 50f;
                        if (hasFire) {
                            body += TIP_TEXT[3] + FireChargePower.EFFECT * Wiz.adp().getPower(FireChargePower.POWER_ID).amount + TIP_TEXT[4];
                            dy += 50f;
                        }
                        if (hasIce) {
                            body += TIP_TEXT[5] + IceChargePower.EFFECT * Wiz.adp().getPower(IceChargePower.POWER_ID).amount + TIP_TEXT[6];
                            dy += 25f;
                        }
                        if (hasWind) {
                            int amount = WindChargePower.EFFECT * Wiz.adp().getPower(WindChargePower.POWER_ID).amount;
                            body += TIP_TEXT[7] + amount + (amount == 1 ? TIP_TEXT[8] : TIP_TEXT[9]);
                            dy += 25f;
                        }
                        if (hasLight) {
                            body += TIP_TEXT[10] + Wiz.adp().getPower(LightChargePower.POWER_ID).amount + TIP_TEXT[11];
                            dy += 25f;
                        }
                    }
                    TipHelper.renderGenericTip(this.current_x - 90.0F * Settings.scale, this.current_y + dy * Settings.scale, TIP_TEXT[0], body);// 259 262
                }
            } else if (this.label.equals(ALREADY_ACTIVE_TEXT)) {// 238
                this.textColor = Settings.CREAM_COLOR;// 239
            } else {
                this.textColor = Color.LIGHT_GRAY;// 241
            }

            if (this.hb.clickStarted && !AbstractDungeon.isScreenUp) {// 267
                tmpY -= 2.0F * Settings.scale;// 268
            } else if (this.hb.hovered && !AbstractDungeon.isScreenUp) {// 269
                tmpY += 2.0F * Settings.scale;// 270
            }

            if (!this.enabled) {// 273
                ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);// 280
            } else if (!this.isDisabled && (!this.hb.clickStarted || !this.hb.hovered)) {// 274
                sb.setColor(Color.WHITE);// 277
            } else {
                sb.setColor(DISABLED_COLOR);// 275
            }

            Texture buttonImg;
            if (this.isGlowing && !this.hb.clickStarted) {// 284
                buttonImg = ImageMaster.END_TURN_BUTTON_GLOW;// 285
            } else {
                buttonImg = ImageMaster.END_TURN_BUTTON;// 287
            }

            if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {// 289
                sb.draw(ImageMaster.END_TURN_HOVER, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);// 290
            }

            sb.draw(buttonImg, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);// 309
            if (!this.enabled) {// 327
                ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);// 328
            }

            this.renderGlowEffect(sb, this.current_x, this.current_y);// 331
            if ((this.hb.hovered || this.holdProgress > 0.0F) && !this.isDisabled && !AbstractDungeon.isScreenUp) {// 333
                sb.setBlendFunction(770, 1);// 334
                sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);// 335
                sb.draw(buttonImg, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);// 336
                sb.setBlendFunction(770, 771);// 353
            }

            FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.current_x - 0.0F * Settings.scale, tmpY - 3.0F * Settings.scale, this.textColor);// 381
            if (!this.isHidden) {// 389
                this.hb.render(sb);// 390
            }
        }

    }// 393

    private void renderHoldEndTurn(SpriteBatch sb) {
        if (Settings.USE_LONG_PRESS) {// 396
            this.holdBarColor.r = 0.0F;// 400
            this.holdBarColor.g = 0.0F;// 401
            this.holdBarColor.b = 0.0F;// 402
            this.holdBarColor.a = this.holdProgress * 1.5F;// 403
            sb.setColor(this.holdBarColor);// 404
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.current_x - 107.0F * Settings.scale, this.current_y + 53.0F * Settings.scale - 7.0F * Settings.scale, 525.0F * Settings.scale * this.holdProgress + 14.0F * Settings.scale, 20.0F * Settings.scale);// 405
            this.holdBarColor.r = this.holdProgress * 2.5F;// 412
            this.holdBarColor.g = 0.6F + this.holdProgress;// 413
            this.holdBarColor.b = 0.6F;// 414
            this.holdBarColor.a = 1.0F;// 415
            sb.setColor(this.holdBarColor);// 416
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.current_x - 100.0F * Settings.scale, this.current_y + 53.0F * Settings.scale, 525.0F * Settings.scale * this.holdProgress, 6.0F * Settings.scale);// 417
        }
    }// 397 423

    private void renderGlowEffect(SpriteBatch sb, float x, float y) {
        // 426

        for (EndTurnGlowEffect e : this.glowList) {
            e.render(sb, x, y);// 427
        }

    }// 429
}
