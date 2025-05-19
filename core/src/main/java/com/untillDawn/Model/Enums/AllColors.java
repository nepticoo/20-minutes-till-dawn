package com.untillDawn.Model.Enums;

import com.badlogic.gdx.graphics.Color;

public enum AllColors {
    backGround(39 / 255f, 32 / 255f, 48 / 255f, 1f),
    red(255 / 255f, 75 / 255f, 99 / 255f, 1f);

    public final Color color;

    AllColors(float r, float g, float b, float a) {
        color = new Color(r, g, b, a);
    }
}
