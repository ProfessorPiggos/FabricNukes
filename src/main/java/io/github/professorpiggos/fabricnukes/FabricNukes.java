package io.github.professorpiggos.fabricnukes;

import io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow.BlackYellowMissileEntity;
import io.github.professorpiggos.fabricnukes.entity.weezer.WeezerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

public class FabricNukes implements ModInitializer {
    public static final Item GUN = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final EntityType<WeezerEntity> WEEZER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("fabricnukes", "weezermob"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WeezerEntity::new).dimensions(EntityDimensions.fixed(3.1f, 2.25f)).build()
    );
    public static final EntityType<BlackYellowMissileEntity> BLACK_YELLOW_MISSILE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("fabricnukes", "blackyellowmissile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, BlackYellowMissileEntity::new).dimensions(EntityDimensions.fixed(1f, 5f)).build()
    );

    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        GeckoLibMod.DISABLE_IN_DEV = true;
        Registry.register(Registry.ITEM, new Identifier("fabricnukes", "gun"), GUN);
        FabricDefaultAttributeRegistry.register(WEEZER, WeezerEntity.weezerDefaultAttributes());
        FabricDefaultAttributeRegistry.register(BLACK_YELLOW_MISSILE, BlackYellowMissileEntity.blackYellowMissileDefaultAttributes());
    }
}
