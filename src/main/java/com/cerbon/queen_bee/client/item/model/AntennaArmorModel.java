package com.cerbon.queen_bee.client.item.model;

import com.cerbon.queen_bee.QueenBeeMod;
import com.cerbon.queen_bee.item.custom.AntennaArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AntennaArmorModel extends AnimatedGeoModel<AntennaArmorItem> {
    @Override
    public ResourceLocation getModelResource(AntennaArmorItem animatable) {
        return new ResourceLocation(QueenBeeMod.MOD_ID, "geo/antenna.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AntennaArmorItem animatable) {
        return new ResourceLocation(QueenBeeMod.MOD_ID, "textures/armor/antenna.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AntennaArmorItem animatable) {
        return new ResourceLocation(QueenBeeMod.MOD_ID, "animations/antenna.animation.json");
    }
}
