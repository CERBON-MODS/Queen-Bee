package com.cerbon.queen_bee.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class StingerToBeesLootTableAdditionModifier extends LootModifier {
    private final Item addition;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected StingerToBeesLootTableAdditionModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(addition, 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<StingerToBeesLootTableAdditionModifier>{

        @Override
        public StingerToBeesLootTableAdditionModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new StingerToBeesLootTableAdditionModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(StingerToBeesLootTableAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.addition)).toString());
            return json;
        }
    }
}