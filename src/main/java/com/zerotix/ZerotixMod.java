package com.zerotixmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("zerotixmod")
public class ZerotixMod {
    public static final String MODID = "zerotixmod";

    public ZerotixMod() {
        // Конструктор мода
    }

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        System.out.println(">>> ZerotixMod загружен на сервере!");
    }

    @SubscribeEvent
    public void doClientStuff(FMLClientSetupEvent event) {
        System.out.println(">>> ZerotixMod загружен на клиенте!");
    }
}
