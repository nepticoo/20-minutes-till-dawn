package com.untillDawn.Model.Enums;

import com.badlogic.gdx.Input;

public enum Keybinding {
    up,
    down,
    left,
    right,
    reload,
    autoAim,
    pause;


    public static String toShortString(int keycode) {
        switch (keycode) {
            // Letters
            case Input.Keys.A:
                return "A";
            case Input.Keys.B:
                return "B";
            case Input.Keys.C:
                return "C";
            case Input.Keys.D:
                return "D";
            case Input.Keys.E:
                return "E";
            case Input.Keys.F:
                return "F";
            case Input.Keys.G:
                return "G";
            case Input.Keys.H:
                return "H";
            case Input.Keys.I:
                return "I";
            case Input.Keys.J:
                return "J";
            case Input.Keys.K:
                return "K";
            case Input.Keys.L:
                return "L";
            case Input.Keys.M:
                return "M";
            case Input.Keys.N:
                return "N";
            case Input.Keys.O:
                return "O";
            case Input.Keys.P:
                return "P";
            case Input.Keys.Q:
                return "Q";
            case Input.Keys.R:
                return "R";
            case Input.Keys.S:
                return "S";
            case Input.Keys.T:
                return "T";
            case Input.Keys.U:
                return "U";
            case Input.Keys.V:
                return "V";
            case Input.Keys.W:
                return "W";
            case Input.Keys.X:
                return "X";
            case Input.Keys.Y:
                return "Y";
            case Input.Keys.Z:
                return "Z";

            // Numbers
            case Input.Keys.NUM_0:
                return "0";
            case Input.Keys.NUM_1:
                return "1";
            case Input.Keys.NUM_2:
                return "2";
            case Input.Keys.NUM_3:
                return "3";
            case Input.Keys.NUM_4:
                return "4";
            case Input.Keys.NUM_5:
                return "5";
            case Input.Keys.NUM_6:
                return "6";
            case Input.Keys.NUM_7:
                return "7";
            case Input.Keys.NUM_8:
                return "8";
            case Input.Keys.NUM_9:
                return "9";

            // Arrow Keys (ASCII-safe)
            case Input.Keys.UP:
                return "UP";
            case Input.Keys.DOWN:
                return "DN";
            case Input.Keys.LEFT:
                return "LT";
            case Input.Keys.RIGHT:
                return "RT";

            // Modifiers
            case Input.Keys.SHIFT_LEFT:
            case Input.Keys.SHIFT_RIGHT:
                return "SFT";
            case Input.Keys.CONTROL_LEFT:
            case Input.Keys.CONTROL_RIGHT:
                return "CTL";
            case Input.Keys.ALT_LEFT:
            case Input.Keys.ALT_RIGHT:
                return "ALT";

            // Common Keys
            case Input.Keys.SPACE:
                return "SPC";
            case Input.Keys.ENTER:
                return "ENT";
            case Input.Keys.ESCAPE:
                return "ESC";
            case Input.Keys.BACKSPACE:
                return "BSP";
            case Input.Keys.TAB:
                return "TAB";
            case Input.Keys.INSERT:
                return "INS";
            case Input.Keys.HOME:
                return "HOM";
            case Input.Keys.END:
                return "END";
            case Input.Keys.PAGE_UP:
                return "PUP";
            case Input.Keys.PAGE_DOWN:
                return "PDN";

            // Function Keys
            case Input.Keys.F1:
                return "F1";
            case Input.Keys.F2:
                return "F2";
            case Input.Keys.F3:
                return "F3";
            case Input.Keys.F4:
                return "F4";
            case Input.Keys.F5:
                return "F5";
            case Input.Keys.F6:
                return "F6";
            case Input.Keys.F7:
                return "F7";
            case Input.Keys.F8:
                return "F8";
            case Input.Keys.F9:
                return "F9";
            case Input.Keys.F10:
                return "F10";
            case Input.Keys.F11:
                return "F11";
            case Input.Keys.F12:
                return "F12";

            // Numpad
            case Input.Keys.NUMPAD_0:
                return "N0";
            case Input.Keys.NUMPAD_1:
                return "N1";
            case Input.Keys.NUMPAD_2:
                return "N2";
            case Input.Keys.NUMPAD_3:
                return "N3";
            case Input.Keys.NUMPAD_4:
                return "N4";
            case Input.Keys.NUMPAD_5:
                return "N5";
            case Input.Keys.NUMPAD_6:
                return "N6";
            case Input.Keys.NUMPAD_7:
                return "N7";
            case Input.Keys.NUMPAD_8:
                return "N8";
            case Input.Keys.NUMPAD_9:
                return "N9";

            // Symbols (use ASCII-safe names)
            case Input.Keys.PLUS:
                return "+";
            case Input.Keys.MINUS:
                return "-";
            case Input.Keys.SLASH:
                return "/";
            case Input.Keys.BACKSLASH:
                return "\\";
            case Input.Keys.COMMA:
                return ",";
            case Input.Keys.PERIOD:
                return ".";
            case Input.Keys.SEMICOLON:
                return ";";
            case Input.Keys.APOSTROPHE:
                return "'";
            case Input.Keys.GRAVE:
                return "`";
            case Input.Keys.EQUALS:
                return "=";
            case Input.Keys.LEFT_BRACKET:
                return "[";
            case Input.Keys.RIGHT_BRACKET:
                return "]";
            case Input.Keys.CAPS_LOCK:
                return "CAP";

            default:
                return "???";
        }
    }
}


