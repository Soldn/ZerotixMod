package com.zerotix;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;

@Mod("zerotix")
@EventBusSubscriber(modid = "zerotix", bus = Bus.FORGE, value = Dist.CLIENT)
public class ZerotixMod {

    public ZerotixMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                Minecraft mc = Minecraft.getInstance();
                mc.getWindow().setTitle("Zerotix");
            } catch (Throwable t) {
                // ignore if not ready
            }
        });
    }

    @SubscribeEvent
    public static void onInitMain(GuiScreenEvent.InitGuiEvent.Post e) {
        if (e.getGui() instanceof MainMenuScreen) {
            Minecraft.getInstance().setScreen(new com.zerotix.client.ZerotixMainMenuScreen());
        }
    }

    @SubscribeEvent
    public static void onOpenGui(GuiOpenEvent e) {
        if (e.getGui() instanceof DisconnectedScreen) {
            // wrap to our custom screen
            DisconnectedScreen d = (DisconnectedScreen) e.getGui();
            e.setGui(new com.zerotix.client.ZerotixDisconnectedScreen(d));
        }
    }
}