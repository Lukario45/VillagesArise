package net.mcthunder.VillagesArise;

import net.mcthunder.VillagesArise.blocks.TownHallBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Lukario45 on 2/25/2023.
 * Class to handle all registrations within this MOD
 */
public class VillageRegister {
    // Deferred Register for BLOCKS
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, VillagesArise.MODID);
    // Deferred Register for ITEMS
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, VillagesArise.MODID);
    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> TOWN_HALL_BLOCK = BLOCKS
            .register("town_hall_block", () -> new TownHallBlock(BlockBehaviour.Properties.of(Material.DIRT)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> TOWN_HALL_BLOCK_ITEM = ITEMS
            .register("town_hall_block", () -> new BlockItem(TOWN_HALL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_COIN_ITEM = ITEMS
            .register("emerald_coin", () -> new Item(new Item.Properties()));
    /** TEMPLATE FOR ITEMS
     public static final RegistryObject<Item>  = ITEMS
            .register(" ", () -> new Item(new Item.Properties()));
        END **/
    public VillageRegister(){

    }
    public void register(IEventBus eventBus){
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(eventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(eventBus);

    }
}
