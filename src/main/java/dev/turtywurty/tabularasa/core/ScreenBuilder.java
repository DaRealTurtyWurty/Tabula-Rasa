package dev.turtywurty.tabularasa.core;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

public class ScreenBuilder {
    private Component title = Component.empty();
    private ContainerScreenBuilder<?> containerScreenBuilder;

    public ScreenBuilder title(@NotNull Component title) {
        if (title == null)
            throw new NullPointerException("title cannot be null!");

        this.title = title;
        return this;
    }

    public <T extends AbstractContainerMenu> ContainerScreenBuilder<T> containerScreen(@NotNull T containerMenu, @NotNull Inventory playerInventory) {
        if (containerMenu == null)
            throw new NullPointerException("containerMenu cannot be null!");
        if (playerInventory == null)
            throw new NullPointerException("playerInventory cannot be null!");

        this.containerScreenBuilder = new ContainerScreenBuilder<>(containerMenu, playerInventory);
        return (ContainerScreenBuilder<T>) this.containerScreenBuilder;
    }

    public class ContainerScreenBuilder<T extends AbstractContainerMenu> {
        private final T containerMenu;
        private final Inventory playerInventory;

        public ContainerScreenBuilder(T containerMenu, Inventory playerInventory) {
            this.containerMenu = containerMenu;
            this.playerInventory = playerInventory;
        }

        public ScreenBuilder build() {
            return ScreenBuilder.this;
        }
    }
}
