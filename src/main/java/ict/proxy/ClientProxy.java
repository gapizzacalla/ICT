package ict.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ict.handler.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy
{
    public static void registerProxies()
    {
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext context)
    {
        return (context.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(context));
    }
}