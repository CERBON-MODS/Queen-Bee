package com.cerbon.queen_bee.item.custom;

import com.cerbon.queen_bee.config.QBCommonConfigs;
import com.cerbon.queen_bee.item.QBItems;
import com.cerbon.queen_bee.util.IBeeEntityMixin;
import com.cerbon.queen_bee.util.QBConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StingerSwordItem extends SwordItem {
    public StingerSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(new Tier() {
            @Override
            public int getUses() {
                return 250;
            }

            @Override
            public float getSpeed() {
                return 2.0F;
            }

            @Override
            public float getAttackDamageBonus() {
                return 0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getEnchantmentValue() {
                return 12;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(QBItems.STINGER.get()));
            }

        }, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (QBCommonConfigs.ENABLE_CURE_BEE.get() && QBCommonConfigs.ENABLE_STINGER_SWORD_TOOLTIP.get()) pTooltipComponents.add(Component.translatable(Screen.hasShiftDown() ? QBConstants.STINGER_SWORD_SHIFT_DOWN_TOOLTIP : QBConstants.ITEM_SHIFT_UP_TOOLTIP).withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        if (QBCommonConfigs.ENABLE_POISON_EFFECT.get()) pTarget.addEffect(new MobEffectInstance(MobEffects.POISON, QBCommonConfigs.STINGER_SWORD_POISON_EFFECT_DURATION.get(), QBCommonConfigs.STINGER_SWORD_POISON_EFFECT_AMPLIFIER.get()));
        if (QBCommonConfigs.ENABLE_NAUSEA_EFFECT.get()) pTarget.addEffect(new MobEffectInstance(MobEffects.CONFUSION, QBCommonConfigs.NAUSEA_EFFECT_DURATION.get(), 0));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (QBCommonConfigs.ENABLE_CURE_BEE.get() && pPlayer.isCrouching() && pPlayer.getItemBySlot(EquipmentSlot.HEAD).is(QBItems.ANTENNA.get())) {

            Bee bee = new Bee(EntityType.BEE, pLevel);
            bee.moveTo(pPlayer.getX(), pPlayer.getY() + 1, pPlayer.getZ());
            ((IBeeEntityMixin)(bee)).queen_bee_setDespawnTime(QBCommonConfigs.REGENERATION_AREA_EFFECT_CLOUD_DURATION.get());
            ((IBeeEntityMixin)(bee)).queen_bee_setCanDespawn(true);
            bee.setInvulnerable(true);
            bee.setNoAi(true);

            AreaEffectCloud areaEffectCloud = new AreaEffectCloud(pLevel, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ());
            areaEffectCloud.addEffect(new MobEffectInstance(MobEffects.REGENERATION, QBCommonConfigs.REGENERATION_EFFECT_DURATION.get(), QBCommonConfigs.REGENERATION_EFFECT_AMPLIFIER.get()));
            areaEffectCloud.setDuration(QBCommonConfigs.REGENERATION_AREA_EFFECT_CLOUD_DURATION.get());
            areaEffectCloud.setRadius(QBCommonConfigs.REGENERATION_AREA_EFFECT_CLOUD_RADIUS.get());
            areaEffectCloud.setFixedColor(PotionUtils.getColor(Potions.REGENERATION));
            areaEffectCloud.setOwner(pPlayer);

            pLevel.addFreshEntity(bee);
            pLevel.addFreshEntity(areaEffectCloud);

            pPlayer.swing(pUsedHand);
            pPlayer.getCooldowns().addCooldown(this, QBCommonConfigs.STINGER_SWORD_COOLDOWN.get());

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
