package ict.proxy;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy implements IProxy
{
    public EntityPlayer getPlayerEntity(MessageContext context)
    {
        return context.getServerHandler().playerEntity;
    }
}