package be.digitalia.fosdem.model;

import android.text.TextUtils;

public enum Building {
	J, K, H, U, AW, Unknown;

	public static Building fromRoomName(String roomName) {
		if (!TextUtils.isEmpty(roomName)) {
			switch (Character.toUpperCase(roomName.charAt(0))) {
				case 'J':
					return J;
				case 'K':
					return K;
				case 'H':
					return H;
				case 'U':
					return U;
			}
			if (roomName.regionMatches(true, 0, "AW", 0, 2)) {
				return AW;
			}
			if ("Janson".equalsIgnoreCase(roomName)) {
				return J;
			}
			if ("Ferrer".equalsIgnoreCase(roomName)) {
				return H;
			}
			if ("Chavanne".equalsIgnoreCase(roomName) || "Lameere".equalsIgnoreCase(roomName) || "Guillissen".equalsIgnoreCase(roomName)) {
				return U;
			}
		}

		return Unknown;
	}
}
