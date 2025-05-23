package com.untillDawn.Model.Enums;

import com.badlogic.gdx.graphics.Color;

public enum AllColors {
    backGround(39 / 255f, 32 / 255f, 48 / 255f, 1f),
    red(255 / 255f, 75 / 255f, 99 / 255f, 1f),
    green(81 / 255f, 152 / 255f, 114 / 255f, 1f),
    gold(236 / 255f, 212 / 255f, 68 / 255f, 1f),
    silver(152 / 255f, 166 / 255f, 212 / 255f, 1f),
    bronze(188 / 255f, 44 / 255f, 26 / 255f, 1f),
    darkGreen(1 / 255f, 142 / 255f, 66 / 255f, 1f),
    darkGrey(40 / 255f, 40 / 255f, 40 / 255f, 1f);
    public final Color color;

    AllColors(float r, float g, float b, float a) {
        color = new Color(r, g, b, a);
    }
}
