package ict.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ict.ICT;
import ict.client.renderer.TileEntityICTRenderer;
import ict.helper.RenderHelper;
import ict.tileentity.TileEntityICT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public static void registerProxies()
    {
        //FMLCommonHandler.instance().bus().register(new KeyHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityICT.class, new TileEntityICTRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ICT.ict), new RenderHelper());
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext context)
    {
        return (context.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(context));
    }
}