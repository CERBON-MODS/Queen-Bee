package com.cerbon.queen_bee.client.entity.model;

import com.cerbon.queen_bee.QueenBeeMod;
import com.cerbon.queen_bee.entity.custom.QueenBeeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QueenBeeModel extends AnimatedGeoModel<QueenBeeEntity> {
    @Override
    public ResourceLocation getModelLocation(QueenBeeEntity animatable) {
        return new ResourceLocation(QueenBeeMod.MOD_ID, "geo/queen_bee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(QueenBeeEntity animatable) {
        return new ResourceLocation(QueenBeeMod.MOD_ID, "textures/entity/queen_bee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(QueenBeeEntity animatable) {
        return new ResourceLocation(QueenBeeMod.MOD_ID, "animations/queen_bee.animation.json");
    }
}
