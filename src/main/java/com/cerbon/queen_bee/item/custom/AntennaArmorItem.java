package com.cerbon.queen_bee.item.custom;

import com.cerbon.queen_bee.QueenBeeMod;
import com.cerbon.queen_bee.config.QueenBeeModCommonConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class AntennaArmorItem extends GeoArmorItem implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public AntennaArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (QueenBeeModCommonConfigs.ENABLE_ANTENNA.get() && QueenBeeModCommonConfigs.ENABLE_ANTENNA_TOOLTIP.get()){
            if (Screen.hasShiftDown()){
                pTooltipComponents.add(new TranslatableComponent("tooltip." + QueenBeeMod.MOD_ID + ".antenna").withStyle(ChatFormatting.YELLOW));
            }else {
                pTooltipComponents.add(new TranslatableComponent("tooltip." + QueenBeeMod.MOD_ID + ".item.shift_up").withStyle(ChatFormatting.YELLOW));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
     event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
     return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
