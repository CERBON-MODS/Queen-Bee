package com.cerbon.queen_bee;

import com.cerbon.queen_bee.client.entity.renderer.QueenBeeRenderer;
import com.cerbon.queen_bee.config.QueenBeeModCommonConfigs;
import com.cerbon.queen_bee.entity.QueenBeeModEntities;
import com.cerbon.queen_bee.item.QueenBeeModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(QueenBeeMod.MOD_ID)
public class QueenBeeMod
{
    public static final String MOD_ID = "queen_bee";
    private static final Logger LOGGER = LogUtils.getLogger();

    public QueenBeeMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        QueenBeeModItems.register(modEventBus);
        QueenBeeModEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, QueenBeeModCommonConfigs.SPEC, "queen_bee.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(QueenBeeModEntities.QUEEN_BEE.get(), QueenBeeRenderer::new);
        }
    }
}
