package com.cerbon.queen_bee.event;

import com.cerbon.queen_bee.QueenBeeMod;
import com.cerbon.queen_bee.client.item.renderer.AntennaArmorRenderer;
import com.cerbon.queen_bee.entity.QueenBeeModEntities;
import com.cerbon.queen_bee.entity.custom.QueenBeeEntity;
import com.cerbon.queen_bee.item.custom.AntennaArmorItem;
import com.cerbon.queen_bee.loot.StingerToBeesLootTableAdditionModifier;
import com.cerbon.queen_bee.recipe.brewing.StingerToPoisonPotionBrewingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = QueenBeeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class QueenBeeModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(QueenBeeModEntities.QUEEN_BEE.get(), QueenBeeEntity.setAttribute());
    }
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(()-> BrewingRecipeRegistry.addRecipe(new StingerToPoisonPotionBrewingRecipe()));
    }
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(AntennaArmorItem.class, AntennaArmorRenderer::new);
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event){
        event.getRegistry().register(new StingerToBeesLootTableAdditionModifier.Serializer().setRegistryName(
                new ResourceLocation(QueenBeeMod.MOD_ID, "add_stinger_drop_to_bee_loot_table.json")
        ));
    }
}
