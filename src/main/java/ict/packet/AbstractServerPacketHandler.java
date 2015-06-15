package ict.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractServerPacketHandler<T extends IMessage> extends AbstractPacketHandler<T>
{
    public final IMessage handleClientMessage(EntityPlayer player, T message, MessageContext context)
    {
        return null;
    }
}