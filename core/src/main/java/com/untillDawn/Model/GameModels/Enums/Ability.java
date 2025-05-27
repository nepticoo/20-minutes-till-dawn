package com.untillDawn.Model.GameModels.Enums;

import java.io.Serializable;

public enum Ability implements Serializable {
    vitality,
    damager,
    procrease,
    ammocrease,
    speedy;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
