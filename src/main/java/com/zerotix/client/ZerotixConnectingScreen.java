package com.zerotix.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.ITextComponent;

public class ZerotixConnectingScreen extends Screen {
    private final Screen previous;
    private final ServerData serverData;
    private ConnectScreen internal;

    public ZerotixConnectingScreen(Screen prev, Minecraft mc, ServerData data) {
        super(new TranslationTextComponent(""));
        this.previous = prev;
        this.serverData = data;
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 100, this.height - 40, 200, 20, new TranslationTextComponent("screen.zerotix.cancel"), (b) -> {
            if (internal != null) internal.cancel();
            this.minecraft.setScreen(previous);
        }));
        // start connecting
        this.internal = new ConnectScreen(this, this.minecraft, this.minecraft.getConnection(), this.serverData);
    }

    @Override
    public void tick() {
        if (internal != null) internal.tick();
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        // no other text
    }
}