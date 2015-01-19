package it.seat.milf.components;

import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;

public class KeyCodeSelector {
	/* spacing characters */
	public final static int KEY_BACKSPACE = -8;
	public final static int KEY_RETURN = -5;
	public final static int KEY_SPACE = 32;

	/* special characters */
	private final static int KEY_EXCLAMATION = 33;
//	private final static int KEY_QUOTE = 34;
//	private final static int KEY_DOLLAR = 36;
//	private final static int KEY_PERCENT = 37;
//	private final static int KEY_AMPERSAND = 38;
	private final static int KEY_APOSTROPHE = 39;
	private final static int KEY_OPEN_PARENTHESIS = 40;
	private final static int KEY_CLOSE_PARENTHESIS = 41;
	private final static int KEY_PLUS = 43;
	private final static int KEY_COMMA = 44;
	private final static int KEY_DASH = 45;
	private final static int KEY_PERIOD = 46;
	private final static int KEY_SLASH = 47;
	private final static int KEY_COLON = 58;
	private final static int KEY_SEMICOLON = 59;
	private final static int KEY_QUESTION = 63;
	private final static int KEY_AT = 64;
//	private final static int KEY_UNDERSCORE = 95;

	/* capital letters */
//	private final static int KEY_CAPITAL_A = 65;
//	private final static int KEY_CAPITAL_B = 66;
//	private final static int KEY_CAPITAL_C = 67;
//	private final static int KEY_CAPITAL_D = 68;
//	private final static int KEY_CAPITAL_E = 69;
//	private final static int KEY_CAPITAL_F = 70;
//	private final static int KEY_CAPITAL_G = 71;
//	private final static int KEY_CAPITAL_H = 72;
//	private final static int KEY_CAPITAL_I = 73;
//	private final static int KEY_CAPITAL_J = 74;
//	private final static int KEY_CAPITAL_K = 75;
//	private final static int KEY_CAPITAL_L = 76;
//	private final static int KEY_CAPITAL_M = 77;
//	private final static int KEY_CAPITAL_N = 78;
//	private final static int KEY_CAPITAL_O = 79;
//	private final static int KEY_CAPITAL_P = 80;
//	private final static int KEY_CAPITAL_Q = 81;
//	private final static int KEY_CAPITAL_R = 82;
//	private final static int KEY_CAPITAL_S = 83;
//	private final static int KEY_CAPITAL_T = 84;
//	private final static int KEY_CAPITAL_U = 85;
//	private final static int KEY_CAPITAL_V = 86;
//	private final static int KEY_CAPITAL_W = 87;
//	private final static int KEY_CAPITAL_X = 88;
//	private final static int KEY_CAPITAL_Y = 89;
//	private final static int KEY_CAPITAL_Z = 90;

	/* common letters */
	private final static int KEY_A = 97;
	private final static int KEY_B = 98;
	private final static int KEY_C = 99;
	private final static int KEY_D = 100;
	private final static int KEY_E = 101;
	private final static int KEY_F = 102;
	private final static int KEY_G = 103;
	private final static int KEY_H = 104;
	private final static int KEY_I = 105;
	private final static int KEY_J = 106;
	private final static int KEY_K = 107;
	private final static int KEY_L = 108;
	private final static int KEY_M = 109;
	private final static int KEY_N = 110;
	private final static int KEY_O = 111;
	private final static int KEY_P = 112;
	private final static int KEY_Q = 113;
	private final static int KEY_R = 114;
	private final static int KEY_S = 115;
	private final static int KEY_T = 116;
	private final static int KEY_U = 117;
	private final static int KEY_V = 118;
	private final static int KEY_W = 119;
	private final static int KEY_X = 120;
	private final static int KEY_Y = 121;
	private final static int KEY_Z = 122;

	/* letters with accents */
//	private final static int KEY_CAPITAL_AGRAVE = 192;
//	private final static int KEY_CAPITAL_EGRAVE = 200;
	private final static int KEY_AGRAVE = 224;
	private final static int KEY_AACUTE = 225;
	private final static int KEY_EGRAVE = 232;
	private final static int KEY_EACUTE = 233;
	private final static int KEY_IGRAVE = 236;
	private final static int KEY_OGRAVE = 242;
	private final static int KEY_OACUTE = 243;
	private final static int KEY_UGRAVE = 249;

	private static KeyCodeSelector singleton;
	private final Hashtable keyCodes;
	private int i;

	static KeyCodeSelector getInstance() {
		if (singleton == null) {
			singleton = new KeyCodeSelector();
		}
		return singleton;
	}

	private KeyCodeSelector() {
		keyCodes = new Hashtable();
		keyCodes.put(new Integer(Canvas.KEY_NUM1), new int[] { Canvas.KEY_NUM1,
				KEY_PERIOD, KEY_COMMA, KEY_DASH, KEY_QUESTION, KEY_EXCLAMATION,
				KEY_APOSTROPHE, KEY_AT, KEY_COLON, KEY_SEMICOLON, KEY_SLASH,
				KEY_OPEN_PARENTHESIS, KEY_CLOSE_PARENTHESIS, KEY_PLUS });
		keyCodes.put(new Integer(Canvas.KEY_NUM2), new int[] { Canvas.KEY_NUM2,
				KEY_A, KEY_B, KEY_C, KEY_AGRAVE, KEY_AACUTE });
		keyCodes.put(new Integer(Canvas.KEY_NUM3), new int[] { Canvas.KEY_NUM3,
				KEY_D, KEY_E, KEY_F, KEY_EGRAVE, KEY_EACUTE });
		keyCodes.put(new Integer(Canvas.KEY_NUM4), new int[] { Canvas.KEY_NUM4,
				KEY_G, KEY_H, KEY_I, KEY_IGRAVE });
		keyCodes.put(new Integer(Canvas.KEY_NUM5), new int[] { Canvas.KEY_NUM5,
				KEY_J, KEY_K, KEY_L });
		keyCodes.put(new Integer(Canvas.KEY_NUM6), new int[] { Canvas.KEY_NUM6,
				KEY_M, KEY_N, KEY_O, KEY_OGRAVE, KEY_OACUTE });
		keyCodes.put(new Integer(Canvas.KEY_NUM7), new int[] { Canvas.KEY_NUM7,
				KEY_P, KEY_Q, KEY_R, KEY_S });
		keyCodes.put(new Integer(Canvas.KEY_NUM8), new int[] { Canvas.KEY_NUM8,
				KEY_T, KEY_U, KEY_V, KEY_UGRAVE });
		keyCodes.put(new Integer(Canvas.KEY_NUM9), new int[] { Canvas.KEY_NUM9,
				KEY_W, KEY_X, KEY_Y, KEY_Z });
		keyCodes.put(new Integer(Canvas.KEY_NUM0), new int[] { Canvas.KEY_NUM0,
				KEY_SPACE });
		keyCodes.put(new Integer(Canvas.KEY_STAR),
				new int[] { Canvas.KEY_STAR });
		keyCodes.put(new Integer(Canvas.KEY_POUND),
				new int[] { Canvas.KEY_POUND });
		i = 0;
	}

	int getKey(int keyCode) {
		int[] keys = (int[]) keyCodes.get(new Integer(keyCode));
		return keys[i];
	}

	int getKey(int keyCode, int direction) {
		int[] keys = (int[]) keyCodes.get(new Integer(keyCode));
		i += direction;
		if (i < 0) {
			i = keys.length - 1;
		} else if (i >= keys.length) {
			i = 0;
		}
		return keys[i];
	}

	public void reset() {
		i = 0;
	}
}
