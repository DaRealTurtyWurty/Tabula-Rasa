package dev.turtywurty.tabularasa;

import dev.turtywurty.tabularasa.screen.ScreenStack;
import dev.turtywurty.tabularasa.screen.TRScreen;
import net.fabricmc.api.ClientModInitializer;

public class TabulaRasaClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

	}

	public static void openScreen(TRScreen screen) {
		ScreenStack.pushScreen(screen);
	}

	public static boolean isTRScreenOpen() {
		return ScreenStack.getCurrentInstance() != null;
	}
}