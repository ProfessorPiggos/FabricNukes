package io.github.professorpiggos.fabricnukes;

import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.MissileLaunchpad;
import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.MissileLaunchpadEntity;
import io.github.professorpiggos.fabricnukes.block.missilelaunchpad.gui.LaunchpadGui;
import io.github.professorpiggos.fabricnukes.entity.missiles.blackyellow.BlackYellowMissileEntity;
import io.github.professorpiggos.fabricnukes.entity.missiles.jonarbuckle.JonArbuckleMissileEntity;
import io.github.professorpiggos.fabricnukes.entity.weezer.WeezerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

public class FabricNukes implements ModInitializer {
    public static ScreenHandlerType<LaunchpadGui> SCREEN_HANDLER_TYPE;
    public static final Item GUN = new Item(new FabricItemSettings());
    public static final ItemGroup FABRIC_NUKES_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("fabricnukes", "main"))
            .icon(() -> new ItemStack(GUN))
            .appendItems(stacks -> stacks.add(new ItemStack(GUN)))
            .build();
    public static final Item FOOD_FOR_THOUGHT = new Item(new FabricItemSettings()
            .group(FABRIC_NUKES_GROUP)
            .rarity(Rarity.RARE)
            .food(new FoodComponent.Builder()
                    .hunger(20)
                    .saturationModifier(1.24F)
                    .meat()
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final MissileLaunchpad MISSILE_LAUNCHPAD = new MissileLaunchpad(
            FabricBlockSettings.of(Material.METAL)
                    .requiresTool()
                    .nonOpaque()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
    );
    public static BlockEntityType<MissileLaunchpadEntity> MISSILE_LAUNCHPAD_ENTITY;
    public static final EntityType<WeezerEntity> WEEZER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("fabricnukes", "weezermob"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WeezerEntity::new)
                    .dimensions(EntityDimensions.fixed(3.1f, 2.25f))
                    .build()
    );
    public static final EntityType<BlackYellowMissileEntity> BLACK_YELLOW_MISSILE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("fabricnukes", "blackyellowmissile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, BlackYellowMissileEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 5f))
                    .build()
    );
    public static final EntityType<JonArbuckleMissileEntity> JON_ARBUCKLE_MISSILE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("fabricnukes", "jonarbucklemissile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, JonArbuckleMissileEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 5f))
                    .build()
    );

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onInitialize() {
        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();
        SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(
                MissileLaunchpad.ID,
                (syncId, inventory) -> new LaunchpadGui(syncId, inventory, ScreenHandlerContext.EMPTY)
        );
        Registry.register(Registry.ITEM, new Identifier("fabricnukes", "gun"), GUN);
        Registry.register(Registry.ITEM, new Identifier("fabricnukes", "food_for_thought"), FOOD_FOR_THOUGHT);
        Registry.register(Registry.BLOCK, MissileLaunchpad.ID, MISSILE_LAUNCHPAD);
        MISSILE_LAUNCHPAD_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                "fabricnukes:missile_launchpad_entity",
                FabricBlockEntityTypeBuilder.create(MissileLaunchpadEntity::new, MISSILE_LAUNCHPAD)
                        .build(null));
        Registry.register(Registry.ITEM, MissileLaunchpad.ID, new BlockItem(MISSILE_LAUNCHPAD, new FabricItemSettings().group(FABRIC_NUKES_GROUP)));
        FabricDefaultAttributeRegistry.register(WEEZER, WeezerEntity.weezerDefaultAttributes());
        SpawnRestrictionAccessor.callRegister(WEEZER, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canMobSpawn);
        BiomeModifications.addSpawn(context -> true, SpawnGroup.MONSTER, WEEZER, 15, 1, 1);
        FabricDefaultAttributeRegistry.register(JON_ARBUCKLE_MISSILE, JonArbuckleMissileEntity.genericExplodingMissileDefaultAttributes());
        FabricDefaultAttributeRegistry.register(BLACK_YELLOW_MISSILE, BlackYellowMissileEntity.genericExplodingMissileDefaultAttributes());
    }
}
