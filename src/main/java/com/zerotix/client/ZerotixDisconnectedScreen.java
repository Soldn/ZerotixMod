package com.zerotix.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ZerotixDisconnectedScreen extends Screen {
    private final DisconnectedScreen vanilla;
    public ZerotixDisconnectedScreen(DisconnectedScreen original) {
        super(new TranslationTextComponent("screen.zerotix.title"));
        this.vanilla = original;
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 100, this.height - 40, 200, 20,
                new TranslationTextComponent("screen.zerotix.cancel"),
                (b) -> this.minecraft.setScreen(vanilla.getParent())));
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        int top = this.height / 2 - 40;
        drawCenteredString(ms, this.font, new TranslationTextComponent("screen.zerotix.title"), this.width/2, top, 0xFFFFFF);
        ITextComponent reason = vanilla.getReason();
        this.font.drawWordWrap(reason, this.width/2 - 150, top + 20, 300, 0xDDDDDD);
        super.render(ms, mouseX, mouseY, partialTicks);
    }
}