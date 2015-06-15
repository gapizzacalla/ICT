package ict.handler;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import ict.ICT;
import ict.packet.OpenGuiPacket;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHandler
{
    private static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(ICT.MOD_ID);
    private static byte packetID = 0;

    public static void registerPackets()
    {
        PacketHandler.registerMessage(OpenGuiPacket.Handler.class, OpenGuiPacket.class, Side.SERVER);
    }

    private static void registerMessage(Class handler, Class message, Side side)
    {
        PacketHandler.network.registerMessage(handler, message, packetID++, side);
    }

    public static void sendTo(IMessage message, EntityPlayerMP playerMP)
    {
        PacketHandler.network.sendTo(message, playerMP);
    }

    public static void sendToServer(IMessage message)
    {
        PacketHandler.network.sendToServer(message);
    }

    public static void sendToDimension (IMessage message, int dimensionID)
    {
        PacketHandler.network.sendToDimension(message, dimensionID);
    }
}