package net.mcthunder.VillagesArise;

/**
 * Created by Lukario45 on 2/24/2023.
 */

//Add Mod Related Importes
import com.mojang.logging.LogUtils;
import net.mcthunder.VillagesArise.blocks.TownHallBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
@Mod(VillagesArise.MODID)
public class VillagesArise {

    public static final String MODID = "villagesarise";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> TOWN_HALL_BLOCK = BLOCKS.register("town_hall_block", () -> new TownHallBlock(BlockBehaviour.Properties.of(Material.DIRT)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> TOWN_HALL_BLOCK_ITEM = ITEMS.register("town_hall_block", () -> new BlockItem(TOWN_HALL_BLOCK.get(), new Item.Properties()));

    public VillagesArise()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("Town Hall Block >> {}", TOWN_HALL_BLOCK);
    }
    //Create Creative Tab

    private void addCreative(CreativeModeTabEvent.Register event){
        //LOGGER.debug("LUKA");
        event.registerCreativeModeTab(new ResourceLocation(MODID, "villagesarise"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + MODID + ".Villages"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(Items.EMERALD))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(new ItemStack(TOWN_HALL_BLOCK_ITEM.get()));
                            // populator.accept(TOWN_HALL_BLOCK.get());
                        })
        );
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent

    /**@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class ModEvents{
        @SubscribeEvent
        public void onCreativeTabRegistry(CreativeModeTabEvent.Register event) {
            LOGGER.debug("LUKA");
            event.registerCreativeModeTab(new ResourceLocation(MODID, "VillagesArise"), builder ->
                    // Set name of tab to display
                    builder.title(Component.translatable("item_group." + MODID + ".Villages"))
                            // Set icon of creative tab
                            .icon(() -> new ItemStack(Items.EMERALD))
                            // Add default items to tab
                            .displayItems((enabledFlags, populator, hasPermissions) -> {
                                populator.accept(new ItemStack(TOWN_HALL_BLOCK_ITEM.get()));
                                // populator.accept(TOWN_HALL_BLOCK.get());
                            })
            );
        }
    }*/
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
