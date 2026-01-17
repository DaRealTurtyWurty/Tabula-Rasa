package dev.turtywurty.testmod;

import dev.turtywurty.tabularasa.TabulaRasaClient;
import dev.turtywurty.testmod.screen.TestTRScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class TestModClient implements ClientModInitializer {
    private static final KeyMapping TEST_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.testmod.test_key",
            GLFW.GLFW_KEY_F9,
            new KeyMapping.Category(TestMod.id("category"))
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(_ -> {
            if (TabulaRasaClient.isTRScreenOpen())
                return;

            boolean pressed = false;
            while (TEST_KEY.consumeClick()) {
                pressed = true;
            }

            if (pressed) {
                TabulaRasaClient.openScreen(new TestTRScreen());
            }
        });
    }
}