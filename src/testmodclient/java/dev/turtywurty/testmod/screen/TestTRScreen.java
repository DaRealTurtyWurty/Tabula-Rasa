package dev.turtywurty.testmod.screen;

import dev.turtywurty.tabularasa.screen.TRScreen;
import dev.turtywurty.tabularasa.ui.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;

public class TestTRScreen extends TRScreen {
    @Override
    protected void constructPanels() {
        var layout = new TRHorizontalLayout()
                .setPadding(Padding.all(5))
                .setSpacing(10)
                .setInverted(false);

        RandomSource random = this.minecraft.level.getRandom();
        for (int i = 0; i < 2; i++) {
            var column = new TRVerticalLayout()
                    .setPadding(Padding.of(10, 5))
                    .setSpacing(5)
                    .setInverted(false);
            for (int j = 0; j < 5; j++) {
                column.addChild(new TRTextWidget(
                        Component.literal("Text " + (i * 5 + j + 1)),
                        random.nextInt(0x1000000) | 0xFF000000,
                        false));
            }

            layout.addChild(column);
        }

        TRPanel centrePanel = addPanelCentre(
                layout,
                centerX(), centerY(),
                200, 100,
                Component.literal("Test TR Screen"));

        addPanel(
                new TRVerticalLayout(),
                centrePanel.getX() + centrePanel.getWidth() + 10, centrePanel.getY(),
                100, 100,
                Component.literal("Right Panel")
        );
    }
}
