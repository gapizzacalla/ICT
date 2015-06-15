package ict;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ict.block.BlockICT;
import ict.event.EventsHandler;
import ict.handler.ConfigurationHandler;
import ict.handler.PacketHandler;
import ict.helper.LogHelper;
import ict.proxy.CommonProxy;
import ict.proxy.IProxy;
import ict.tileentity.TileEntityICT;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ICT.MOD_ID, name = ICT.MOD_NAME, version = ICT.VERSION, guiFactory = ICT.GUI_FACTORY_CLASS)
public class ICT
{
    public static final String MOD_ID = "ict";
    public static final String MOD_NAME = "Interactive Crafting Table";
    public static final String VERSION = "1.7.10-1.1.1";
    public static final String CONFIG_NAME = MOD_NAME;
    public static final String CLIENT_PROXY_CLASS = "ict.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "ict.proxy.ServerProxy";
    public static final String GUI_FACTORY_CLASS = "ict.client.GuiFactory";

    @Instance(MOD_ID)
    public static ICT instance;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS, modId = MOD_ID)
    public static CommonProxy commonProxy;
    public static IProxy proxy;
    public static final PacketHandler packetHandler = new PacketHandler();
    public static BlockICT ict = new BlockICT(Material.wood);

    /**
     * Put Network Handling, Mod Configurations, Register Blocks/Items
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        GameRegistry.registerBlock(ict, ict.getName());
        LogHelper.info("PreInitialization Completed");
    }

    /**
     * Put GUI, TileEntity, Crafting, Events
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PacketHandler.registerPackets();
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
        GameRegistry.registerTileEntity(TileEntityICT.class, MOD_ID + "tile" + ict.getName());
        registerRecipes();
        LogHelper.info("Initialization Completed");
    }

    /**
     * Interact with other mods
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("PostInitialization Completed");
    }

    public static void registerRecipes()
    {
        //GameRegistry.addShapelessRecipe(new ItemStack(ict), new ItemStack(Blocks.crafting_table));
    }
}