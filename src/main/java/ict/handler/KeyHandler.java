package ict.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import ict.ICT;
import ict.packet.OpenGuiPacket;
import ict.reference.Values;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyHandler
{
    public static final int BACKPACK_KEY = 0;
    private static final String[] keyDesc = {"keys." + ICT.MOD_ID + ":backpack"};
    private static final Integer[] keyValues = {Keyboard.KEY_R};
    private final KeyBinding[] keys;

    public KeyHandler()
    {
        keys = new KeyBinding[keyValues.length];
        for (int i = 0; i < keyValues.length; i++)
        {
            keys[i] = new KeyBinding(keyDesc[i], keyValues[i], "keys." + ICT.MOD_ID + ":category");
            ClientRegistry.registerKeyBinding(keys[i]);
        }
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event)
    {
        if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class))
        {
            int key = Keyboard.getEventKey();
            boolean isKeyPressed = Keyboard.getEventKeyState();
            if (isKeyPressed && key == keyValues[BACKPACK_KEY])
            {
                PacketHandler.sendToServer(new OpenGuiPacket(Values.GUI_ICT_ID));
            }
        }
    }
}