package ict.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ict.ICT;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacketHandler<T extends IMessage> implements IMessageHandler<T, IMessage>
{
    @SideOnly(Side.CLIENT)
    public abstract IMessage handleClient(EntityPlayer player, T message, MessageContext context);

    public abstract IMessage handleServer(EntityPlayer player, T message, MessageContext context);

    @Override
    public IMessage onMessage(T message, MessageContext context)
    {
        if(context.side.isClient())
            return handleClient(ICT.commonProxy.getPlayerEntity(context), message, context);
        else
            return handleServer(ICT.commonProxy.getPlayerEntity(context), message, context);
    }
}