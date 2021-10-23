package io.github.professorpiggos.fabricnukes;

import io.github.professorpiggos.fabricnukes.entity.WeezerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FabricNukes implements ModInitializer {
    public static final Item GUN = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final EntityType<WeezerEntity> WEEZER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("fabricnukes", "weezer"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WeezerEntity::new).dimensions(EntityDimensions.fixed(2f,2f)).build()
    );
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("fabricnukes", "gun"), GUN);
    }
}
