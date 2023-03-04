package net.mcthunder.VillagesArise;

import net.mcthunder.VillagesArise.blocks.TownHallBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Lukario45 on 2/25/2023.
 * Class to handle all registrations within this MOD
 */
public class VillageRegister {
    /**
     * Deferred Registers
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, VillagesArise.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, VillagesArise.MODID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister
            .create(ForgeRegistries.MENU_TYPES, VillagesArise.MODID);
    /**
     * Block Registry Objects
     */
    public static final RegistryObject<Block> TOWN_HALL_BLOCK = BLOCKS
            .register("town_hall_block.json", () -> new TownHallBlock(BlockBehaviour.Properties.of(Material.DIRT)));
    /**
     * Item Registry Objects
     */
    public static final RegistryObject<Item> TOWN_HALL_BLOCK_ITEM = ITEMS
            .register("town_hall_block_item", () -> new BlockItem(TOWN_HALL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_COIN_ITEM = ITEMS
            .register("emerald_coin", () -> new Item(new Item.Properties()));
    /** TEMPLATE FOR ITEMS
     public static final RegistryObject<Item>  = ITEMS
            .register(" ", () -> new Item(new Item.Properties()));
        END **/
    /**
     * Menu Registry Objects
     */
    public VillageRegister(){

    }
    public void register(IEventBus eventBus){


        // Register the commonSetup method for modloading
        //modEventBus.addListener(this::commonSetup);
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(eventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
        eventBus.addListener(this::registerCreative);

    }

    private  void registerCreative(CreativeModeTabEvent.Register event){
        //LOGGER.debug("LUKA");
        event.registerCreativeModeTab(new ResourceLocation(VillagesArise.MODID, "villagesarise"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + VillagesArise.MODID + ".Villages"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(Items.EMERALD))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(new ItemStack(VillageRegister.TOWN_HALL_BLOCK_ITEM.get()));
                            populator.accept(new ItemStack(VillageRegister.EMERALD_COIN_ITEM.get()));
                        })
        );
    }
}
