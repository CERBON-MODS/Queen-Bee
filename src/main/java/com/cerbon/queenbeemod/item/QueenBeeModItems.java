package com.cerbon.queenbeemod.item;

import com.cerbon.queenbeemod.QueenBeeMod;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class QueenBeeModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, QueenBeeMod.MOD_ID);

    public static final RegistryObject<Item> STINGER = ITEMS.register("stinger",
            () -> new Item(new Item.Properties().food(QueenBeeModFoods.STINGER)));

    public static final RegistryObject<Item> BEE_ANTENNA = ITEMS.register("bee_antenna",
            () -> new ArmorItem(QueenBeeModArmorMaterials.BEE_ANTENNA, ArmorItem.Type.HELMET, new Item.Properties()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
