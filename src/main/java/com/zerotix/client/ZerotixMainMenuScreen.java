package com.zerotix.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.multiplayer.ServerData;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;

public class ZerotixMainMenuScreen extends Screen {
    private static final ITextComponent TITLE = new TranslationTextComponent("screen.zerotix.title");
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    public ZerotixMainMenuScreen() {
        super(TITLE);
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = this.height / 2 - 10;

        // Play (auto-connect)
        this.addButton(new Button(centerX - 100, y - 10, 200, 20, new TranslationTextComponent("screen.zerotix.play"), (b) -> {
            ServerData data = new ServerData("Zerotix", "mc.zerotix.fun", false);
            // Custom minimal connecting screen
            Minecraft.getInstance().setScreen(new ZerotixConnectingScreen(this, Minecraft.getInstance(), data));
        }));

        // Options
        this.addButton(new Button(centerX - 100, y + 20, 200, 20, new TranslationTextComponent("screen.zerotix.options"), (b) -> {
            Minecraft.getInstance().setScreen(new OptionsScreen(this, Minecraft.getInstance().options));
        }));

        // Quit
        this.addButton(new Button(centerX - 100, y + 50, 200, 20, new TranslationTextComponent("screen.zerotix.quit"), (b) -> {
            Minecraft.getInstance().stop();
        }));
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        // draw vanilla panorama background
        this.renderBackground(ms);
        // darken slightly for readability
        Minecraft.getInstance().getTextureManager().bind(PANORAMA_OVERLAY);
        blit(ms, 0, 0, 0, 0, this.width, this.height, 16, 128);

        super.render(ms, mouseX, mouseY, partialTicks);
        // Draw "Zerotix" top-left large
        drawCenteredString(ms, this.font, TITLE, this.width / 2, 40, 0xFFFFFF);
    }
}